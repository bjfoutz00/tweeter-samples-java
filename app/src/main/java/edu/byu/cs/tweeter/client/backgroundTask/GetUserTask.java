package edu.byu.cs.tweeter.client.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.model.net.response.UserResponse;

/**
 * Background task that returns the profile for a specified user.
 */
public class GetUserTask extends AuthenticatedTask {
    private static final String LOG_TAG = "GetUserTask";
    public static final String USER_KEY = "user";
    private static final String URL_PATH = "/getUser";

    /**
     * Alias (or handle) for user whose profile is being retrieved.
     */
    private String alias;
    private User user;

    public GetUserTask(AuthToken authToken, String alias, Handler messageHandler) {
        super(messageHandler, authToken);
        this.alias = alias;
    }

    @Override
    protected void runTask() throws Exception {
        TargetUserRequest request = new TargetUserRequest(authToken, alias);
        UserResponse response = getServerFacade().getUser(request, URL_PATH);

        if (response.isSuccess()) {
            user = response.getUser();
            sendSuccessMessage();
        } else {
            sendFailedMessage(response.getMessage());
        }
    }

    @Override
    protected void addBundleData(Bundle msgBundle) {
        msgBundle.putSerializable(USER_KEY, user);
    }

}
