package edu.byu.cs.tweeter.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.UnknownServiceException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.request.LoginRequest;
import edu.byu.cs.tweeter.model.net.request.PagedRequest;
import edu.byu.cs.tweeter.model.net.request.RegisterRequest;
import edu.byu.cs.tweeter.model.net.request.TargetUserRequest;
import edu.byu.cs.tweeter.model.net.response.AuthenticationResponse;
import edu.byu.cs.tweeter.model.net.response.CountResponse;
import edu.byu.cs.tweeter.model.net.response.PagedResponse;

public class ServerFacadeTests {
    ServerFacade serverFacade;
    AuthToken authToken;

    @BeforeEach
    void setUp() throws IOException, TweeterRemoteException {
        serverFacade = new ServerFacade();
        LoginRequest request = new LoginRequest("a", "b");
        authToken = serverFacade.login(request, "/login").getAuthToken();
    }

    @Test
    void registerTest() throws IOException, TweeterRemoteException {
        RegisterRequest request = new RegisterRequest("bob", "bob", "bob", "bob", "img");
        AuthenticationResponse testResponse = serverFacade.register(request, "/register");

        assert(testResponse.isSuccess());
        assertEquals(testResponse.getUser().getAlias(), "@allen");
        assertNotNull(testResponse.getAuthToken());
    }

    @Test
    void getFollowersTest() throws IOException, TweeterRemoteException {
        int pageSize = 1;
        PagedRequest<User> request = new PagedRequest<>(authToken, "@bob", pageSize, null);
        PagedResponse<User> response = serverFacade.getFollowers(request, "/getFollowers");

        System.out.println(response.hasMorePages());
        assert(response.isSuccess());
        assertEquals(response.getItems().size(), pageSize);
        assert(response.hasMorePages());
    }

    @Test
    void getFollowingCountTest() throws IOException, TweeterRemoteException {
        TargetUserRequest request = new TargetUserRequest(authToken, "@bob");
        CountResponse response = serverFacade.getFollowingCount(request, "/getFollowingCount");

        assert(response.isSuccess());
        assertEquals(response.getCount(), 20);
    }
}
