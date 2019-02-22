package com.andrtech.taskgrup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String DATABASE_NAME="doglist.db";
    public static  final String TABLE_NAME="mydoglist_data";
    public static  final String COL1="dogimage";
    public static  final String COL2="time_added";

    SQLiteDatabase dbSql;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // String createTable="CREATE TABLE "+ TABLE_NAME + " (" +COL1 + " BLOB, "+ COL2 + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)";

        String createTable="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL1 + " BLOB, " +
                COL2 + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +");";




        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create new table
        onCreate(db);
    }

    public DatabaseHelper openDB()
    {
        try {
            dbSql=this.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this ;
    }


    public void closeDB()
    {
        try {
            this.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean addData(String item1){
        SQLiteDatabase db=this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String dayOfTheWeek = (String) DateFormat.format("EEE", date); // Thursday
        String day          = (String) DateFormat.format("dd",   date); // 20
        String monthString  = (String) DateFormat.format("MMM",  date); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   date); // 06
        String year         = (String) DateFormat.format("yyyy", date); // 2013

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,item1);
        //   contentValues.put(COL2,dateFormat.format(date));

        contentValues.put(COL2,dayOfTheWeek+" "+monthString+" "+day+" "+year+" "+dateFormat.format(date));
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return  false;
        }
        else {
            return true;
        }

    }


    public  void  deleteTable(){

        SQLiteDatabase db = this.getWritableDatabase(); //get database
        db.execSQL("DELETE FROM "+ TABLE_NAME); //delete all rows in a table
        db.close();


    }
    public Cursor getListContents(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return data;
    }
}

