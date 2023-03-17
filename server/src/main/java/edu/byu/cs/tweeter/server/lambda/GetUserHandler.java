package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.model.net.response.UserResponse;
import edu.byu.cs.tweeter.server.service.UserService;

public class GetUserHandler implements RequestHandler<TargetUserRequest, UserResponse> {

        @Override
        public UserResponse handleRequest(TargetUserRequest request, Context context) {
                UserService service = new UserService();
                return service.getUser(request);
        }
}