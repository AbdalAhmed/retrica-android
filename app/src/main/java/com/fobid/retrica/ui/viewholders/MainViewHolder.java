package com.fobid.retrica.ui.viewholders;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import com.fobid.retrica.R;
import com.fobid.retrica.ui.widgets.ChipTextView;

/**
 * Created by android01 on 2017. 8. 17..
 */

public class MainViewHolder {

    private ChipTextView chipTextView;

    public MainViewHolder(final @NonNull View itemView) {
        chipTextView = itemView.findViewById(R.id.chip);
    }

    public void bindData(final int position) {
        if (position % 2 == 0) {
            chipTextView.setBackgroundColor(Color.LTGRAY);
        } else {
            chipTextView.setTextColor(Color.WHITE);
        }
    }
}
