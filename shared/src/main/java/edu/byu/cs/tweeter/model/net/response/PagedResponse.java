package edu.byu.cs.tweeter.model.net.response;

import java.util.List;

/**
 * A response that can indicate whether there is more data available from the server.
 */
public class PagedResponse<T> extends Response {

    public List<T> items;
    public boolean hasMorePages;

    public PagedResponse(boolean success, List<T> items, boolean hasMorePages) {
        super(success);
        this.items = items;
        this.hasMorePages = hasMorePages;
    }

    public PagedResponse(boolean success, String message, boolean hasMorePages) {
        super(success, message);
        this.hasMorePages = hasMorePages;
    }

    public List<T> getItems() {
        return items;
    }

    /**
     * An indicator of whether more data is available from the server. A value of true indicates
     * that the result was limited by a maximum value in the request and an additional request
     * would return additional data.
     *
     * @return true if more data is available; otherwise, false.
     */
    public boolean hasMorePages() {
        return hasMorePages;
    }
}
