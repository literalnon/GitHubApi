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

    static private final String PERSONAL_TOKEN = "14f30e9f697093ffa61c2cadc1ca4cb51d65b4c2";
    static private final String GIT_HUB_API_URL = "https://api.github.com/";

    private static Retrofit getRetrofit() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request authRequest = request.newBuilder().addHeader("Authorization", "Bearer " + PERSONAL_TOKEN).build();
                return chain.proceed(authRequest);
            }
        }).build();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GIT_HUB_API_URL)
                .client(client)
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
