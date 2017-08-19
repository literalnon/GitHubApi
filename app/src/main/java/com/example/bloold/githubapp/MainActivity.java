package com.example.bloold.githubapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bloold.githubapp.Fragment.RepositoriesFragment;
import com.example.bloold.githubapp.Fragment.UsersFragment;
import com.example.bloold.githubapp.Model.ModelImpl;
import com.example.bloold.githubapp.Presenter.PresenterImpl;
import com.example.bloold.githubapp.View.DependView;
import com.example.bloold.githubapp.View.MainView;
import com.example.bloold.githubapp.domain.Repository;
import com.example.bloold.githubapp.domain.User;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private PresenterImpl presenter;
    private UsersFragment usersFragment;
    private RepositoriesFragment repositoryFragment;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);

        setContentView(R.layout.activity_users_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.good_luck, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        if(isOnline()) {
            presenter = new PresenterImpl(new ModelImpl(), this);
            presenter.updateUsers();

            usersFragment = UsersFragment.newInstance();

            getSupportFragmentManager().beginTransaction().add(R.id.main_container, usersFragment).commit();
        }else{
            Toast.makeText(this, R.string.not_online, Toast.LENGTH_LONG).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_users_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_job) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public PresenterImpl getPresenter() {
        return presenter;
    }

    @Override
    public void updateUsers() {
        progressBar.setVisibility(View.INVISIBLE);
        ((DependView)usersFragment).updateView();
    }

    @Override
    public void updateRepositories(List<Repository> repositories) {
        progressBar.setVisibility(View.INVISIBLE);
        if(repositoryFragment != null)
            ((DependView)repositoryFragment).updateView(repositories);
    }

    @Override
    public void startRepositoryFragment(String username) {
        progressBar.setVisibility(View.VISIBLE);
        repositoryFragment = RepositoriesFragment.newInstance(username);
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, repositoryFragment).hide(usersFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(usersFragment.isHidden()){
            getSupportFragmentManager().beginTransaction().show(usersFragment).remove(repositoryFragment).commit();
            repositoryFragment = null;
        }else{
            super.onBackPressed();
        }
    }
}
