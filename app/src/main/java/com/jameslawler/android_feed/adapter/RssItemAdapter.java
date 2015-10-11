package com.jameslawler.android_feed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jameslawler.android_feed.R;
import com.jameslawler.android_feed.model.RssItem;

import java.util.List;

public class RssItemAdapter extends RecyclerView.Adapter<RssItemAdapter.RssItemViewHolder> {

    public List<RssItem> rssItems;

    public RssItemAdapter(List<RssItem> rssItems) {
        this.rssItems = rssItems;
    }

    @Override
    public int getItemCount() {
        return rssItems.size();
    }

    @Override
    public void onBindViewHolder(RssItemViewHolder rssItemViewHolder, int index) {
        RssItem rssItem = rssItems.get(index);

        rssItemViewHolder.vName.setText(rssItem.name);
    }

    @Override
    public RssItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.rss_item, viewGroup, false);

        return new RssItemViewHolder(itemView);
    }

    public static class RssItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;

        public RssItemViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
        }
    }
}
