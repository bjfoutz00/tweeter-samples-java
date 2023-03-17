package edu.byu.cs.tweeter.model.net.response;

public class CountResponse extends Response {
    int count;

    public CountResponse(boolean success, int count) {
        super(success);
        this.count = count;
    }

    public CountResponse(boolean success, String message, int count) {
        super(success, message);
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
