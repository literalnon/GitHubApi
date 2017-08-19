package com.example.bloold.githubapp.Presenter;

import com.example.bloold.githubapp.Model.Model;
import com.example.bloold.githubapp.View.MainView;
import com.example.bloold.githubapp.domain.User;

import java.util.List;

/**
 * Created by bloold on 18.08.17.
 */

public abstract class Presenter<M extends Model, V extends MainView> {
    protected M model;
    protected V view;

    public abstract List<User> getUsers();

    public abstract void updateUsers();
    public abstract void updateRepository(String username);

    public abstract void startRepositoryFragment(String username);
}
