package com.example.bloold.githubapp.Presenter;

import com.example.bloold.githubapp.Model.Model;
import com.example.bloold.githubapp.service.ServiceGenerator;
import com.example.bloold.githubapp.View.MainView;
import com.example.bloold.githubapp.domain.Repository;
import com.example.bloold.githubapp.domain.User;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bloold on 18.08.17.
 */

public class PresenterImpl extends Presenter {

    public PresenterImpl(Model model, MainView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public List<User> getUsers() {
        return model.getUsers();
    }

    @Override
    public void updateUsers(){
        Observable<List<User>> usersListObservable = ServiceGenerator.getUsersService().getUsersByName();

        usersListObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    for(User u : users){
                        Observable<User> userObservable = ServiceGenerator.getUserService().getUserByName(u.getLogin());
                        userObservable.subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(user ->{
                                    u.setReposCount(user.getReposCount());
                                    model.updateUser(user);
                                    view.updateUsers();
                                });
                    }
                    model.updateUsers(users);
                    view.updateUsers();
                });
    }

    @Override
    public void updateRepository(String username){
        Observable<List<Repository>> reposListObservable = ServiceGenerator.getRepositoryService().getRepositories(username);

        reposListObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                    view.updateRepositories(repositories);
                });
    }

    @Override
    public void startRepositoryFragment(String username) {
        view.startRepositoryFragment(username);
    }
}
