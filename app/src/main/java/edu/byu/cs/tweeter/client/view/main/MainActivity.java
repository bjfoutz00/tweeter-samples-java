package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.MainPresenter;
import edu.byu.cs.tweeter.client.presenter.views.MainView;
import edu.byu.cs.tweeter.client.view.login.LoginActivity;
import edu.byu.cs.tweeter.client.view.login.StatusDialogFragment;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class MainActivity extends AppCompatActivity implements StatusDialogFragment.Observer, MainView {
    public static final String CURRENT_USER_KEY = "CurrentUser";
    private Toast logOutToast;
    private Toast postingToast;
    private TextView followeeCount;
    private TextView followerCount;
    private Button followButton;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        presenter.setSelectedUser((User) getIntent().getSerializableExtra(CURRENT_USER_KEY));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), presenter.getSelectedUser());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusDialogFragment statusDialogFragment = new StatusDialogFragment();
                statusDialogFragment.show(getSupportFragmentManager(), "post-status-dialog");
            }
        });

        presenter.updateSelectedUserFollowingAndFollowers();

        TextView userName = findViewById(R.id.userName);
        userName.setText(presenter.getSelectedUser().getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(presenter.getSelectedUser().getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        Picasso.get().load(presenter.getSelectedUser().getImageUrl()).into(userImageView);

        followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, "..."));

        followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, "..."));

        followButton = findViewById(R.id.followButton);
        presenter.determineIsFollower();

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFollowButtonClick(followButton.getText().toString(), v.getContext().getString(R.string.following));
            }
        });
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFollowerCount(int count) {
        followerCount.setText(getString(R.string.followerCount, String.valueOf(count)));
    }

    @Override
    public void setFolloweeCount(int count) {
        followeeCount.setText(getString(R.string.followeeCount, String.valueOf(count)));
    }

    @Override
    public void setFollowButtonVisibility(boolean value) {
        if (value) {
            followButton.setVisibility(android.view.View.VISIBLE);
        } else {
            followButton.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void setFollowButton(boolean isFollower) {
        // If logged in user is a follower of the selected user, display the follow button as "following"
        if (isFollower) {
            followButton.setText(R.string.following);
            followButton.setBackgroundColor(getResources().getColor(R.color.white));
            followButton.setTextColor(getResources().getColor(R.color.lightGray));
        } else {
            followButton.setText(R.string.follow);
            followButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void enableFollowButton(boolean enable) {
        followButton.setEnabled(enable);
    }

    @Override
    public void logoutUser() {
        logOutToast.cancel();
        // Revert to login screen.
        Intent intent = new Intent(this, LoginActivity.class);
        // Clear everything so that the main activity is recreated with the login page.
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logoutMenu) {
            logOutToast = Toast.makeText(this, "Logging Out...", Toast.LENGTH_LONG);
            logOutToast.show();
            presenter.logout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStatusPosted(String post) {
        presenter.postStatus(post);
    }
}
