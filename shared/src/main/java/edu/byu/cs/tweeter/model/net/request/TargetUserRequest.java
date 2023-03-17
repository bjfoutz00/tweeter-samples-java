package edu.byu.cs.tweeter.model.net.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;

/**
 * Doubles as follow/unfollow/isfollowing/getfollowingcount/getfollowerscount request (for now)
 */
public class TargetUserRequest extends SessionRequest {
    // user to follow/unfollow/
    private String targetUserAlias;

    private TargetUserRequest() {}

    public TargetUserRequest(AuthToken authToken, String targetUserAlias) {
        super(authToken);
        this.targetUserAlias = targetUserAlias;
    }

    public String getTargetUserAlias() {
        return targetUserAlias;
    }

    public void setTargetUserAlias(String targetUserAlias) {
        this.targetUserAlias = targetUserAlias;
    }
}
