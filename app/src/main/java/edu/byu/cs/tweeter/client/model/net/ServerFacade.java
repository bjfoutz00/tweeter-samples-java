package edu.byu.cs.tweeter.client.model.net;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.request.LoginRequest;
import edu.byu.cs.tweeter.model.net.request.PagedRequest;
import edu.byu.cs.tweeter.model.net.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.net.request.RegisterRequest;
import edu.byu.cs.tweeter.model.net.request.SessionRequest;
import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.model.net.response.CountResponse;
import edu.byu.cs.tweeter.model.net.response.AuthenticationResponse;
import edu.byu.cs.tweeter.model.net.response.IsFollowerResponse;
import edu.byu.cs.tweeter.model.net.response.PagedResponse;
import edu.byu.cs.tweeter.model.net.response.Response;
import edu.byu.cs.tweeter.model.net.response.UserResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {

    // TODO: Set this to the invoke URL of your API. Find it by going to your API in AWS, clicking
    //  on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "Insert your API invoke URL here";

    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public AuthenticationResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, AuthenticationResponse.class);
    }

    public AuthenticationResponse register(RegisterRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, AuthenticationResponse.class);
    }

    public Response logout(SessionRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, Response.class);
    }

    public Response follow(TargetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, Response.class);
    }

    public Response unfollow(TargetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, Response.class);
    }

    public IsFollowerResponse getIsFollowing(TargetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, IsFollowerResponse.class);
    }

    public PagedResponse<User> getFollowers(PagedRequest<User> request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, PagedResponse.class);
    }

    public PagedResponse<User> getFollowing(PagedRequest<User> request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, PagedResponse.class);
    }

    public PagedResponse<Status> getFeed(PagedRequest<Status> request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, PagedResponse.class);
    }

    public PagedResponse<Status> getStory(PagedRequest<Status> request, String urlPath)
            throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, PagedResponse.class);
    }

    public UserResponse getUser(TargetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, UserResponse.class);
    }

    public Response postStatus(PostStatusRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, Response.class);
    }

    public CountResponse getFollowersCount(TargetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, CountResponse.class);
    }

    public CountResponse getFollowingCount(TargetUserRequest request, String urlPath) throws IOException, TweeterRemoteException {
        return clientCommunicator.doPost(urlPath, request, null, CountResponse.class);
    }



//    /**
//     * Returns the users that the user specified in the request is following. Uses information in
//     * the request object to limit the number of followees returned and to return the next set of
//     * followees after any that were returned in a previous request.
//     *
//     * @param request contains information about the user whose followees are to be returned and any
//     *                other information required to satisfy the request.
//     * @return the followees.
//     */
//    public FollowingResponse getFollowees(FollowingRequest request, String urlPath)
//            throws IOException, TweeterRemoteException {
//        return clientCommunicator.doPost(urlPath, request, null, FollowingResponse.class);
//    }
}