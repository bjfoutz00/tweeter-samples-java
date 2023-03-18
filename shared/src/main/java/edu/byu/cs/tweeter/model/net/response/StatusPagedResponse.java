package edu.byu.cs.tweeter.model.net.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;

public class StatusPagedResponse extends PagedResponse<Status> {
    public StatusPagedResponse(boolean success, List<Status> items, boolean hasMorePages) {
        super(success, items, hasMorePages);
    }

    public StatusPagedResponse(boolean success, String message, boolean hasMorePages) {
        super(success, message, hasMorePages);
    }
}
