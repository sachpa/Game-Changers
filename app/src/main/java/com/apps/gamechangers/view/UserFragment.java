package com.apps.gamechangers.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.gamechangers.R;
import com.apps.gamechangers.model.User;
import com.apps.gamechangers.viewmodel.UserViewModel;

import java.util.List;

public class UserFragment extends Fragment {

    private UserViewModel viewModel;
    private UserListAdapter adapter;

    public interface UserSelectedListener {
        void onUserSelected(User user);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_users, null);

        viewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
        observeUserListChanges();
        createUserList(rootView);
        return rootView;
    }

    private void createUserList(View rootView) {
        final RecyclerView usersListView = rootView.findViewById(R.id.users_list_view);
        usersListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UserListAdapter(new UserListAdapter.Callback() {
            @Override
            public void onSelected(User user) {
                ((UserSelectedListener)getActivity()).onUserSelected(user);
            }
        });
        usersListView.setAdapter(adapter);
        viewModel.onUserListCreated();
    }

    private void observeUserListChanges() {
        viewModel.getUsersLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapter.setUsers(users);
            }
        });
    }
}
