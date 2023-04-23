package com.example.win7.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class emergency extends AppCompatActivity implements OnClickListener {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    GoogleSignInAccount acct;
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        acct=GoogleSignIn.getLastSignedInAccount(this);

        String a = acct.getEmail();
        setContentView(R.layout.activity_emergency);
        Button button = (Button) findViewById(R.id.send);
        final TextInputLayout name1 =  findViewById(R.id.name1);
        final TextInputLayout name2 =  findViewById(R.id.name2);
        final TextInputLayout name3 =  findViewById(R.id.name3);
        final TextInputLayout number1 =  findViewById(R.id.number1);
        final TextInputLayout number2 =  findViewById(R.id.number2);
        final TextInputLayout number3 =  findViewById(R.id.number3);
        DatabaseReference reference=FirebaseDatabase.getInstance(" https://protean-chassis-372415-default-rtdb.asia-southeast1.firebasedatabase.app").getReference(a.replace('.',',')).child("emergency");

        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()) {

                                                    name1.getEditText().setText(snapshot.child("name1").getValue(String.class));
                                                    number1.getEditText().setText(snapshot.child("number1").getValue(String.class));
                                                    name2.getEditText().setText(snapshot.child("name2").getValue(String.class));
                                                    number2.getEditText().setText(snapshot.child("number2").getValue(String.class));
                                                    name3.getEditText().setText(snapshot.child("name3").getValue(String.class));
                                                    number3.getEditText().setText(snapshot.child("number3").getValue(String.class));
                                                    Log.d("n2", snapshot.getValue().toString());
                                                }

                                            }


                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
             });


        button.setOnClickListener(this);
    }


    public void onClick(View view) {
        TextInputLayout name1 =  findViewById(R.id.name1);
        TextInputLayout name2 =  findViewById(R.id.name2);
        TextInputLayout name3 =  findViewById(R.id.name3);
        String a =acct.getEmail();
        TextInputLayout number1 =  findViewById(R.id.number1);
        TextInputLayout number2 =  findViewById(R.id.number2);
        TextInputLayout number3 =  findViewById(R.id.number3);
        int size1 = number1.getEditText().getText().toString().length();
        int size2 = number2.getEditText().getText().toString().length();
        int size3 = number3.getEditText().getText().toString().length();
        int Name1 = name1.getEditText().getText().toString().length();
        int Name2 = name2.getEditText().getText().toString().length();
        int Name3 = name3.getEditText().getText().toString().length();
        if (size1 < 10 || size2 < 10 || size3 < 10 ) {
            Toast.makeText(this, "number not correct", Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference db= FirebaseDatabase.getInstance(" https://protean-chassis-372415-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        final HashMap<String,Object> dataset=new HashMap<>();
        dataset.put("name1",name1.getEditText().getText().toString());
        dataset.put("number1",number1.getEditText().getText().toString());
        dataset.put("name2",name2.getEditText().getText().toString());
        dataset.put("number2",number2.getEditText().getText().toString());
        dataset.put("name3",name3.getEditText().getText().toString());
        dataset.put("number3",number3.getEditText().getText().toString());
        db.child(a.replace('.',',')).child("emergency").updateChildren(dataset).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(emergency.this, "done ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(emergency.this, MainActivity.class));
            }
        });

    }
}
