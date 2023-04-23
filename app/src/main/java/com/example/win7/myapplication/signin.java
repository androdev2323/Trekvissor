package com.example.win7.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class signin extends AppCompatActivity implements View.OnClickListener{
    GoogleSignInOptions gso;
            GoogleSignInClient gsc;
            SignInButton button;
            public static final String TAG="wwe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        button=(SignInButton) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View v){

        signin2();
    }

    public void signin2(){
        Intent signinintent=gsc.getSignInIntent();
        startActivityForResult(signinintent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Intent intent=new Intent(this,mainmenu.class);
                startActivity(intent);
            } catch (ApiException e) {
                Log.w(TAG,"SIGNINRESULT:FAILEDCODE="+e.getStatusCode());
            }
        }
    }


}
