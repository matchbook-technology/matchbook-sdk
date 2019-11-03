package com.matchbook.sdk.rest.workers;

import com.matchbook.sdk.core.StreamObserver;
import com.matchbook.sdk.core.exceptions.ErrorType;
import com.matchbook.sdk.core.exceptions.MatchbookSDKException;
import com.matchbook.sdk.core.exceptions.MatchbookSDKHttpException;
import com.matchbook.sdk.rest.UserClient;
import com.matchbook.sdk.rest.UserClientRest;
import com.matchbook.sdk.rest.configs.ConnectionManager;
import com.matchbook.sdk.rest.dtos.user.Login;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionKeepAliveScheduler {

    private static final long KEEP_ALIVE_PERIOD_HOURS = 4L;

    private static Logger LOG = LoggerFactory.getLogger(SessionKeepAliveScheduler.class);

    private final long loginTimeout;
    private final ScheduledExecutorService sessionKeepAliveExecutor;
    private final UserClient userClient;

    private boolean isStarted;

    public SessionKeepAliveScheduler(ConnectionManager connectionManager) {
        loginTimeout = connectionManager.getClientConfig().getHttpConfig().getWriteTimeoutInMillis();
        userClient = new UserClientRest(connectionManager);

        sessionKeepAliveExecutor = Executors.newSingleThreadScheduledExecutor(runnable -> {
            final Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("mb-sdk-session-manager");
            return thread;
        });
        isStarted = false;
    }

    public void start() {
        if (!isStarted) {
            doLogin();
            startScheduler();
            isStarted = true;
        }
    }

    private void doLogin() {
        CompletableFuture<Void> loginFuture = new CompletableFuture<>();
        userClient.login(new StreamObserver<Login>() {

            @Override
            public void onNext(Login login) {
                LOG.info("User {} successfully logged in.", login.getAccount().getUsername());
            }

            @Override
            public void onCompleted() {
                loginFuture.complete(null);
            }

            @Override
            public <E extends MatchbookSDKException> void onError(E exception) {
                LOG.warn("Unable to authenticate user.", exception);
                loginFuture.completeExceptionally(exception);
            }
        });

        try {
            loginFuture.get(loginTimeout, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            LOG.error("An error occurred performing authentication.", e);
            throw new MatchbookSDKHttpException(e.getCause(), ErrorType.UNAUTHENTICATED);
        } catch (InterruptedException | TimeoutException e) {
            LOG.error("The login request did not complete.", e);
            throw new MatchbookSDKHttpException(e, ErrorType.HTTP);
        }
    }

    private void startScheduler() {
        SessionKeepAliveTask sessionKeepAliveTask = new SessionKeepAliveTask(userClient);
        sessionKeepAliveExecutor.scheduleAtFixedRate(sessionKeepAliveTask,
                KEEP_ALIVE_PERIOD_HOURS,
                KEEP_ALIVE_PERIOD_HOURS,
                TimeUnit.HOURS);
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void stop() {
        sessionKeepAliveExecutor.shutdown();
        isStarted = false;
    }

}
