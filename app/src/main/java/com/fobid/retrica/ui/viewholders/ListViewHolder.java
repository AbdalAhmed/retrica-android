package com.fobid.retrica.ui.viewholders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import com.fobid.retrica.R;
import com.fobid.retrica.ui.widgets.ChipTextView;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class ListViewHolder {

    private ChipTextView chipTextView;

    public ListViewHolder(final @NonNull View itemView) {
        chipTextView = itemView.findViewById(R.id.chip);
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
