package com.example.win7.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.win7.myapplication.statestolocation.statestopage;

public class locationmenu extends AppCompatActivity {

    private Cursor cursor;
   private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationmenu);
        SQLiteOpenHelper helper=new Statedatabase(this);
         db=helper.getWritableDatabase();


         cursor = db.query("STATES", new String[]{"STATE"},null,null,null,null,null);
        String[] arr=new String[cursor.getCount()];
        Log.d("n",String.valueOf(cursor.getCount()));
        cursor.moveToFirst();
         while(!cursor.isAfterLast()){

              arr[i]=cursor.getString(0);
              cursor.moveToNext();
              i++;
         }




        locationmenuadapter adapter=new locationmenuadapter(arr);
         adapter.setListener(new locationmenuadapter.Listener() {
             @Override
             public void onClick(String position) {
                 Intent intent=new Intent(locationmenu.this, statestopage.class);

                 intent.putExtra(statestopage.answer,position);
                  startActivity(intent);
             }
         });
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.menurecycler);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
