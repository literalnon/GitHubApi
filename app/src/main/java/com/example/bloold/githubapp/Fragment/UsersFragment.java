package com.example.bloold.githubapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloold.githubapp.Adapters.UsersRecyclerViewAdapter;
import com.example.bloold.githubapp.View.DependView;
import com.example.bloold.githubapp.View.MainView;
import com.example.bloold.githubapp.R;
import com.example.bloold.githubapp.domain.Repository;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class UsersFragment extends Fragment implements DependView{

    private MainView customView;
    private RecyclerView recyclerView;
    private UsersRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UsersFragment() {}

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);

            recyclerView.setLayoutManager(linearLayoutManager);
            adapter = new UsersRecyclerViewAdapter(customView.getPresenter(), context);
            recyclerView.setAdapter(adapter);
        }

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
        adapter.updateView();
    }

    @Override
    public void updateView(List<Repository> repositories) {

    }
}
