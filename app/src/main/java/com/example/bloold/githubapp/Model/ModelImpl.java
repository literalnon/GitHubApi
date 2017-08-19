package com.example.bloold.githubapp.Model;

import android.text.TextUtils;

import com.example.bloold.githubapp.Model.Model;
import com.example.bloold.githubapp.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bloold on 17.08.17.
 */

public class ModelImpl implements Model {
    private List<User> users;

    public ModelImpl(){
        users = new ArrayList<>();
    }

    @Override
    public List<User> getUsers(){
        return users;
    }

    @Override
    public void updateUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public void updateUser(User user) {
        for(User u : users){
            if(TextUtils.equals(u.getLogin(), user.getLogin())){
                u.setReposCount(user.getReposCount());
                return;
            }
        }

    }
}
