package com.project.me.near.sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2016-01-02.
 */
public class CustomAdapter extends BaseAdapter {

    Context mContext;

    ArrayList<Gridata> data;

    public CustomAdapter(Context context, ArrayList<Gridata> data){

        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView=inflater.inflate(R.layout.list_item_places, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.data =(TextView) convertView.findViewById(R.id.textViewData);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Gridata gridata = data.get(position);
        viewHolder.image.setImageResource(gridata.getImage());
        viewHolder.data.setText(gridata.getData());

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView data;
    }
}
