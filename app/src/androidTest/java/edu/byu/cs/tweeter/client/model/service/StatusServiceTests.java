package edu.byu.cs.tweeter.client.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import edu.byu.cs.tweeter.client.backgroundTask.PagedTask;
import edu.byu.cs.tweeter.client.backgroundTask.observer.GetPageTaskObserver;
import edu.byu.cs.tweeter.client.backgroundTask.observer.PagedTaskObserver;
import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.presenter.StoryPresenter;
import edu.byu.cs.tweeter.client.view.main.story.StoryFragment;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.FakeData;

public class StatusServiceTests {
    StatusService service;
    CountDownLatch countDownLatch;
    User currentUser;
    StoryObserver observer;

    @BeforeEach
    void setUp() {
        currentUser = new User("first", "last", "@bob", null);
        service = Mockito.spy(new StatusService());
        observer = new StoryObserver();
        countDownLatch = new CountDownLatch(1);
        Cache.getInstance().setCurrUserAuthToken(new AuthToken());
    }

    private void awaitCountDownLatch() throws InterruptedException {
        countDownLatch.await();
        countDownLatch = new CountDownLatch(1);
    }

    @Test
    void getStoryTest() throws InterruptedException {
        service.getStoryStatuses(currentUser, 2, null, observer);
        awaitCountDownLatch();

        List<Status> expectedStory = FakeData.getInstance().getFakeStatuses().subList(0, 2);
        assertTrue(observer.isSuccess());
        assertNull(observer.getMessage());
        for (int i = 0; i < expectedStory.size(); i++) {
            assertEquals(expectedStory.get(i).post, observer.getStory().get(i).post);
            assertEquals(expectedStory.get(i).mentions, observer.getStory().get(i).mentions);
            assertEquals(expectedStory.get(i).user, observer.getStory().get(i).user);
            assertEquals(expectedStory.get(i).urls, observer.getStory().get(i).urls);
        }
        assertTrue(observer.hasMorePages());
        assertNull(observer.getException());
    }

    private class StoryObserver implements PagedTaskObserver<Status> {

        private boolean success;
        private String message;
        private List<Status> story;
        private boolean hasMorePages;
        private Exception exception;

        @Override
        public void handleSuccess(List<Status> story, boolean hasMorePages) {
            this.success = true;
            this.message = null;
            this.story = story;
            this.hasMorePages = hasMorePages;
            this.exception = null;

            countDownLatch.countDown();
        }

        @Override
        public void handleFailure(String message) {
            this.success = false;
            this.message = message;
            this.story = null;
            this.hasMorePages = false;
            this.exception = null;

            countDownLatch.countDown();
        }

        @Override
        public void handleException(Exception exception) {
            this.success = false;
            this.message = null;
            this.story = null;
            this.hasMorePages = false;
            this.exception = exception;

            countDownLatch.countDown();
        }

        @Override
        public void displayMessage(String message) {}

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public List<Status> getStory() {
            return story;
        }

        public boolean hasMorePages() {
            return hasMorePages;
        }

        public Exception getException() {
            return exception;
        }
    }
}
