package edu.byu.cs.tweeter.model.net.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

public class PagedRequest<T> extends SessionRequest {
    private String userAlias;
    private int pageSize;
    protected T lastItem;

    protected PagedRequest() {}

    public PagedRequest(AuthToken authToken, String userAlias, int pageSize, T lastItem) {
        super(authToken);
        this.userAlias = userAlias;
        this.pageSize = pageSize;
        this.lastItem = lastItem;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getLastItem() {
        return lastItem;
    }

    public void setLastItem(T lastItem) {
        this.lastItem = lastItem;
    }
}
