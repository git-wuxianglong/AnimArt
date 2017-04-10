package com.wuxl.animart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wuxl.animart.R;
import com.wuxl.animart.view.SearchAnimView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchAnimActivity extends AppCompatActivity {

    @Bind(R.id.btn_search)
    Button btnSearch;
    @Bind(R.id.searchView)
    SearchAnimView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_anim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        searchView.start();
    }
}
