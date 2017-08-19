package com.example.bloold.githubapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.bloold.githubapp.Presenter.Presenter;
import com.example.bloold.githubapp.R;
import com.example.bloold.githubapp.View.DependView;
import com.example.bloold.githubapp.domain.Repository;
import com.example.bloold.githubapp.domain.User;

import java.util.List;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder> implements DependView{

    private List<User> users;
    private Presenter presenter;
    private Context context;

    public UsersRecyclerViewAdapter(Presenter presenter, Context context) {
        this.users = presenter.getUsers();
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_users, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCountRepository.setText(String.valueOf(users.get(position).getReposCount()));
        holder.mUserName.setText(users.get(position).getLogin());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startRepositoryFragment(users.get(position).getLogin());
            }
        });

        //download user avatar
        Glide
                .with(context)
                .load(users.get(position).getAvatar())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mUserAvatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void updateView() {
        users = presenter.getUsers();
        notifyDataSetChanged();

    }

    @Override
    public void updateView(List<Repository> repositories) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mUserName;
        public final ImageView mUserAvatar;
        public final TextView mCountRepository;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mUserName= (TextView) view.findViewById(R.id.name);
            mCountRepository= (TextView) view.findViewById(R.id.count_repo);
            mUserAvatar = (ImageView)view.findViewById(R.id.user_avatar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mUserName.getText() + "'";
        }
    }
}
