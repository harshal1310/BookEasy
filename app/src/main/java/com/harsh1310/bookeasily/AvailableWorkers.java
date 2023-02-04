package com.harsh1310.bookeasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;

public class AvailableWorkers extends AppCompatActivity
{
    public static final String DEFAULT = "0";
    TextView professsion;
    ArrayList<Person> al;
    Agencyadapter md;
    ImageView work_image;
    RecyclerView rv;
    Person p;
    int integer_img_var;
    String City,prof;
    private DatabaseReference database;
    private DatabaseReference mref;
    private DatabaseReference msubref;
    private DatabaseReference msubref_seeker;
    public FirebaseAuth mAuth;
Credentials pref;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

      //  new checkInternetConnection(this).checkConnection();
        setTitle("Available Workers");
        setContentView(R.layout.activity_available_workers);
        professsion = (TextView)findViewById(R.id.ava_profession);
        SharedPreferences sharedPreferences = getSharedPreferences("Categories" , Context.MODE_PRIVATE);
        //category of the type of the worker
        prof = sharedPreferences.getString("categorie" , DEFAULT);
        //Log.d("check",categorie);
        String img_var = sharedPreferences.getString("imgvar" , DEFAULT);
        SharedPreferences sharedPreferences1 = getSharedPreferences("Pincode" , Context.MODE_PRIVATE);
pref=Credentials.getInstance(this);
City=pref.getpin();

      //  City = sharedPreferences.getString("Pcode" , DEFAULT).trim();
        //City="Pune";
      //  Toast.makeText(this , "City : " + City , Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext() , "String" + img_var , Toast.LENGTH_SHORT).show();
//Log.d("check",categorie);
Log.d("check",City);

      //  Log.d("check",c);
        integer_img_var = Integer.parseInt(img_var);
        //Toast.makeText(getApplicationContext() , "int" + integer_img_var , Toast.LENGTH_SHORT).show();
  //      professsion.setText(categorie);

        rv = (RecyclerView)findViewById(R.id.rec);
        work_image = (ImageView)findViewById(R.id.work_image);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        mref = database.child("Users");
        msubref_seeker = database.child("Seeker");
    //    msubref = msubref_seeker.child(categorie);

    //    Log.d("Get Data", );
        al = new ArrayList<>();
        p = new Person();


        FirebaseDatabase.getInstance().getReference().child("Agencies").child(City).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
            //String x=    dataSnapshot.child("City").getValue().toString().trim();
              String x=dataSnapshot.getRef().getKey();
             // Log.d("c",x);
             // dataSnapshot.child()
            for(DataSnapshot dataloop : dataSnapshot.getChildren())
                {   //Log.d("check",getcity);
                      //  Log.d("case:",City+" "+getcity);
                        //Toast.makeText(AvailableWorkers.this , "TEST" , Toast.LENGTH_LONG).show();
                        Person p = new Person();
                        //Toast.makeText(getApplicationContext() , "Value:" + child.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();

                        p.setName(dataloop.child("name").getValue(String.class));
                        p.setNumber(dataloop.child("contact_Number").getValue(String.class));

                        if(dataloop.hasChild("urlToImage"))
                        {
                            p.setUrl(dataloop.child("urlToImage").getValue(String.class));
                            //  p.setImage(R.drawable.profile);

                        }
                        else
                        {
                            p.setImage(R.drawable.profile);
                        }


                        al.add(p);


                }
                md = new Agencyadapter(AvailableWorkers.this , al);
                rv.setAdapter(md);
                //Toast.makeText(AvailableWorkers.this , "" + City , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
	    /*msubref.addValueEventListener(new ValueEventListener()
	    {
		   @Override
		   public void onDataChange(DataSnapshot dataSnapshot)
		   {
			  long count = dataSnapshot.getChildrenCount();
			  Toast.makeText(getApplicationContext() , "Children:" + count , Toast.LENGTH_SHORT).show();
			  for(DataSnapshot child : dataSnapshot.getChildren())
			  {
				 if((child.child("City").getValue().toString()).equals(City))
				 {
				      Toast.makeText(AvailableWorkers.this ,"TEST",Toast.LENGTH_LONG ).show();
					Person p = new Person();
					Toast.makeText(getApplicationContext() , "Value:" + child.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();

					p.setName(child.child("Name").getValue(String.class));
					p.setId(child.child("Id").getValue(String.class));
					//p.setRating(child.child("Rating").getValue().toString());
					p.setRating("4");

					if(dataSnapshot.hasChild("urlToImage"))
					{
					     Toast.makeText(AvailableWorkers.this , "Image exists" , Toast.LENGTH_SHORT).show();
					     Picasso.get().load(dataSnapshot.child("urlToImage").getValue().toString()).transform(new CropCircleTransformation()).into(work_image);
					}
					else
					{
					     p.setImage(R.drawable.profile);
					}


					al.add(p);
				 }
			  }
			  md = new MyAdaptor(AvailableWorkers.this , al);
			  rv.setAdapter(md);
		   }

		   @Override
		   public void onCancelled(DatabaseError databaseError)
		   {
			  Toast.makeText(getApplicationContext() , "Error in loading" , Toast.LENGTH_SHORT).show();
		   }
	    });*/
        //Toast.makeText(getApplicationContext() , "Adapter" , Toast.LENGTH_SHORT).show();




	    /*
        for(int i=0;i<5;i++)
        {
            Person p = new Person();
            p.setName("Rohit");
            switch (integer_img_var)
            {
                case 1:
                    p.setImage(R.drawable.electrician);
                    break;
                case 2:
                    p.setImage(R.drawable.carpainter);
                    break;
                case 3:
                    p.setImage(R.drawable.plumber);
                    break;
                case 4:
                    p.setImage(R.drawable.bricklayer);
                    break;
                case 5:
                    p.setImage(R.drawable.painter);
                    break;
                case 6:
                    p.setImage(R.drawable.labour);
                    break;
                    default:
                        p.setImage(R.drawable.electrician);
            }
            p.setRating("4");
            al.add(p);
        }

	    md = new MyAdaptor(AvailableWorkers.this , al);
	    rv.setAdapter(md);

     }*/
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(AvailableWorkers.this , RecruiterMain.class));
    }
}