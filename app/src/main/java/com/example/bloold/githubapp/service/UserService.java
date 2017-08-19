package com.example.bloold.githubapp.service;

import com.example.bloold.githubapp.domain.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by bloold on 17.08.17.
 */

public interface UserService {
        @GET("users/{username}")
        Observable<User> getUserByName(@Path("username") String username);
}
