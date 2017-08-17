package com.fobid.retrica.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.fobid.retrica.R;
import com.fobid.retrica.libs.qualifiers.RequiresPresenter;
import com.fobid.retrica.ui.adapters.ListViewAdapter;
import com.fobid.retrica.ui.presenters.ListViewPresenter;
import com.fobid.retrica.ui.views.ListViewView;

@RequiresPresenter(ListViewPresenter.class)
public class ListViewActivity extends BaseActivity<ListViewPresenter> implements ListViewView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_list_view);

        final ListView listView = (ListView) findViewById(R.id.list_view);
        final ListViewAdapter adapter = new ListViewAdapter(this);
        listView.setAdapter(adapter);
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
                return true;
            case R.id.recycler_view:
                startRecyclerViewActivity();
                return true;
            case R.id.github:
                startGitHubActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startRecyclerViewActivity() {
        final Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
        back();
    }

    @Override
    public void startGitHubActivity() {
        final Intent intent = new Intent(this, GitHubActivity.class);
        startActivity(intent);
        back();
    }
}
