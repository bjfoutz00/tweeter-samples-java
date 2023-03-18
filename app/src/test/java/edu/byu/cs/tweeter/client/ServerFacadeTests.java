package edu.byu.cs.tweeter.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.net.request.RegisterRequest;
import edu.byu.cs.tweeter.model.net.response.AuthenticationResponse;

public class ServerFacadeTests {
    ServerFacade serverFacade;

    @BeforeEach
    void setUp() {
        serverFacade = new ServerFacade();
    }

    @Test
    void registerTest() throws IOException, TweeterRemoteException {
        RegisterRequest request = new RegisterRequest("bob", "bob", "bob", "bob", "img");
        AuthenticationResponse testResponse = serverFacade.register(request, "/register");

        assert(testResponse.isSuccess());
        assertEquals(testResponse.getUser().getAlias(), "@allen");
        assertEquals(testResponse.getAuthToken().getToken(), "");
    }
}
