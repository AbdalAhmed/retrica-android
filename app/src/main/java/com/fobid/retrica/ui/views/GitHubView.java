package com.fobid.retrica.ui.views;

import android.support.annotation.NonNull;

/**
 * Created by android01 on 2017. 8. 17..
 */

public interface GitHubView extends BaseView {

    void startListViewActivity();

    void startRecyclerViewActivity();

    void startWebViewerActivity(@NonNull String url);

    void showLoading();

    void hideLoading();
}
