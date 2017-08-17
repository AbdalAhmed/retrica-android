package com.fobid.retrica.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fobid.retrica.R;
import com.fobid.retrica.ui.viewholders.ListViewHolder;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ListViewAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    public ListViewAdapter(final @NonNull Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 100;
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
        final ListViewHolder viewHolder;

        if (view != null) {
            viewHolder = (ListViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.i_main, viewGroup, false);

            viewHolder = new ListViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.bindData(i);
        return view;
    }
}
