package com.example.win7.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent ihome = new Intent(page1.this,signin.class);
                finish();
                startActivity(ihome);
            }
        }, 3000);


    }
}


