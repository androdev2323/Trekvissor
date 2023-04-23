package com.example.win7.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bookingdatabase  extends SQLiteOpenHelper {
    public static final String DB_NAME = "booking";
    public static final int DB_VERSION = 1;

    bookingdatabase(Context context) {

      super(context,DB_NAME,null,DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE BOOKING(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "TREKNNAME TEXT,"
                + " TREKDATE TEXT,"
                + " COMPANIONS TEXT,"
                + "  EMAIL TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void insertDrink(SQLiteDatabase db, String trekname, String Trekdate, String companions,String email) {
        ContentValues content = new ContentValues();
        content.put("NAME", trekname);
        content.put("TREKDATE",Trekdate);
        content.put("COMPANIONS",companions);
        content.put("EMAIL",email);
        db.insert("booking", null, content);
    }
}
