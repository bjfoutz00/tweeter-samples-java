package edu.byu.cs.tweeter.model.net.response;

import edu.byu.cs.tweeter.model.domain.User;

public class UserResponse extends Response {
    User user;

    public UserResponse(boolean success, User user) {
        super(success);
        this.user = user;
    }

    UserResponse(boolean success, String message, User user) {
        super(success, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
