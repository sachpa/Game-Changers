package com.apps.gamechangers.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.apps.gamechangers.R;
import com.apps.gamechangers.model.User;
import com.apps.gamechangers.viewmodel.UserViewModel;

public class UsersActivity extends AppCompatActivity implements UserFragment.UserSelectedListener {

    public static final String TAG_USERS_FRAGMENT = "fragment.users";
    public static final String TAG_PROFILE_FRAGMENT = "fragment.profile";

    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fragments);
        getSupportActionBar().hide();

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment userFragment = fragmentManager.findFragmentByTag(TAG_USERS_FRAGMENT);
        if (userFragment == null) {
            userFragment = new UserFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_placeholder, userFragment, TAG_USERS_FRAGMENT)
                    .disallowAddToBackStack()
                    .commit();
        }
    }

    @Override
    public void onUserSelected(User user) {
        viewModel.setSelectedUser(user);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_placeholder, new ProfileFragment(), TAG_PROFILE_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }
}
