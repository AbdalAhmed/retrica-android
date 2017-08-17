package com.fobid.retrica.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fobid.retrica.R;
import com.fobid.retrica.ui.viewholders.RecyclerViewHolder;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.i_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecyclerViewHolder)holder).bindData(position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
