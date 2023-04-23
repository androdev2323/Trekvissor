package com.example.win7.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseimp extends SQLiteOpenHelper {
    private static final String DB_NAME="wwf";
    private static final int DB_ver=1;

    public databaseimp(Context context){
        super(context,DB_NAME,null,DB_ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"STATE TEXT,"
                +"TREKNAME TEXT,"
                +"DIFFICULTY TEXT,"
                +"TRAILTYPE TEXT,"
                +"BASECAMP TEXT,"
                +"TIME TEXT,"
                +"FEES INTEGER,"
                +"POPULAR INTEGER,"
                +"IMAGEID INTEGER);");


       insertDrink(db,"Maharashtra","HARIHAR FORT","HARD","NOT","YUP","50",90,0, R.drawable.harihar);
       insertDrink(db,"Maharashtra"," VISAPUR AND LOHAGAD","HARD","NOT","YUP","50",90, 0,R.drawable.lohagad);
        insertDrink(db,"Maharashtra","BHIMASHANKAR TREK","HARD","NOT","YUP","50",90, 0,R.drawable.bhimnashankar);
        insertDrink(db,"Maharashtra","KALSUBAI TREK","HARD","NOT","YUP","50",90,1, R.drawable.kalsubai);
        insertDrink(db,"Maharashtra","TORANA TREK","HARD","NOT","YUP","50",90,0, R.drawable.torna);
        insertDrink(db,"Maharashtra","RAIGAD FORT","HARD","NOT","YUP","50",90,1, R.drawable.raigadfort);
        insertDrink(db,"Gujarat","Saputara","HARD","NOT","YUP","50",90,0, R.drawable.satputra);
        insertDrink(db,"Gujarat","Polo Monuments","HARD","NOT","YUP","50",90,0, R.drawable.polo);
        insertDrink(db,"Himachal Pradesh","Malana Village Trek","HARD","NOT","YUP","50",90,0, R.drawable.malana);
        insertDrink(db,"Himachal Pradesh","Beas kund","HARD","NOT","YUP","50",90,0, R.drawable.beaskund);
        insertDrink(db,"Karnataka","KOPATTY COORG KERALA TREK","HARD","NOT","YUP","50",90,0, R.drawable.coorg);
        insertDrink(db,"Karnataka","Kodachadri","HARD","NOT","YUP","50",90,0, R.drawable.kodachadri);
        insertDrink(db,"Kerala","Agasthyarkoodam peak","HARD","NOT","YUP","50",90,0, R.drawable.agasthyarkoodam);
        insertDrink(db,"Kerala","Chembra peak","MODERATE","Amaizing","YUP","3hr",500,0, R.drawable.chembra);
        insertDrink(db,"Odisha","Daringbadi","MODERATE","Amaizing","YUP","3hr",500,0, R.drawable.daringbadi);
        insertDrink(db,"Odisha","Mahendra nagri","MODERATE","Amaizing","YUP","3hr",500,0, R.drawable.mahendranagri);
        insertDrink(db,"Madhya Pradesh","Kalkund Trek","MODERATE","Amaizing","YUP","3hr",500,0, R.drawable.kalkund);
        insertDrink(db,"Madhya Pradesh","Bhairav kund Trek","MODERATE-DIFFICULT","Amaizing","YUP","3hr",500,0, R.drawable.bhairavkund);
        insertDrink(db,"Rajasthan","Kumbhalgarh Trek","MODERATE-DIFFICULT","Nature Trails","YUP","3hr",500,0, R.drawable.kumbhalgarh);
        insertDrink(db,"Rajasthan","Pushkar","MODERATE-DIFFICULT","Nature Trails","YUP","3hr",500,0, R.drawable.pushkar);
        insertDrink(db,"Uttarakhand","Kedartal","MODERATE-DIFFICULT","Nature Trails","YUP","3hr",500,0, R.drawable.kedartal);
        insertDrink(db,"Uttarakhand","Bali Pass","MODERATE-DIFFICULT","Nature Trails","YUP","3hr",500,0, R.drawable.balipass);






    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertDrink(SQLiteDatabase db,String state,String trekname,String difficulty,String trailtype,String basecamp,String time,int fees,int popular,int id){
        ContentValues cv=new ContentValues();
        cv.put("STATE",state);
        cv.put("TREKNAME",trekname);
        cv.put("DIFFICULTY",difficulty);
        cv.put("TRAILTYPE",trailtype);
        cv.put("BASECAMP",basecamp);
        cv.put("TIME",time);
        cv.put("FEES",fees);
        cv.put("POPULAR",popular);
        cv.put("IMAGEID",id);
        db.insert("DRINK",null,cv);
    }




}
