package com.example.win7.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Statedatabase extends SQLiteOpenHelper {
    private static final String DB_NAME="States";
    private static final int DB_ver=1;

    Statedatabase(Context context){
        super(context,DB_NAME,null,DB_ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STATES(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "STATE TEXT);");


        insertDrink(db,"Most popular Treks");
        insertDrink(db,"Maharashtra");
        insertDrink(db,"Kerala");
        insertDrink(db,"Karnataka");
        insertDrink(db,"Himachal Pradesh");
        insertDrink(db,"Uttarakhand");
        insertDrink(db,"Rajasthan");
        insertDrink(db,"Madhya Pradesh");
        insertDrink(db,"Odisha");
        insertDrink(db,"Gujarat");





    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertDrink(SQLiteDatabase db,String state){
        ContentValues cv=new ContentValues();
        cv.put("STATE",state);
        db.insert("STATES",null,cv);
    }




}
