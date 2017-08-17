package com.fobid.retrica.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fobid.retrica.R;
import com.fobid.retrica.libs.qualifiers.RequiresPresenter;
import com.fobid.retrica.ui.adapters.RecyclerViewAdapter;
import com.fobid.retrica.ui.presenters.RecyclerViewPresenter;
import com.fobid.retrica.ui.views.RecyclerViewView;

import butterknife.BindView;

/**
 * Created by android01 on 2017. 8. 17..
 */

@RequiresPresenter(RecyclerViewPresenter.class)
public class RecyclerViewActivity extends BaseActivity<RecyclerViewPresenter> implements RecyclerViewView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_recycler_view);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.a_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.list_view:
                startListViewActivity();
                return true;
            case R.id.recycler_view:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startListViewActivity() {
        final Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
        back();
    }
}
