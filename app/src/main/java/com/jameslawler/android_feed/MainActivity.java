package com.jameslawler.android_feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding.view.RxView;
import com.jameslawler.android_feed.adapter.RssItemAdapter;
import com.jameslawler.android_feed.model.RssItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView feed;
    private RssItemAdapter rssItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        feed = (RecyclerView) findViewById(R.id.feed);
        feed.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        feed.setLayoutManager(layoutManager);

        rssItemAdapter = new RssItemAdapter(createList("First", 30));
        feed.setAdapter(rssItemAdapter);

        RxView.clicks(findViewById(R.id.change))
                .subscribe(l -> changeList());
    }

    private void changeList() {
        Integer prefix = 100 + (int)(Math.random()* 999);
        rssItemAdapter.rssItems = createList(prefix.toString(), 30);
        rssItemAdapter.notifyDataSetChanged();
        feed.scrollToPosition(0);
    }

    private List<RssItem> createList(String prefix, int size) {

        List<RssItem> result = new ArrayList<RssItem>();
        for (int i=1; i <= size; i++) {
            RssItem rssItem = new RssItem();
            rssItem.name = prefix + i;

            result.add(rssItem);
        }

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
