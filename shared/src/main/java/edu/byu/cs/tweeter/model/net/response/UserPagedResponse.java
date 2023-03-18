package edu.byu.cs.tweeter.model.net.response;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;

public class UserPagedResponse extends PagedResponse<User>{
    public UserPagedResponse(boolean success, List<User> items, boolean hasMorePages) {
        super(success, items, hasMorePages);
    }

    public UserPagedResponse(boolean success, String message, boolean hasMorePages) {
        super(success, message, hasMorePages);
    }
}
