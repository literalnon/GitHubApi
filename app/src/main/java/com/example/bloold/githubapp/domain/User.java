package com.example.bloold.githubapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bloold on 17.08.17.
 */

public class User {
    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private String id;

    @SerializedName("public_repos")
    private int reposCount;

    @SerializedName("avatar_url")
    private String avatar;

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public int getReposCount() {
        return reposCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setReposCount(int reposCount) {
        this.reposCount = reposCount;
    }
}
