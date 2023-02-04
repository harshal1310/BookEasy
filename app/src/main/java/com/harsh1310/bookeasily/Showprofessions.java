package com.harsh1310.bookeasily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Showprofessions extends AppCompatActivity {
    CircleImageView header;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase , mref , msubref;
    ProgressDialog pd;

    TextView name , mail;

    ArrayList<String> mTitles = new ArrayList<>();
    private RecyclerView.ViewHolder mViewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showprofessions);

    }

}