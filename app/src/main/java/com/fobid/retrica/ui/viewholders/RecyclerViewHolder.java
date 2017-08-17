package com.fobid.retrica.ui.viewholders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fobid.retrica.R;
import com.fobid.retrica.ui.widgets.ChipTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.chip)
    ChipTextView chipTextView;

    public RecyclerViewHolder(final @NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("DefaultLocale")
    public void bindData(final int position) {
        chipTextView.setText(String.format("position: %d", position));

        if (position % 2 == 0) {
            chipTextView.setBackgroundColor(Color.LTGRAY);
            chipTextView.setTextColor(Color.BLACK);
        } else {
            chipTextView.setBackgroundColor(Color.BLUE);
            chipTextView.setTextColor(Color.WHITE);
        }
    }
}
