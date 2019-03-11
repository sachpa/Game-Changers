package com.apps.gamechangers.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("ok")
    private boolean isSuccessful;

    @SerializedName("members")
    private List<User> users;

    @SerializedName("response_metadata")
    private MetaData metaData ;


    public boolean isSuccessful() {
        return isSuccessful;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getNextCursor() {
        if (metaData != null) {
            return metaData.getNextCursor();
        }
        return null;
    }

    public class MetaData {
        @SerializedName("next_cursor")
        private String nextCursor;

        public String getNextCursor() {
            return nextCursor;
        }
    }
}
