package com.andrtech.taskgrup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHolder {
    TextView nameTxt;
    ImageView img;

    public MyHolder(View v) {

        this.nameTxt= (TextView) v.findViewById(R.id.timeId);
        this.img= (ImageView) v.findViewById(R.id.imageList);

    }



}