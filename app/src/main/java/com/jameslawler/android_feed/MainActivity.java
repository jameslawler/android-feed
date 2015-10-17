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

        rssItemAdapter = new RssItemAdapter(createList("DW Top Thema", 10));
        feed.setAdapter(rssItemAdapter);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
