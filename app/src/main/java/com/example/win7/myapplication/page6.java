package com.example.win7.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class page6 extends AppCompatActivity {
    EditText datepicker ;
    TextInputLayout companions;
    TextInputLayout phone;
    Spinner spinner;
    GoogleSignInAccount acct;
    DatePickerDialog dialog;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);
        datepicker = findViewById(R.id.datepicker);
        companions= findViewById(R.id.companions);
        phone = findViewById(R.id.phone);
        spinner=findViewById(R.id.spinner);
        SQLiteOpenHelper helper=new databaseimp(this);
        int i=0;
        db=helper.getWritableDatabase();
        acct= GoogleSignIn.getLastSignedInAccount(this);
        TextView emailidtext=findViewById(R.id.textView2);
        TextView usernametext=findViewById(R.id.textView);
        emailidtext.setText(acct.getEmail().toString());
        usernametext.setText(acct.getDisplayName().toString());


        cursor = db.query("DRINK", new String[]{"_id","TREKNAME"},null,null,null,null,null);
        String[] arr=new String[cursor.getCount()];
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            arr[i]=cursor.getString(1);
            cursor.moveToNext();
            i++;
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,arr);
        spinner.setAdapter(arrayAdapter);
        final Calendar calendar = Calendar.getInstance();
        final Calendar cal = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        datepicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog=new DatePickerDialog(page6.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        datepicker.setText(day+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

                cal.add(Calendar.DAY_OF_MONTH,10);
                dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
                dialog.show();
            }
        });


        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getEditText().getText().toString().length() != 10) {
                    Toast.makeText(page6.this, "incorrect phone number", Toast.LENGTH_SHORT).show();


                    if (companions.getEditText().getText().toString().length() > 5) {
                        Toast.makeText(page6.this, "exceeds companion limit", Toast.LENGTH_SHORT).show();
                    }
                    if (spinner.getSelectedItem() == null) {
                        Toast.makeText(page6.this, "no location selected ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    insertData(spinner.getSelectedItem().toString(),datepicker.getText().toString(),companions.getEditText().getText().toString(),acct.getEmail());


                }
            }
        });

    }
    public void insertData (String trekname, String Trekdate, String companions,String email){
        DatabaseReference db= FirebaseDatabase.getInstance(" https://protean-chassis-372415-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

        final HashMap<String,Object> dataset=new HashMap<>();
        dataset.put("trekname",trekname);
        dataset.put("companions",companions);
        dataset.put("Trekdate",Trekdate);
        dataset.put("emails",email);
        db.child(email.replace('.',',')).push().updateChildren(dataset).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(page6.this, "done ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}