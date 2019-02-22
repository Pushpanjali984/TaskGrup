package com.andrtech.taskgrup;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<MyPojo> theList;
    ArrayList<String> dateList;
    LayoutInflater inflater;


    public MyAdapter(Context context, ArrayList<MyPojo> theList,ArrayList<String> dateList) {
        this.context = context;
        this.theList = theList;
        this.dateList=dateList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return this.theList.size();
    }

    @Override
    public Object getItem(int position) {
        return theList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row, parent, false);
        }

        //BIND DATA

        MyHolder holder=new MyHolder(convertView);
        PicassoClient.loadImage(context,theList.get(position).getMessage(),holder.img);
        holder.nameTxt.setText(dateList.get(position));


        return  convertView;
    }
}
