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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name,password;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);

        btn = findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Error()){
                    SharedPreferences sp = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed=sp.edit();
                    String Name = name.getText().toString();
                    String Password = password.getText().toString();
                    ed.putString("loginName",Name);
                    ed.putString("loginPassword",Password);
                    ed.commit();

                    Intent i = new Intent(MainActivity.this,HomeScreen.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
    private Boolean Error(){
        if(name.getText().toString().trim().isEmpty() ||
           password.getText().toString().trim().isEmpty()){
            alertbox("Error","The field must required");
            return false ;
        }
        else if (name.getText().toString().equals("Anandhu") &&
        password.getText().toString().equals("Anandhu@123")){
            return true;
        }
        alertbox("Error","name and Password Missmatch");
        return false;
    }

    private void alertbox(String title,String messge){
        AlertDialog.Builder b=new AlertDialog.Builder(MainActivity.this);
        b.setTitle(title);
        b.setMessage(messge);
        b.setCancelable(false);

        b.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog build = b.create();
        build.show();
    }
}
