package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();
            }
        });
    }


    private void alertbox(){
        AlertDialog.Builder b=new AlertDialog.Builder(HomeScreen.this);
        b.setTitle("Warning");
        b.setMessage("Are you sure you want to logout");
        b.setCancelable(false);

        b.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("loginName","");
                ed.putString("loginPassword","");
                ed.commit();
                Intent i = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(i);
            }
        });

        AlertDialog build = b.create();
        build.show();
    }
}