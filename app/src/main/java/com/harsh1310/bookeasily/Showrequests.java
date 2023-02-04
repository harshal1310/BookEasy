package com.harsh1310.bookeasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Showrequests extends AppCompatActivity {
    Credentials pref;
    String name, email, pincode, mobilenum, altnum, state, city, gender, profession, adhar;
    public static final String DEFAULT = "0";
    TextView professsion;
    ArrayList<User> al;
    requestsadapter md;
    ImageView work_image;
    RecyclerView rv;
    User p;
    int integer_img_var;
    String City, agencyname;
    private DatabaseReference database;
    private DatabaseReference mref;
    private DatabaseReference msubref;
    private DatabaseReference msubref_seeker;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showrequests);
        pref = Credentials.getInstance(this);

        //  name=pref.getName();
        //email=pref.getmail();
        // adhar=pref.getadhar();
        // pincode=pref.getpin();
        // mobilenum=pref.getmobile();
        //altnum=pref.getaltnum();
        // state=pref.getstate();
        // city=pref.getcity();
        // gender=pref.getgender();
        // profession=pref.getprofession();
        // Log.d("check",name+" "+email+" "+pincode+" "+mobilenum+" "+altnum+" "+state+" "+city+" "+gender+" "+profession);
        Toast.makeText(Showrequests.this, "hii", Toast.LENGTH_SHORT).show();
        rv = (RecyclerView) findViewById(R.id.recforuserlist);
        work_image = (ImageView) findViewById(R.id.work_image);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

      agencyname = pref.getagencyname();
//Log.d("check",agencyname+"ko");
      //agencyname="agency3"+" "+"1";
        //    Log.d("Get Data", );
        al = new ArrayList<>();
        p = new User();

       // String agencyname = pref.getName();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Agenciesreq");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String x=    dataSnapshot.child("City").getValue().toString().trim();
                //  String x=dataSnapshot.getRef().getKey();
                // Log.d("c",x);
                //dataSnapshot.child()
                if(dataSnapshot.hasChild(agencyname+" "+"1"))
                {for (DataSnapshot dataloop : dataSnapshot.child(agencyname+" "+"1").getChildren()) {

                    {//Log.d("check",getcity);
                        // Log.d("case:",City+" "+getcity);
                        //Toast.makeText(AvailableWorkers.this , "TEST" , Toast.LENGTH_LONG).show();
                        User p = new User();
                        //Toast.makeText(getApplicationContext() , "Value:" + child.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();
Log.d("check",dataloop.child("contact_Number").getValue(String.class));
                        p.setName(dataloop.child("name").getValue(String.class));
                        p.setId(dataloop.child("id").getValue(String.class));
                            p.setContact_Number(dataloop.child("contact_Number").getValue(String.class));
                p.setAddress(dataloop.child("street_No").getValue(String.class));
             //   p.setCity(dataloop.child("city").getValue(String.class));
                        //p.setRating("4");
                        if (dataloop.hasChild("urlToImage")) {
                            //Toast.makeText(AvailableWorkers.this , "Image exists" , Toast.LENGTH_SHORT).show();
                            Picasso.get().load(dataSnapshot.child("urlToImage").getValue().toString());
                            p.setUrl(dataloop.child("urlToImage").getValue(String.class));
                            p.setImage(R.drawable.profile);

                        } else {
                            p.setImage(R.drawable.profile);
                        }


                        al.add(p);
                    }

                }
                md = new requestsadapter(Showrequests.this, al);
                rv.setAdapter(md);
                //Toast.makeText(AvailableWorkers.this , "" + City , Toast.LENGTH_SHORT).show();
            }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

}