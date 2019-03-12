package com.apps.gamechangers.repository;

import android.content.Context;

public class Repository {

    private final static Repository repository = new Repository();

    private Repository() { }

    public static Repository get() {
        return repository;
    }

    public DataSource getDataSource(Context context) {
        return new WebService(context);
    }
}
