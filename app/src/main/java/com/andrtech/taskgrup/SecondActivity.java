package com.andrtech.taskgrup;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;

    DatabaseHelper myDb;
    ArrayList<String> dateList;
    ArrayList<MyPojo> theList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView=findViewById(R.id.listView);
        myDb=new DatabaseHelper(this);
        theList=new ArrayList<>();
        dateList=new ArrayList<>();
        Cursor data=myDb.getListContents();

        if(data.getCount()==0){
            //  Toast.makeText(getApplicationContext(),"the database was empty",Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()){

                retrieveData();

            }}

    }


    public void retrieveData(){
        theList.clear();
        myDb.openDB();


        Cursor c=myDb.getListContents();
        while (c.moveToNext())
        {
            String url=c.getString(0);
            MyPojo myPojo=new MyPojo();
            myPojo.setMessage(url);
            theList.add(myPojo);

            String str2=c.getString(1);
            dateList.add(str2);

            MyAdapter myAdapter=new MyAdapter(getApplicationContext(),theList,dateList);
            listView.setAdapter(myAdapter);

        }

        myDb.closeDB();


    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

}

