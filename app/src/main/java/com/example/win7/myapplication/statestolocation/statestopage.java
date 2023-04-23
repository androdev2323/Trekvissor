package com.example.win7.myapplication.statestolocation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.win7.myapplication.MainActivity;
import com.example.win7.myapplication.R;
import com.example.win7.myapplication.databaseimp;

public class statestopage extends AppCompatActivity {

    public static String  answer="0";
    private Cursor cursor;
    private SQLiteDatabase db;
    String[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statestopage);
        SQLiteOpenHelper helper=new databaseimp(this);
        db=helper.getWritableDatabase();
       answer=getIntent().getStringExtra(answer);


if(answer.equals("Most popular Treks")){
    cursor = db.query("DRINK", new String[]{"TREKNAME"},"POPULAR==?", new String[]{Integer.toString(1)},null,null,null,null);
}
else {
    cursor = db.query("DRINK", new String[]{"TREKNAME"}, "STATE==?", new String[]{answer}, null, null, null, null);
}
        arr=new String[cursor.getCount()];

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            arr[i]=cursor.getString(0);
            cursor.moveToNext();
            i++;
        }




        statestopageadapter adapter=new statestopageadapter(arr);
        adapter.setListener(new statestopageadapter.Listener() {
            @Override
            public void onClick(String position) {
                Intent intent=new Intent(statestopage.this, MainActivity.class);
                intent.putExtra(MainActivity.b,position);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recylermenustates);
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



