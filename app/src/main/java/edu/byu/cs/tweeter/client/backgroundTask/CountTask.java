package edu.byu.cs.tweeter.client.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.response.CountResponse;

public abstract class CountTask extends AuthenticatedTask {
    public static final String COUNT_KEY = "count";
    private int count;

    /**
     * The user whose follower count is being retrieved.
     * (This can be any user, not just the currently logged-in user.)
     */
    private User targetUser;

    public CountTask(Handler messageHandler, AuthToken authToken, User targetUser) {
        super(messageHandler, authToken);
        this.targetUser = targetUser;
    }

    @Override
    protected void runTask() throws Exception {
        CountResponse response = runCountTask();

        if (response.isSuccess()) {
            count = response.getCount();
            sendSuccessMessage();
        } else {
            sendFailedMessage(response.getMessage());
        }
    }

    @Override
    protected void addBundleData(Bundle msgBundle) {
        msgBundle.putInt(COUNT_KEY, count);
    }

    protected abstract CountResponse runCountTask() throws Exception;

    public int getCount() {
        return count;
    }

    public User getTargetUser() {
        return targetUser;
    }
}
