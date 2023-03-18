package edu.byu.cs.tweeter.client.backgroundTask;

import android.os.Bundle;
import android.os.Handler;

import java.io.Serializable;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.response.PagedResponse;
import edu.byu.cs.tweeter.util.Pair;

public abstract class PagedTask<T> extends AuthenticatedTask {
    public static final String ITEMS_KEY = "items";
    public static final String MORE_PAGES_KEY = "more-pages";

    /**
     * The user whose following is being retrieved.
     * (This can be any user, not just the currently logged-in user.)
     */
    private User targetUser;
    /**
     * Maximum number of followed users to return (i.e., page size).
     */
    private int limit;
    /**
     * The last person being followed returned in the previous page of results (can be null).
     * This allows the new page to begin where the previous page ended.
     */
    List<T> items;
    private T lastItem;
    boolean hasMorePages;

    public PagedTask(Handler messageHandler, AuthToken authToken, User targetUser, int limit, T lastItem) {
        super(messageHandler, authToken);
        this.targetUser = targetUser;
        this.limit = limit;
        this.lastItem = lastItem;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public int getLimit() {
        return limit;
    }

    public T getLastItem() {
        return lastItem;
    }

    @Override
    protected void runTask() throws Exception {
        PagedResponse<T> response = getItems();

        if (response.isSuccess()) {
            items = response.getItems();
            hasMorePages = response.hasMorePages();
            sendSuccessMessage();
        } else {
            sendFailedMessage(response.getMessage());
        }
    }

    @Override
    protected void addBundleData(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) items);
        msgBundle.putBoolean(MORE_PAGES_KEY, hasMorePages);
    }

    protected abstract PagedResponse<T> getItems() throws Exception;
}
