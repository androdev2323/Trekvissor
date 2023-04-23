package com.example.win7.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.win7.myapplication.page10.page10;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* loaded from: classes2.dex */
public class mainmenu extends AppCompatActivity {
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        this.gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    public void launchemergency(View view) {
        startActivity(new Intent(this, emergency.class));
    }

    public void launchlocationmenu(View view) {
        startActivity(new Intent(this, locationmenu.class));
    }

    public void launchmap(View view) {
        Intent intent = new Intent(this, map.class);
        startActivity(intent);
    }

    public void launchweather(View view) {
        Intent intent = new Intent(this, weather.class);
        startActivity(intent);
    }

    public void launchpage10(View view) {
        Intent intent = new Intent(this, page10.class);
        startActivity(intent);
    }

    public void signout(View view) {
        this.gsc = GoogleSignIn.getClient((Activity) this, this.gso);
        this.gsc.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() { // from class: com.example.win7.myapplication.mainmenu.1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                mainmenu.this.finish();
                Intent intent = new Intent(mainmenu.this, signin.class);
                mainmenu.this.startActivity(intent);
            }
        });
    }
}
