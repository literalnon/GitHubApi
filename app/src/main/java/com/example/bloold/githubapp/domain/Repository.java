package com.example.bloold.githubapp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bloold on 17.08.17.
 */
public class Repository{

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private String created_date;


    public String getName() {
        return name;
    }

    public String getCreated_date() {
        return created_date;
    }
}
