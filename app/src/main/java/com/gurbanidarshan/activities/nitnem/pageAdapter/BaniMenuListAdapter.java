package com.gurbanidarshan.activities.nitnem.pageAdapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gurbanidarshan.R;

import java.util.ArrayList;

/**
 * Created by Sumit Singh on 3/13/2018.
 */

public class BaniMenuListAdapter extends ArrayAdapter<String> {
    ArrayList<String> baniList = new ArrayList<>();
    Context con;
    TextView textView;
    LayoutInflater inflater;
    View v;

    public BaniMenuListAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        baniList = objects;
        con = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            v = convertView;
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.banimenucustomlayout, null);
            textView = (TextView) v.findViewById(R.id.lists_text_style);
        }
        Typeface custom_font = Typeface.createFromAsset(con.getAssets(), "fonts/amrlipi_.ttf");
        textView.setText(baniList.get(position));
        textView.setTypeface(custom_font);
        return v;
    }
}