package com.example.bloold.githubapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloold.githubapp.Adapters.RepositoriesRecyclerViewAdapter;
import com.example.bloold.githubapp.Adapters.UsersRecyclerViewAdapter;
import com.example.bloold.githubapp.R;
import com.example.bloold.githubapp.View.DependView;
import com.example.bloold.githubapp.View.MainView;
import com.example.bloold.githubapp.domain.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RepositoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoriesFragment extends Fragment implements DependView{

    private static final String USERNAME_KEY = "username";

    private static String username;

    private MainView customView;
    private RecyclerView recyclerView;


    public RepositoriesFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment RepositoriesFragment.
     */
    public static RepositoriesFragment newInstance(String username) {
        RepositoriesFragment fragment = new RepositoriesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME_KEY, username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            username = getArguments().getString(USERNAME_KEY);
        }else{
            Log.d("err ", "getArgs null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repositories_list, container, false);

        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new RepositoriesRecyclerViewAdapter(new ArrayList<Repository>()));
        }

        customView.getPresenter().updateRepository(username);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        customView = ((MainView)context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void updateView() {

    }

    @Override
    public void updateView(List<Repository> repositories) {
        recyclerView.setAdapter(new RepositoriesRecyclerViewAdapter(repositories));
    }
}
