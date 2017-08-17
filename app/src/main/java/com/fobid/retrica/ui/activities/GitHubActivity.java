package com.fobid.retrica.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fobid.retrica.R;
import com.fobid.retrica.libs.qualifiers.RequiresPresenter;
import com.fobid.retrica.models.GitHubUser;
import com.fobid.retrica.ui.adapters.GitHubAdapter;
import com.fobid.retrica.ui.presenters.GitHubPresenter;
import com.fobid.retrica.ui.views.GitHubView;
import com.tfc.webviewer.ui.WebViewerActivity;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by android01 on 2017. 8. 17..
 */

@RequiresPresenter(GitHubPresenter.class)
public class GitHubActivity extends BaseActivity<GitHubPresenter> implements GitHubView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_github);
        realm = Realm.getDefaultInstance();
        final RealmResults<GitHubUser> users = realm.where(GitHubUser.class)
                .findAll();
        final GitHubAdapter adapter = new GitHubAdapter(users, presenter);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> presenter.syncClick());

        presenter.showWebViewer()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(this::startWebViewerActivity);

        presenter.showLoading()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(__ -> showLoading());

        presenter.hideLoading()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(__ -> hideLoading());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.a_github, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.sync:
                presenter.syncClick();
                return true;
            case R.id.list_view:
                startListViewActivity();
                return true;
            case R.id.recycler_view:
                startRecyclerViewActivity();
                return true;
            case R.id.github:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    @Override
    public void startListViewActivity() {
        final Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
        back();
    }

    @Override
    public void startRecyclerViewActivity() {
        final Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
        back();
    }

    @Override
    public void startWebViewerActivity(@NonNull String url) {
        final Intent intent = new Intent(this, WebViewerActivity.class);
        intent.putExtra(WebViewerActivity.EXTRA_URL, url);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }
}
