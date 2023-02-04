package com.harsh1310.bookeasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button user,serviceman,agencies;
Credentials pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent=new Intent(MainActivity.this,Login.class);
        //startActivity(intent);
        user=findViewById(R.id.User);
        serviceman=findViewById(R.id.Serviceman);
        agencies=findViewById(R.id.Agency);
        pref=Credentials.getInstance(this);
        if(pref.gettypeofuser().equals("User")||pref.gettypeofuser().equals("Agency")){
            Log.d("check","user");
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }
        if(pref.gettypeofuser().equals("Serviceman"))
        {
       //     Toast.makeText(MainActivity.this,"hii",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,Agencieslist.class);
            startActivity(intent);
        }
        if(pref.gettypeofuser().equals("Serviceman1")){
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }
        user.setOnClickListener(v->userregfun());
        serviceman.setOnClickListener(v->servicemanregfun());
        agencies.setOnClickListener(v->agenciesregfun());
    }

    private void agenciesregfun() {
        Intent intent=new Intent(MainActivity.this,Agenciesact.class);
        startActivity(intent);

    }

    private void servicemanregfun() {
        Intent intent=new Intent(MainActivity.this,Servicemanregact.class);
        startActivity(intent);

    }

    private void userregfun() {
        Intent intent=new Intent(MainActivity.this,Userregact.class);
    startActivity(intent);
    }
}