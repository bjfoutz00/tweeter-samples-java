package edu.byu.cs.tweeter.server.service;

import static edu.byu.cs.tweeter.util.FakeData.getInstance;

import java.util.List;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.net.request.PagedRequest;
import edu.byu.cs.tweeter.model.net.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.net.response.PagedResponse;
import edu.byu.cs.tweeter.model.net.response.Response;
import edu.byu.cs.tweeter.util.FakeData;
import edu.byu.cs.tweeter.util.Pair;

public class StatusService {

    public PagedResponse<Status> getFeed(PagedRequest<Status> request) {
        Pair<List<Status>, Boolean> page = FakeData.getInstance().getPageOfStatus(request.getLastItem(), request.getPageSize());
        return new PagedResponse<>(true, page.getFirst(), page.getSecond());
    }

    public PagedResponse<Status> getStory(PagedRequest<Status> request) {
        Pair<List<Status>, Boolean> page = FakeData.getInstance().getPageOfStatus(request.getLastItem(), request.getPageSize());
        return new PagedResponse<>(true, page.getFirst(), page.getSecond());
    }

    public Response postStatus(PostStatusRequest request) {
        return new Response(true);
    }


}
