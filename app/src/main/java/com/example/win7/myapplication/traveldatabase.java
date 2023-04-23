package com.example.win7.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class traveldatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "starbuzz";
    public static final int DB_VERSION = 1;

    traveldatabase(Context context) {

        super(context, DB_NAME, null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE EMERGENCY(_id INTEGER PRIMARY KEY AUTOINCREMENT" +"" + ",NAME TEXT,EMAIL TEXT,MOBILENUMBER BIGINT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void insertDrink(SQLiteDatabase db, String name, String email, long number) {
        ContentValues content = new ContentValues();
        content.put("NAME", name);
        content.put("MOBILENUMBER", number);
        content.put("EMAIL",email);
        db.insert("EMERGENCY", null, content);
    }
}
