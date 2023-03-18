package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.request.LoginRequest;
import edu.byu.cs.tweeter.model.net.request.PagedRequest;
import edu.byu.cs.tweeter.model.net.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.net.request.RegisterRequest;
import edu.byu.cs.tweeter.model.net.request.SessionRequest;
import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.util.FakeData;

public class RequestValidator {

    static void validateLoginRequest(LoginRequest request) {
        if (request.getUsername() == null){
            throw new RuntimeException("[BadRequest] Missing a username");
        } else if (request.getPassword() == null) {
            throw new RuntimeException("[BadRequest] Missing a password");
        }
    }

    static void validateRegisterRequest(RegisterRequest request) {
        if (request.getUsername() == null){
            throw new RuntimeException("[BadRequest] Missing a username");
        } else if (request.getPassword() == null) {
            throw new RuntimeException("[BadRequest] Missing a password");
        } else if (request.getFirstName() == null) {
            throw new RuntimeException("[BadRequest] Missing a first name");
        } else if (request.getLastName() == null) {
            throw new RuntimeException("[BadRequest] Missing a last name");
        } else if (request.getImage() == null) {
            throw new RuntimeException("[BadRequest] Missing an image");
        }
    }

    static void validateSessionRequest(SessionRequest request) {
        // todo: make more robust
        if (request.getAuthToken() == null) {
            throw new RuntimeException("[AuthError] unauthenticated request");
        }
    }

    static void validateTargetUserRequest(TargetUserRequest request) {
        RequestValidator.validateSessionRequest(request);
        if (request.getUserAlias() == null) {
            throw new RuntimeException("[BadRequest] Missing target user alias");
        }
        User user = FakeData.getInstance().findUserByAlias(request.getUserAlias());
        if (user == null) {
            throw new RuntimeException(String.format("[BadRequest] requested user %s does not exist", request.getUserAlias()));
        }
    }

    static void validatePagedRequest(PagedRequest request) {
        RequestValidator.validateSessionRequest(request);
        if (request.getUserAlias() == null) {
            throw new RuntimeException("[BadRequest] Missing user alias");
        } else if (request.getPageSize() <= 0) {
            throw new RuntimeException("[BadRequest] Invalid or missing page size");
        }
        // todo: see if last item exists?
    }

    static void validatePostStatusRequest(PostStatusRequest request) {
        RequestValidator.validateSessionRequest(request);
        if (request.getStatus() == null) {
            throw new RuntimeException("[BadRequest] Missing status");
        }
    }
}
