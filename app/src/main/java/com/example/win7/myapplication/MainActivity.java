package com.example.win7.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private SQLiteDatabase db;
    private Cursor cursor;
    public static final  String  b="0";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String d[]={getIntent().getStringExtra(b)};
        SQLiteOpenHelper helper=new databaseimp(this);
        db=helper.getWritableDatabase();
        cursor = db.query("DRINK", new String[]{"_id","TREKNAME","DIFFICULTY","BASECAMP","TIME","TRAILTYPE","FEES","POPULAR","IMAGEID"},"TREKNAME=?",d,null,null,null,null);
        cursor.moveToFirst();
        TextView difficult = (TextView) findViewById(R.id.Difficulty);
        TextView Trail = (TextView) findViewById(R.id.Trailtype);
        TextView BaseCamp = (TextView) findViewById(R.id.Basecamp);
        TextView Fees = (TextView) findViewById(R.id.Fees);
        TextView time = (TextView) findViewById(R.id.Time);
        TextView t=((TextView) findViewById(R.id.Trekname));
        t.setText((cursor.getString(1)));
        difficult.setText(cursor.getString(2));
        Trail.setText(cursor.getString(5));
        BaseCamp.setText(cursor.getString(3));
        Fees.setText(Integer.toString(cursor.getInt(6)));
        time.setText(cursor.getString(4));
        ImageView i=(ImageView) findViewById(R.id.imageView);
        i.setImageResource(cursor.getInt(8));

        Button bookbutton=findViewById(R.id.Bookbutton);
        bookbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,page6.class);
                startActivity(intent);


            }

        });

    }
}
