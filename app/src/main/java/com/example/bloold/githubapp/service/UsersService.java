package com.example.bloold.githubapp.service;

import com.example.bloold.githubapp.domain.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by bloold on 18.08.17.
 */

public interface UsersService {
    @GET("users")
    Observable<List<User>> getUsersByName();
}
