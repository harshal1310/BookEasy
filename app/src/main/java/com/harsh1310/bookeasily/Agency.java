package com.harsh1310.bookeasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Agency extends AppCompatActivity {
Button showreq,showorkers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency);
        showreq=findViewById(R.id.showrequests);
        showorkers=findViewById(R.id.showworkers);
        showreq.setOnClickListener(v->openreq());
        showorkers.setOnClickListener(v->openworkers());
    }

    private void openworkers() {
        Intent intent=new Intent(Agency.this,RecruiterMain.class);
        startActivity(intent);
    }

    private void openreq() {
        Intent intent=new Intent(Agency.this,Showrequests.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}