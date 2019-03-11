package com.apps.gamechangers.repository;

import com.apps.gamechangers.model.Response;

public interface DataSource {

    interface Callback {
        void onComplete(Response response, String nextPage);
        void onError();
    }

    void getUsers(String page, Callback callback);
}
