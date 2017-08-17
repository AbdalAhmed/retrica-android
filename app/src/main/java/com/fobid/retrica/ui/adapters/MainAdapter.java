package com.fobid.retrica.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fobid.retrica.R;
import com.fobid.retrica.ui.viewholders.MainViewHolder;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class MainAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    public MainAdapter(final @NonNull Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MainViewHolder viewHolder;

        if (view != null) {
            viewHolder = (MainViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.i_main, viewGroup, false);

            viewHolder = new MainViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.bindData(i);
        return view;
    }
}
