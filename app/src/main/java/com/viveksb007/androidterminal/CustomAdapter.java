package com.viveksb007.androidterminal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by viveksb007 on 3/25/16.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<String> cmdsList;
    Context context;
    private static LayoutInflater lf;

    public CustomAdapter(Terminal terminal,ArrayList cmds)
    {
        cmdsList = cmds;
        context = terminal;
        lf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cmdsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = lf.inflate(R.layout.cmdlayout,null);
        TextView tempTV;
        tempTV = (TextView)rowView.findViewById(R.id.cmdID);
        tempTV.setText(cmdsList.get(position));
        return rowView;
    }
}
