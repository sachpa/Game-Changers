package com.apps.gamechangers.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.gamechangers.R;
import com.apps.gamechangers.model.User;
import com.apps.gamechangers.viewmodel.UserViewModel;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    private UserViewModel viewModel;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_profile, null);

        viewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        user =  viewModel.getSelectedUser();
        ((TextView)rootView.findViewById(R.id.user_name)).setText(user.getProfile().getRealName());
        ((TextView)rootView.findViewById(R.id.user_display_name)).setText(getString(R.string.code_name, user.getProfile().getDisplayName()));
        updateTimeTravel(rootView);
        final ImageView profilePicture = rootView.findViewById(R.id.avatar);
        profilePicture.setBackgroundColor(user.getColor());

        Glide.with(this)
                .load(user.getProfile().getProfilePicture())
                .into(profilePicture);

        return rootView;
    }

    private void updateTimeTravel(View rootView) {
        final TextView textView = rootView.findViewById(R.id.user_local_time);
        if (user.getTimeDifference() == 0) {
            textView.setText(getString(R.string.travels_present));
        } else if (user.getTimeDifference() > 0) {
            textView.setText(getString(R.string.travels_future, "" + user.getTimeDifference()));
        } else {
            textView.setText(getString(R.string.travels_past, "" + Math.abs(user.getTimeDifference())));
        }
    }
}
