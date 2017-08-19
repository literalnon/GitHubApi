package com.example.bloold.githubapp.View;

import com.example.bloold.githubapp.domain.Repository;

import java.util.List;

/**
 * Created by bloold on 18.08.17.
 */

public interface DependView {
    void updateView();
    void updateView(List<Repository> repositories);
}
