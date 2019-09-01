package com.matchbook.sdk.rest.workers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.ConnectionManager;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.UserClientRest;
import com.matchbook.sdk.rest.dtos.user.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionManager {

    private static final Logger LOG = LoggerFactory.getLogger(SessionManager.class);

    private final ScheduledExecutorService loginSchedulerExecutor;
    private final UserClient userClient;

    public SessionManager(ConnectionManager connectionManager) {
        this.userClient = new UserClientRest(connectionManager);

        ThreadFactory threadFactory = new SessionManagerThreadFactory();
        this.loginSchedulerExecutor = Executors.newSingleThreadScheduledExecutor(threadFactory);
    }

    public void keepAlive() {
        doLogin();
        startScheduler();
    }

    private void startScheduler() {
        SessionKeepAliveScheduler sessionKeepAliveScheduler = new SessionKeepAliveScheduler(userClient);
        loginSchedulerExecutor.scheduleAtFixedRate(sessionKeepAliveScheduler,
                4,
                4,
                TimeUnit.HOURS);
    }

    private void doLogin() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        userClient.login(new StreamObserver<Login>() {
            @Override
            public void onNext(com.matchbook.sdk.rest.dtos.user.Login login) {
                LOG.info("Successfully login to the user: {}", login.getAccount().getUsername());
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
            countDownLatch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            throw new MatchbookSDKHttpException(exception, ErrorType.UNAUTHENTICATED);
        }
    }

    private static class SessionManagerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("session-manager");
            return t;
        }
    }
}
