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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Agencieslist extends AppCompatActivity {
Credentials pref;
String name,email,pincode,mobilenum,altnum,state,city,gender,profession,adhar;
    public static final String DEFAULT = "0";
    TextView professsion;
    ArrayList<Person> al;
    Agencyadapter md;
    ImageView work_image;
    RecyclerView rv;
    Person p;
    int integer_img_var;
    String City;
    private DatabaseReference database;
    private DatabaseReference mref;
    private DatabaseReference msubref;
    private DatabaseReference msubref_seeker;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencieslist);
        pref=Credentials.getInstance(this);
        if(pref.getsignup().equals("1")){
            Intent intent=new Intent(Agencieslist.this,Login.class);
            startActivity(intent);
        }
      //  name=pref.getName();
        //email=pref.getmail();
       // adhar=pref.getadhar();
       // pincode=pref.getpin();
       // mobilenum=pref.getmobile();
        //altnum=pref.getaltnum();
       // state=pref.getstate();
       City=pref.getcity();
       // gender=pref.getgender();
       // profession=pref.getprofession();
       // Log.d("check",name+" "+email+" "+pincode+" "+mobilenum+" "+altnum+" "+state+" "+city+" "+gender+" "+profession);
        rv = (RecyclerView)findViewById(R.id.recforafency);
        work_image = (ImageView)findViewById(R.id.agency_image);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();


        //    Log.d("Get Data", );
        al = new ArrayList<>();
        p = new Person();

pincode=pref.getpin();
Log.d("check",pincode);
        FirebaseDatabase.getInstance().getReference().child("Agencies").child(pincode).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //String x=    dataSnapshot.child("City").getValue().toString().trim();
                String x=dataSnapshot.getRef().getKey();
                Log.d("check",x);
                //dataSnapshot.child()
                for(DataSnapshot dataloop : dataSnapshot.getChildren())
                {//Log.d("check")
                    //Toast.makeText(AvailableWorkers.this , "" + dataloop.child("City").getValue(String.class), Toast.LENGTH_SHORT).show();


                   // String getcity = dataloop.child("city").getValue(String.class);

             //    if(dataloop.hasChild("city"))
                 //{
               //      Log.d("check","yy");
                 //}
                 //else
                   //  Log.d("check","nn");
                  //  Log.d("check",getcity);
                    //
                   // if(getcity!=null)
                     //   Log.d("Get Data",getcity );
                   // else
                     //   Log.d("OKay",getcity);
                    //getcity="Kapurthala";
                    //Toast.makeText(AvailableWorkers.this , getcity+"" , Toast.LENGTH_SHORT).show();
                    //String a = "a" , b = "aGe";


                  // if(getcity.equalsIgnoreCase(getcity.trim()))
                   {//Log.d("check",getcity);
                   //Log.d("case:",City+" "+getcity);
                        //Toast.makeText(AvailableWorkers.this , "TEST" , Toast.LENGTH_LONG).show();
                       Person p = new Person();
                       //Toast.makeText(getApplicationContext() , "Value:" + child.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();
Log.d("check",dataloop.child("name").getValue(String.class));
                       p.setName(dataloop.child("name").getValue(String.class));
                        p.setId(dataloop.child("id").getValue(String.class));
                 p.setNumber(dataloop.child("contact_Number").getValue(String.class));
                 //p.setCity(dataloop.child("city").getValue(String.class));
                // p.setAddress(dataloop.child(""));
                        //p.setRating("4");
                         if(dataloop.hasChild("urlToImage"))
                        {
                            //Toast.makeText(AvailableWorkers.this , "Image exists" , Toast.LENGTH_SHORT).show();
                            Picasso.get().load(dataSnapshot.child("urlToImage").getValue().toString());
                            p.setUrl(dataloop.child("urlToImage").getValue(String.class));
                              p.setImage(R.drawable.profile);

                        }
                        else
                        {
                            p.setImage(R.drawable.profile);
                        }


                       al.add(p);
                    }

                }
                md = new Agencyadapter(Agencieslist.this , al);
                rv.setAdapter(md);
                //Toast.makeText(AvailableWorkers.this , "" + City , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}