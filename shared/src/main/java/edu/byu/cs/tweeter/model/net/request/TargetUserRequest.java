package edu.byu.cs.tweeter.model.net.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

/**
 * Doubles as follow/unfollow/isfollowing/getfollowingcount/getfollowerscount request (for now)
 */
public class TargetUserRequest extends SessionRequest {
    // user to follow/unfollow/
    private String userAlias;

    private TargetUserRequest() {}

    public TargetUserRequest(AuthToken authToken, String userAlias) {
        super(authToken);
        this.userAlias = userAlias;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
}
