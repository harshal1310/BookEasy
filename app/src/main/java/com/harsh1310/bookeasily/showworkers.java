package com.harsh1310.bookeasily;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class showworkers extends AppCompatActivity {
    Credentials pref;
    String name,email,pincode,mobilenum,altnum,state,city,gender,profession,adhar;
    public static final String DEFAULT = "0";
    TextView professsion;
    ArrayList<workermodel> al;
    requestsadapter md;
    ImageView work_image;
    RecyclerView rv;
    User p;
    int integer_img_var;
    String City;
    ArrayList<String>ls;
    private DatabaseReference database;
    private DatabaseReference mref;
    private DatabaseReference msubref;
    private DatabaseReference msubref_seeker;
    public FirebaseAuth mAuth;
    FirebaseRecyclerOptions<workermodel> options;
    workersadapter adapter;
    DatabaseReference dbref;
   // stored_credentials pref;
    Context con;
    TextView tv;
    ArrayList<String> list;
    private ArrayList<workermodel> courseModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showworkers);
        rv=findViewById(R.id.recforworkers);
        courseModelArrayList = new ArrayList<>();
        con = this;
      pref=Credentials.getInstance(this);
     String agencyname= pref.getagencyname();
        String service=pref.getprofession();
        al=new ArrayList<>();
        ls=new ArrayList<>();
       // DatabaseReference db = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Agency").child(servicename);
//DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("Agencyrelatedworkers").child("firstagency").child(service);
  //      options = new FirebaseRecyclerOptions.Builder<workermodel>().setQuery(db, workermodel.class).build();
     //   setuprecyclerview();
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);
//Log.d("check",service);
     DatabaseReference rf=   FirebaseDatabase.getInstance().getReference().child("Agencyrelatedworkers");//.child("Agency Name:"+"agency6"+"Electrician");
Log.d("check",agencyname);
        Log.d("check",service);
     rf.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.hasChild("Agency Name:" + agencyname + service)) {
            Log.d("aaa", snapshot.getKey());

            for (DataSnapshot dataloop : snapshot.child("Agency Name:"+agencyname+service).getChildren()) {
                workermodel p = new workermodel();
                //if (dataloop.hasChild("-NKHoO3biEAi3g-wTN69"))
                  //  Log.d("aaa", snapshot.getChildren().toString() + " " + snapshot.getRef() + " " + snapshot.getKey());
                //Log.d("check","p"+dataloop.getChildrenCount());
                //Toast.makeText(getApplicationContext() , "Value:" + child.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();
//Log.d("check",dataloop.child("contact_Number").getValue(String.class));
                String x = (dataloop.child("name").getValue(String.class));

                Log.d("aaa", "aaa" + x);
                //Log.d("check", dataloop.child("-NHYhkfjl9yPwK52Fr4E").child("name").getValue(String.class));
                p.setName(x);
                p.setMobnum(dataloop.child("contact_Number").getValue(String.class));
                p.setCity(dataloop.child("city").getValue(String.class));
                al.add(p);
            }


            adapter = new workersadapter(showworkers.this, al);
            rv.setAdapter(adapter);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
    }

});


    }
}