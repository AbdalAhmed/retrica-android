package com.fobid.retrica.ui.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.fobid.retrica.R;
import com.fobid.retrica.libs.qualifiers.RequiresPresenter;
import com.fobid.retrica.ui.adapters.MainAdapter;
import com.fobid.retrica.ui.presenters.MainPresenter;
import com.fobid.retrica.ui.views.MainView;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        ListView listView = (ListView) findViewById(R.id.list_view);
        MainAdapter adapter = new MainAdapter(this);
        listView.setAdapter(adapter);
    }
}
