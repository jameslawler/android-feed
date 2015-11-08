package com.jameslawler.android_feed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jameslawler.android_feed.R;
import com.jameslawler.android_feed.model.RssItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RssItemAdapter extends RecyclerView.Adapter<RssItemAdapter.RssItemViewHolder> {

    private Context context;
    public List<RssItem> rssItems;

    public RssItemAdapter(Context context, List<RssItem> rssItems) {
        this.context = context;
        this.rssItems = rssItems;
    }

    @Override
    public int getItemCount() {
        return rssItems.size();
    }

    @Override
    public void onBindViewHolder(RssItemViewHolder rssItemViewHolder, int index) {
        RssItem rssItem = rssItems.get(index);

        rssItemViewHolder.name.setText(rssItem.name);
        rssItemViewHolder.description.setText(rssItem.description);
        rssItemViewHolder.publishDate.setText(rssItem.publishDate);

        Picasso
                .with(context)
                .load("http://www.dw.com/image/0,,15944905_302,00.jpg")
                .fit()
                .into(rssItemViewHolder.image);

        rssItemViewHolder.channel.setImageResource(R.drawable.channel);
    }

    @Override
    public RssItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.rss_item, viewGroup, false);

        return new RssItemViewHolder(itemView);
    }

    public static class RssItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView description;
        protected TextView publishDate;
        protected ImageView image;
        protected ImageView channel;

        public RssItemViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            description = (TextView) v.findViewById(R.id.description);
            publishDate = (TextView) v.findViewById(R.id.publishDate);
            image = (ImageView) v.findViewById(R.id.profile);
            channel = (ImageView) v.findViewById(R.id.channel_picture);
        }
    }
}
