package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.model.net.response.Response;
import edu.byu.cs.tweeter.server.service.FollowService;

public class UnfollowHandler implements RequestHandler<TargetUserRequest, Response> {

    @Override
    public Response handleRequest(TargetUserRequest request, Context context) {
        FollowService service = new FollowService();
        return service.unfollow(request);
    }
}
