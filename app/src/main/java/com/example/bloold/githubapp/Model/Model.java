package com.example.bloold.githubapp.Model;

import com.example.bloold.githubapp.domain.Repository;
import com.example.bloold.githubapp.domain.User;

import java.util.List;

/**
 * Created by bloold on 18.08.17.
 */

public interface Model {

    void updateUsers(List<User> users);
    void updateUser(User user);

    List<User> getUsers();
}
