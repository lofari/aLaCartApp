package com.example.lorenzo.alacarta.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lorenzo.alacarta.R;
import com.example.lorenzo.alacarta.SPManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText userName;
    private EditText password;
    private Button login;
    private FloatingActionButton supportButton;
    private SPManager mSPManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSPManager = new SPManager(this);

        setViews();

        if(mSPManager.sessionExists()) {
            Log.d(TAG, "onCreate: sessionexists");
            login();
        } else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = userName.getText().toString();
                    String pass = password.getText().toString();
                    userName.getText().clear();
                    password.getText().clear();

                    if (mSPManager.validateCredentials(name, pass)) {
                        login();
                    } else {
                        Toast.makeText(MainActivity.this, "wong paswold mr chang", Toast.LENGTH_LONG).show();
                    }
                }
            });

            supportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:pepe@gmail.com"));
                    startActivity(emailIntent);

                }
            });

        }
    }

    private void setViews(){
        userName = findViewById(R.id.userNameInput);
        password = findViewById(R.id.passwordInput);
        login = findViewById(R.id.btn_login);
        supportButton = findViewById(R.id.btnSupport);
    }


    private void login(){
//        Intent intent = new Intent(this, ListActivity.class);
        Intent intent = new Intent(this, DrawerActivity.class);
        startActivity(intent);
        finish();
    }





}
