package com.example.bloold.githubapp.View;

import com.example.bloold.githubapp.Presenter.PresenterImpl;
import com.example.bloold.githubapp.domain.Repository;
import com.example.bloold.githubapp.domain.User;

import java.util.List;

/**
 * Created by bloold on 18.08.17.
 */

public interface MainView {

    PresenterImpl getPresenter();

    //refresh view with users
    void updateUsers();

    //refresh view with users
    void updateRepositories(List<Repository> repositories);

    void startRepositoryFragment(String username);
}
