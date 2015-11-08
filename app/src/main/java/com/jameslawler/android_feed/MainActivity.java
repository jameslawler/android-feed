package com.jameslawler.android_feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.jameslawler.android_feed.adapter.RssItemAdapter;
import com.jameslawler.android_feed.data.DbModule;
import com.jameslawler.android_feed.data.DbOpenHelper;
import com.jameslawler.android_feed.model.RssItem;
import com.jameslawler.library.HttpRx;
import com.jameslawler.library.OpenGraphParser;
import com.jameslawler.library.RssParser;
import com.jameslawler.library.storio.entities.Channel;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
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

        rssItemAdapter = new RssItemAdapter(MainActivity.this, createList("DW Top Thema", 10));
        feed.setAdapter(rssItemAdapter);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        RssParser rssParser = new RssParser();
        OpenGraphParser openGraphParser = new OpenGraphParser();


//        StorIOSQLite storIOSQLite = DbModule.provideStorIOSQLite(
//                DbModule.provideSQLiteOpenHelper(this.getApplicationContext())
//        );
//
//        storIOSQLite
//                .put()
//                .object(new Channel((long)123, "My Title", "My Desc"))
//                .prepare()
//                .createObservable()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(x -> Log.v("STUDYFEED", "Data Saved"));


        Observable
                .just("http://rss.dw.com/xml/DKpodcast_topthemamitvokabeln_de")
                .flatMap(x -> HttpRx.GetObservable(x))
                .flatMap(x -> RssParser.ParseObservable(x))
                .flatMapIterable(x -> x.channel.itemList)
                .limit(3)
                .flatMap(x -> HttpRx.GetObservable(x.link))
                .flatMap(x -> OpenGraphParser.ParseObservable(x))
                .filter(x -> x.getProperty().equalsIgnoreCase("og:image"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        x -> System.out.println(x.getContent()));



//        Observable
//                .just("http://rss.dw.com/xml/DKpodcast_topthemamitvokabeln_de")
//                .flatMap(x -> rssParser.ParseObservable(x))
//                .flatMap(x -> openGraphParser.ParseObservable(x))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(success -> Log.v("STUDYFEED2", success),
//                        error -> Log.v("STUDYFEED3", error.toString()));


    }

    private void changeList() {
        Integer prefix = 100 + (int)(Math.random()* 999);
        rssItemAdapter.rssItems = createList(prefix.toString(), 30);
        rssItemAdapter.notifyDataSetChanged();
        feed.scrollToPosition(0);
    }

    private List<RssItem> createList(String prefix, int size) {

        List<RssItem> result = new ArrayList<RssItem>();

        result.add(new RssItem("Flüchtlinge auf dem deutschen Arbeitsmarkt", "Viele Betriebe in Deutschland suchen Lehrlinge, viele Flüchtlinge suchen Arbeit. Trotzdem können Flüchtlinge oft nicht in Betrieben arbeiten. Für die schlechte Integration auf dem Arbeitsmarkt gibt es viele Gründe.", "1 hour ago", R.drawable.test));
        result.add(new RssItem("Demonstration gegen TTIP", "Die USA und die EU verhandeln gerade über das Freihandelsabkommen TTIP. 150.000 Menschen haben in Berlin gegen das Abkommen demonstriert. Sie sehen darin große Gefahren. Für die Wirtschaft aber überwiegen die Vorteile.", "1 hour ago", R.drawable.test2));
        result.add(new RssItem("Mehr Datenschutz für Europa", "Wegen eines Abkommens zwischen Europa und den USA konnten Unternehmen bisher persönliche Daten von EU-Bürgern einfach in die USA weitergeben. Der Europäische Gerichtshof erklärte dieses Abkommen nun für ungültig.", "1 hour ago", R.drawable.test3));
        result.add(new RssItem("Sichere Taxis für Frauen in Ägypten", "Fast jede Frau in Ägypten wurde schon mal sexuell belästigt. Reem Fawzy hat in Kairo das Projekt „Pink Taxi“ gestartet. Es ist ein Taxi-Service von Frauen für Frauen. So können sie sich sicher durch die Stadt bewegen.", "1 hour ago", R.drawable.test4));

        result.add(new RssItem("Flüchtlinge auf dem deutschen Arbeitsmarkt", "Viele Betriebe in Deutschland suchen Lehrlinge, viele Flüchtlinge suchen Arbeit. Trotzdem können Flüchtlinge oft nicht in Betrieben arbeiten. Für die schlechte Integration auf dem Arbeitsmarkt gibt es viele Gründe.", "1 hour ago", R.drawable.test));
        result.add(new RssItem("Demonstration gegen TTIP", "Die USA und die EU verhandeln gerade über das Freihandelsabkommen TTIP. 150.000 Menschen haben in Berlin gegen das Abkommen demonstriert. Sie sehen darin große Gefahren. Für die Wirtschaft aber überwiegen die Vorteile.", "1 hour ago", R.drawable.test2));
        result.add(new RssItem("Mehr Datenschutz für Europa", "Wegen eines Abkommens zwischen Europa und den USA konnten Unternehmen bisher persönliche Daten von EU-Bürgern einfach in die USA weitergeben. Der Europäische Gerichtshof erklärte dieses Abkommen nun für ungültig.", "1 hour ago", R.drawable.test3));
        result.add(new RssItem("Sichere Taxis für Frauen in Ägypten", "Fast jede Frau in Ägypten wurde schon mal sexuell belästigt. Reem Fawzy hat in Kairo das Projekt „Pink Taxi“ gestartet. Es ist ein Taxi-Service von Frauen für Frauen. So können sie sich sicher durch die Stadt bewegen.", "1 hour ago", R.drawable.test4));

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
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
