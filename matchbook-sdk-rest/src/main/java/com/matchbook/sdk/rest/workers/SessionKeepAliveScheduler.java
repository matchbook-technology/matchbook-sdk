package com.matchbook.sdk.rest.workers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.core.utils.VisibleForTesting;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.UserClientRest;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.dtos.user.Login;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionKeepAliveScheduler {

    private static final long KEEP_ALIVE_PERIOD_HOURS = 4L;

    private static Logger LOG = LoggerFactory.getLogger(SessionKeepAliveScheduler.class);

    private final long loginTimeout;
    private final ScheduledExecutorService sessionKeepAliveExecutor;
    private final UserClient userClient;

    public SessionKeepAliveScheduler(ConnectionManager connectionManager) {
        loginTimeout = connectionManager.getClientConfig().getHttpConfig().getWriteTimeoutInMillis();
        userClient = new UserClientRest(connectionManager);

        sessionKeepAliveExecutor = Executors.newSingleThreadScheduledExecutor(runnable -> {
            final Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("mb-sdk-session-manager");
            return thread;
        });
    }

    @VisibleForTesting
    SessionKeepAliveScheduler(long loginTimeout, UserClient userClient, ScheduledExecutorService executor) {
        this.loginTimeout = loginTimeout;
        this.userClient = userClient;
        this.sessionKeepAliveExecutor = executor;
    }

    public void start() {
        doLogin();
        startScheduler();
    }

    private void startScheduler() {
        SessionKeepAliveTask sessionKeepAliveTask = new SessionKeepAliveTask(userClient);
        sessionKeepAliveExecutor.scheduleAtFixedRate(sessionKeepAliveTask,
                KEEP_ALIVE_PERIOD_HOURS,
                KEEP_ALIVE_PERIOD_HOURS,
                TimeUnit.HOURS);
    }

    public void stop() {
        sessionKeepAliveExecutor.shutdown();
    }

    private void doLogin() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        userClient.login(new StreamObserver<Login>() {

            @Override
            public void onNext(Login login) {
                LOG.info("User {} successfully logged in.", login.getAccount().getUsername());
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                throw new MatchbookSDKHttpException(exception, ErrorType.UNAUTHENTICATED);
            }
        });

        try {
            countDownLatch.await(loginTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException exception) {
            throw new MatchbookSDKHttpException(exception, ErrorType.UNAUTHENTICATED);
        }
    }

}
