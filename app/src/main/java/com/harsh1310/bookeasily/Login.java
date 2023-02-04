package com.harsh1310.bookeasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
EditText username,pass;
Button signin;
Credentials pref;
TextView newuser;
SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String DEFAULT = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        signin=findViewById(R.id.login);
        newuser=findViewById(R.id.newuser);
        pref=Credentials.getInstance(this);
//Log.d("check",pref.gettypeofuser()+" "+pref.getlogin());
       if(pref.getlogin().equals("1"))
        {

                if(pref.gettypeofuser().equals("User"))
                {Log.d("check","user");
                    Intent intent=new Intent(Login.this,RecruiterMain.class);
                    startActivity(intent);
                }
                else if(pref.gettypeofuser().equals("Agency")){
                    Intent intent=new Intent(Login.this,Agency.class);
                    startActivity(intent);
                }


                else{
                    Intent intent=new Intent(Login.this,SeekerMain.class);
                    startActivity(intent);
                }

        }

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //sharedPreferences = getSharedPreferences("Categories" , Context.MODE_PRIVATE);
        //String categorie = sharedPreferences.getString("categorie" , DEFAULT);

       // editor = sharedPreferences.edit();
signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (username.getText().toString().trim().length() > 0 && pass.getText().toString().length() > 0) {


            FirebaseAuth.getInstance().signInWithEmailAndPassword(username.getText().toString().trim(), pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        pref.checkforlogin("1");

                        if(pref.gettypeofuser().equals("User"))
                        {
                            Intent intent=new Intent(Login.this,RecruiterMain.class);
                            startActivity(intent);
                        }
                        else if(pref.gettypeofuser().equals("Agency")){
                            Intent intent=new Intent(Login.this,Agency.class);
                            startActivity(intent);
                        }
                        else if(pref.gettypeofuser().equals("Serviceman")){
                            Intent intent=new Intent(Login.this,SeekerMain.class);
                            startActivity(intent);
                        }
                        else if(username.getText().toString().equals("agency3@gmail.com")){
                            pref.settypeofuser("Agency");
                            Intent intent=new Intent(Login.this,Agency.class);
                            startActivity(intent);
                        }
                        else{

                            FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    boolean flag=false;
                                    for(DataSnapshot x:snapshot.getChildren()){
                                        if(x.child("email").equals(username.getText().toString().trim())){
                                        flag=true;
                                          break;
                                        }

                                    }
                                    if(flag==true){
                                        pref.settypeofuser("User");
                                        Intent intent=new Intent(Login.this,RecruiterMain.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            FirebaseDatabase.getInstance().getReference("Agencymail").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    boolean flag=false;
                                    for(DataSnapshot x:snapshot.getChildren()){
                                        if(x.child("email").equals(username.getText().toString().trim())){
                                            flag=true;
                                            break;
                                        }

                                    }
                                    if(flag==true){
                                        pref.settypeofuser("Agency");
                                        Intent intent=new Intent(Login.this,RecruiterMain.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                            FirebaseDatabase.getInstance().getReference("Serviceman").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    boolean flag=false;
                                    for(DataSnapshot x:snapshot.getChildren()){
                                        if(x.child("email").equals(username.getText().toString().trim())){
                                            flag=true;
                                            break;
                                        }

                                    }
                                    if(flag==true){
                                        pref.settypeofuser("Serviceman");
                                        Intent intent=new Intent(Login.this,RecruiterMain.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    } else {
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
    }
});

    }

}