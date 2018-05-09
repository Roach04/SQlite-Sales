package com.project.me.near.sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2016-06-09.
 */
public class CustomAdapterData extends BaseAdapter{

    Context mContext;

    ArrayList<Dbase> data;

    public CustomAdapterData(Context context, ArrayList<Dbase> data){

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
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView=inflater.inflate(R.layout.list_item_layout, parent, false);
            viewHolder = new ViewHolder();

            /*viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView);*/
            viewHolder.name =(TextView) convertView.findViewById(R.id.textViewNames);
            viewHolder.mail =(TextView) convertView.findViewById(R.id.textViewEmail);
            viewHolder.tell =(TextView) convertView.findViewById(R.id.textViewPhone);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Dbase dbase = data.get(position);
        /*viewHolder.image.setImageResource(dbase.getImage());*/
        viewHolder.name.setText(dbase.getNames());
        viewHolder.mail.setText(dbase.getEmail());
        viewHolder.tell.setText(dbase.getPhone());

        return convertView;
    }

    static class ViewHolder {
        /*ImageView image;*/
        TextView name;
        TextView mail;
        TextView tell;
    }
}
