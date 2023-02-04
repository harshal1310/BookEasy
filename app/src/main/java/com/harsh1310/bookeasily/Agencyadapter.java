package com.harsh1310.bookeasily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Agencyadapter extends RecyclerView.Adapter<Agencyadapter.MyHolder>
{
    Context ct;
    ArrayList<Person> al;
    Credentials pref;
DatabaseReference db,servicemandb,agencydb,agencyrelatedworkers;
public String aname="a12";
    Agencyadapter(Context cont , ArrayList<Person> al)
    {
        this.ct = cont;
        this.al = al;
pref=Credentials.getInstance(cont);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent , int viewType)
    {
        //Toast.makeText(ct , "Inside Adapter" , Toast.LENGTH_SHORT).show();
        LayoutInflater li = LayoutInflater.from(ct);
        View v = li.inflate(R.layout.listofagencies , parent , false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
       // SharedPreferences sharedPreferences;
       // final SharedPreferences.Editor editor;
      //  sharedPreferences = ct.getSharedPreferences("Getkey" , Context.MODE_PRIVATE);
       // editor = sharedPreferences.edit();
        Credentials pref;
        pref=Credentials.getInstance(ct);
if(pref.gettypeofuser().equals("User")){
    holder.registerforagencct.setText("Book");
}
        final String key;

        final Person p1 = al.get(position);
 aname=p1.getName();

        holder.Name.setText("Agency Name:"+p1.getName());
holder.number.setText("Agency No:"+p1.getNumber());

        //Log.d("1st check" , String.valueOf(0));

        key = p1.getId();

        //holder.Rating.setText(p1.getRating());
       // Log.d("2nd check" , String.valueOf(1));

        //holder.WorkImage.setImageResource(p1.getImage());
        if(p1.getUrl()!=null)
            Picasso.get().load(p1.getUrl()).into(holder.WorkImage);
        else
            holder.WorkImage.setImageResource(R.drawable.profile);
        Log.d("3rd check" , String.valueOf(2));
        String pin= pref.getpin();
        String prof=pref.getprofession();
        String name=pref.getName();
        String adhar=pref.getadhar();
        String number=pref.getmobile();
        String altnum=pref.getaltnum();
        String mail=pref.getmail();
        String state=pref.getstate();
        String city=pref.getcity();
        String gender=pref.getgender();
        String id=pref.getuserid();
        String pass=pref.getpass();
        FirebaseAuth Auth= FirebaseAuth.getInstance();

        //  Log.d("kk",x+" "+"p");
        holder.cld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x = holder.Name.getText().toString();
Log.d("check",x);
            }
        });
//String x;
        holder.registerforagencct.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {

        if(pref.gettypeofuser().equals("Serviceman")) {
            String x = holder.Name.getText().toString();

            Auth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        db = FirebaseDatabase.getInstance().getReference();
                        servicemandb = FirebaseDatabase.getInstance().getReference("Serviceman");
                        agencyrelatedworkers=FirebaseDatabase.getInstance().getReference("Agencyrelatedworkers").child(holder.Name.getText().toString().trim()+prof);
                        User u = new User(pin, name, x);
                        Log.d("kk", holder.Name.getText().toString());
                        User serviceuser = new User(mail, id, number, adhar, prof, pin, state, city, gender, name, altnum);
                        // String email,String id,String contact_Number,String adhar,String profession,String pincode,String state,String city,String gender,String name,String alternate_Contact_Number
                        db.child(pin).child(prof).push().setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    agencyrelatedworkers.push().setValue(serviceuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                    servicemandb.push().setValue(serviceuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            pref.checkforsignup("1");
                                            pref.settypeofuser("Serviceman");
                                            ct.startActivity(new Intent(ct, Login.class));

                                            //     Intent intent=new Intent(ct,Login.class);
                                            //               startActivity(intent);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
        else if(pref.gettypeofuser().equals("User")){
           String add= pref.getadres();
           String city=pref.getcity();
           String id=pref.getuserid();
            User u=new User(name,mail,number,aname+" "+"1",add,city,id);
            agencydb=FirebaseDatabase.getInstance().getReference("Agenciesreq").child(p1.getName()+" "+"1");
            agencydb.push().setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   ct.startActivity(new Intent(ct, Login.class));


               }
                }
            });



        }

      //  Log.d("kk",holder.Name.getText().toString());
    }

});

        holder.view_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.setwname(p1.getName());
                Intent intent=new Intent(ct,view_feedbacks.class);
                ct.startActivity(intent);
            }
        });
        /*holder.cld.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(ct , "Clicked " + position , Toast.LENGTH_SHORT).show();
                //ct.startActivity(new Intent(ct,Rec_Show_Seeker.class));
                //Toast.makeText(ct , "Id" + key , Toast.LENGTH_SHORT).show();
                editor.putString("key" , key);
                editor.commit();
                ct.startActivity(new Intent(ct , Login.class));

            }
        });
*/
    }



    @Override
    public int getItemCount()
    {
        return al.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView WorkImage;
        TextView Name;
        TextView number;
        CardView cld;
Button registerforagencct,view_feedback;
        public MyHolder(View itemView)
        {
            super(itemView);
            WorkImage = (ImageView)itemView.findViewById(R.id.agency_image);
            Name = (TextView)itemView.findViewById(R.id.agency_name);
            number = (TextView)itemView.findViewById(R.id.agency_number);
            cld = (CardView)itemView.findViewById(R.id.clagency);
registerforagencct=itemView.findViewById(R.id.registerforagency);
view_feedback=itemView.findViewById(R.id.view_feedback);
        }
    }


}

