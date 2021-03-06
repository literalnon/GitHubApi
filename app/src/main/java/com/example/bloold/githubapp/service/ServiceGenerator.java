package com.example.bloold.githubapp.service;

import com.example.bloold.githubapp.domain.User;
import com.example.bloold.githubapp.service.RepositoryService;
import com.example.bloold.githubapp.service.UserService;
import com.example.bloold.githubapp.service.UsersService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bloold on 17.08.17.
 */

public class ServiceGenerator {
    static private final String GIT_HUB_API_URL = "https://api.github.com/";

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GIT_HUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static UserService getUserService(){
        return getRetrofit().create(UserService.class);
    }

    public static UsersService getUsersService(){
        return getRetrofit().create(UsersService.class);
    }

    public static RepositoryService getRepositoryService(){
        return getRetrofit().create(RepositoryService.class);
    }
}
