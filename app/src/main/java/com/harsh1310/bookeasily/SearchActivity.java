package com.harsh1310.bookeasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
EditText cityname;
Button search;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Credentials pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        cityname=findViewById(R.id.cityname);
       search=findViewById(R.id.search);
        // Intent intent=new Intent(SearchActivity.this,AvailableWorkers.class);
       // intent.putExtra("cname",cityname.getText().toString().trim());
       // startActivity(intent);
       sharedPreferences = getSharedPreferences("Pincode" , Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.putString("Pcode" , cityname.getText().toString().trim());
        //editor.putString("Address","Kondwa");
        editor.commit();
        pref=Credentials.getInstance(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cityname.getText().toString().length()>0) {
                    startActivity(new Intent(SearchActivity.this, AvailableWorkers.class));
                    pref.setpincode(cityname.getText().toString().trim());
                    editor.putString("Pcode" , cityname.getText().toString().trim());

                }
            }
        });

    }
}