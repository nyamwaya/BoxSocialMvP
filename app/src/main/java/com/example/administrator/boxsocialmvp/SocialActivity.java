package com.example.administrator.boxsocialmvp;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.boxsocialmvp.Adapters.SocialAdapter;

/**
 * Created by Administrator on 2/27/2015.
 */
public class SocialActivity extends Activity {

    private RecyclerView recyclerview;
    private SocialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_view);
        recyclerview = (RecyclerView) findViewById(R.id.social_list);
        TvCard show = (TvCard)getIntent().getExtras().getSerializable("show");
        TextView title = (TextView)findViewById(R.id.show_name);
        title.setText(show.showTitle);
        adapter = new SocialAdapter(this,show);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
