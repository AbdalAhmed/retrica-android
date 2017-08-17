package com.fobid.retrica.ui.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fobid.retrica.R;
import com.fobid.retrica.models.GitHubUser;
import com.fobid.retrica.ui.viewholders.GitHubViewHolder;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class GitHubAdapter extends RealmRecyclerViewAdapter<GitHubUser, RecyclerView.ViewHolder> {

    private final Delegate delegate;

    public GitHubAdapter(@Nullable OrderedRealmCollection<GitHubUser> data, final @NonNull Delegate delegate) {
        super(data, true, true);
        this.delegate = delegate;
    }

    public interface Delegate extends GitHubViewHolder.Delegate {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GitHubViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_github, parent, false), delegate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GitHubViewHolder) holder).bindData(getData().get(position));
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }
}
