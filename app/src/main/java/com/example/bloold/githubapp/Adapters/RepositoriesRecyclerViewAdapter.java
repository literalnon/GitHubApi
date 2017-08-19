package com.example.bloold.githubapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bloold.githubapp.R;
import com.example.bloold.githubapp.View.DependView;
import com.example.bloold.githubapp.domain.Repository;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by bloold on 18.08.17.
 */

public class RepositoriesRecyclerViewAdapter extends RecyclerView.Adapter<RepositoriesRecyclerViewAdapter.ViewHolder> implements DependView {
    List<Repository> repositories;

    public RepositoriesRecyclerViewAdapter(List<Repository> repositories){
        this.repositories = repositories;
    }

    @Override
    public RepositoriesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_repositories, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoriesRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mReposName.setText(repositories.get(position).getName());

        holder.mDateCreated.setText(new SimpleDateFormat("dd.MM.yyyy")
                .format(DateTimeUtils.toDate(Instant.parse(repositories.get(position).getCreated_date()))));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    @Override
    public void updateView() {

    }

    @Override
    public void updateView(List<Repository> repositories) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mReposName;
        public final TextView mDateCreated;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mReposName = (TextView) view.findViewById(R.id.repos_name);
            mDateCreated = (TextView) view.findViewById(R.id.repos_date_created);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mReposName.getText() + "'";
        }
    }
}
