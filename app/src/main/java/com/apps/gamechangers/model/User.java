package com.apps.gamechangers.model;

import android.graphics.Color;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
        @SerializedName("image_24")          private String image24;
        @SerializedName("image_32")          private String image32;
        @SerializedName("image_48")          private String image48;
        @SerializedName("image_72")          private String image72;
        @SerializedName("image_192")          private String image192;
        @SerializedName("image_512")         private String image512;

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
            for (String profilePicture : getProfilePictures()) {
                if (!TextUtils.isEmpty(profilePicture)) {
                    return profilePicture;
                }
            }
            return "";
        }

        public String getProfilePicture() {
            List<String> profilePictures = getProfilePictures();
            for (int i = profilePictures.size() - 1; i >= 0; i--) {
                final String profilePicture = profilePictures.get(i);
                if (!TextUtils.isEmpty(profilePicture)) {
                    return profilePicture;
                }
            }
            return "";
        }

        List<String> getProfilePictures() {
            final List<String> profilePictures = new ArrayList<>();
            profilePictures.add(image24);
            profilePictures.add(image32);
            profilePictures.add(image48);
            profilePictures.add(image72);
            profilePictures.add(image192);
            profilePictures.add(image512);
            return profilePictures;
        }
    }
}
