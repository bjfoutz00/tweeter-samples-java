package edu.byu.cs.tweeter.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.request.PagedRequest;
import edu.byu.cs.tweeter.model.net.response.PagedResponse;
import edu.byu.cs.tweeter.server.lambda.GetFollowersHandler;

public class PagedResponseTest {
    @Test
    void testGetFollowers() {
        GetFollowersHandler handler = new GetFollowersHandler();
        PagedRequest<User> request = new PagedRequest<User>(new AuthToken(), "@bob", 2, null);

        PagedResponse<User> response = handler.handleRequest(request, null);

        assert(response.isSuccess());
        assertEquals(response.getItems().size(), 2);
        assert(response.hasMorePages());
    }
}
