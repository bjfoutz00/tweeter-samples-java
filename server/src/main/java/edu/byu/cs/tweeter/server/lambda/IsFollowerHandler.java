package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.model.net.response.IsFollowerResponse;
import edu.byu.cs.tweeter.server.service.FollowService;

public class IsFollowerHandler implements RequestHandler<TargetUserRequest, IsFollowerResponse> {

    @Override
    public IsFollowerResponse handleRequest(TargetUserRequest request, Context context) {
        FollowService service = new FollowService();
        return service.isFollower(request);
    }
}

