package com.apps.gamechangers.model;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")               private String id;
    @SerializedName("color")            private String color;
    @SerializedName("profile")          private Profile profile;
    @SerializedName("updated")          private long updated;

    public String getId() {
        return id;
    }

    public int getColor() {
        return Color.parseColor("#" + color);
    }

    public long getUpdated() {
        return updated;
    }

    public Profile getProfile() {
        return profile;
    }


    public static class Profile {

        @SerializedName("real_name")         private String realName;
        @SerializedName("display_name")      private String displayName;
        @SerializedName("status")            private String status;
        @SerializedName("email")             private String email;
        @SerializedName("phone")             private String phone;
        @SerializedName("skype")             private String skype;
        @SerializedName("image_24")          private String profilePictureThumbnail;
        @SerializedName("image_512")         private String profilePicture;

        public String getRealName() {
            return realName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getStatus() {
            return status;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getSkype() {
            return skype;
        }

        public String getProfilePictureThumbnail() {
            return profilePictureThumbnail;
        }

        public String getProfilePicture() {
            return profilePicture;
        }
    }
}
