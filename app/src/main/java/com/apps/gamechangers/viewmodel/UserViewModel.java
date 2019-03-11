package com.apps.gamechangers.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.apps.gamechangers.model.Response;
import com.apps.gamechangers.model.User;
import com.apps.gamechangers.repository.DataSource;
import com.apps.gamechangers.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private User selectedUser;
    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private String nextPage = "";

    public UserViewModel(@NonNull Application application) {
        super(application);
        usersLiveData.setValue(new ArrayList<User>());
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public MutableLiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    void fetchUsers(final boolean fresh) {
        DataSource source = Repository.get().getDataSource(getApplication().getApplicationContext());
        source.getUsers(fresh ? "" : nextPage, new DataSource.Callback() {
            @Override
            public void onComplete(Response response, String nextPagePointer) {
                if (fresh) {
                    usersLiveData.postValue(response.getUsers());
                } else {
                    List<User> users = usersLiveData.getValue();
                    users.addAll(response.getUsers());
                    usersLiveData.postValue(users);
                }
                nextPage = nextPagePointer;
            }

            @Override
            public void onError() {

            }
        });
    }

    public void onUserListCreated() {
        if (usersLiveData.getValue() == null || usersLiveData.getValue().isEmpty()) {
            fetchUsers(true);
        }
    }

    public void onUserListScrolled() {
        if (!TextUtils.isEmpty(nextPage)) {
            fetchUsers(false);
        }
    }
}
