package com.anaadar.akaltakhatsahibpro.activities.news.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anaadar.akaltakhatsahibpro.R;
import com.anaadar.akaltakhatsahibpro.Webview.Contact_web;
import com.anaadar.akaltakhatsahibpro.activities.news.Model.DataModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    static Typeface custom_font;
    static Context con;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, desc, postedby;
        ImageView image;
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            con = itemView.getContext();
            custom_font = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/amrlipi_.ttf");
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.desc = (TextView) itemView.findViewById(R.id.desc);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.postedby = (TextView) itemView.findViewById(R.id.postedBy);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.layout = (LinearLayout) itemView.findViewById(R.id.card_layout);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        try {
            TextView textitle = holder.title;
            TextView textdate = holder.date;
            TextView textdesc = holder.desc;
            TextView textpostedby = holder.postedby;
            ImageView image = holder.image;
            Glide.with(con)
                    .load(dataSet.get(listPosition).getImageurl())
                    .override(300, 200)
                    .into(image);
            //Glide Code
            // image.setImageResource(dataSet.get(listPosition).getImageUrl());
            LinearLayout layout = holder.layout;
            textitle.setText(dataSet.get(listPosition).getTitle());
            textitle.setTypeface(custom_font);
            textdesc.setText(dataSet.get(listPosition).getDesc());
            textdesc.setTypeface(custom_font);
            textdate.setText(dataSet.get(listPosition).getDate());
            textpostedby.setText(dataSet.get(listPosition).getPostedBy());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent b = new Intent(con, Contact_web.class);
                    b.putExtra("Message", dataSet.get(listPosition).getUrl());
                    con.startActivity(b);
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}