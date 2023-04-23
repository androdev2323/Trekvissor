package com.example.win7.myapplication.page10;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.win7.myapplication.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Map;


public class page10 extends AppCompatActivity {
 private ArrayList<String> trekdate=new ArrayList<>();
     private ArrayList<String>  trekname=new ArrayList<>();
      private  ArrayList<String> noofcom=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page10);
        GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount(this);
        String email=acct.getEmail();
        final page10adapter adapter=new page10adapter(trekname,trekdate,noofcom);
        DatabaseReference db= FirebaseDatabase.getInstance(" https://protean-chassis-372415-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(email.replace('.',','));
      Query query=  db.orderByChild("emails").equalTo(email);
      query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    if (snapshot.getChildren().toString().equals("emergency")){
                        return;
                    } else {
                        Map map = (Map) snapshot.getValue();
                        trekname.add(map.get("trekname").toString());
                        trekdate.add(map.get("Trekdate").toString());
                        noofcom.add(map.get("companions").toString());
                        Log.d("n2", "trekname :" + trekname.get(0));
                        Log.d("n2", "trekdate :" + map.get("Trekdate").toString());
                        Log.d("n2", "compi :" + map.get("companions").toString());
                    }

                } else {
                    Toast.makeText(page10.this, "no bookings done yet", Toast.LENGTH_SHORT).show();

                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.page10menurecycl);
        recyclerView.setAdapter(adapter);
       LinearLayoutManager gridLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(gridLayoutManager);

    }
    }

