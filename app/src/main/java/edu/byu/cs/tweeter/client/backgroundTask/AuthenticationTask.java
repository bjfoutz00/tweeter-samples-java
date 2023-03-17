package edu.byu.cs.tweeter.client.backgroundTask;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.response.AuthenticationResponse;

public abstract class AuthenticationTask extends BackgroundTask {
    private static final String LOG_TAG = "AuthenticationTask";

    public static final String USER_KEY = "user";
    public static final String AUTH_TOKEN_KEY = "auth-token";
    /**
     * The user's username (or "alias" or "handle"). E.g., "@susan".
     */
    protected String username;
    /**
     * The user's password.
     */
    protected String password;
    private User authenticatedUser;
    private AuthToken authToken;

    public AuthenticationTask(Handler messageHandler, String username, String password) {
        super(messageHandler);
        this.username = username;
        this.password = password;
    }

    @Override
    protected void runTask() throws Exception {
        AuthenticationResponse response = doAuthentication();

        if (response.isSuccess()) {
            authenticatedUser = response.getUser();
            authToken = response.getAuthToken();
            sendSuccessMessage();
        } else {
            sendFailedMessage(response.getMessage());
        }

    }

    @Override
    protected void addBundleData(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, authenticatedUser);
        msgBundle.putSerializable(AUTH_TOKEN_KEY, authToken);
    }

    protected abstract AuthenticationResponse doAuthentication() throws Exception;

//    private Pair<User, AuthToken> doAuthentication() {
//        authenticatedUser = getFakeData().getFirstUser();
//        authToken = getFakeData().getAuthToken();
//        return new Pair<>(authenticatedUser, authToken);
//    }
}
