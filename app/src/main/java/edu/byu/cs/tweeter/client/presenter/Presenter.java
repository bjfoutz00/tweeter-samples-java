package edu.byu.cs.tweeter.client.presenter;

public abstract class Presenter {
    public interface View {
        void displayMessage(String message);
    }

    protected View  view;

    Presenter(View view) {
        this.view = view;
    }

    protected abstract String getErrorPrefix();
}
