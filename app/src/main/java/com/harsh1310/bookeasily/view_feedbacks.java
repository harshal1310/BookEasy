package com.harsh1310.bookeasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class view_feedbacks extends AppCompatActivity {
    ArrayList<commentmodel> al;
    feedbackadapter md;
    ImageView work_image;
    RecyclerView rv;
    commentmodel p;
FirebaseDatabase db;
Credentials pref;
String agencyname;
EditText comentbox;
Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedbacks);
        comentbox=findViewById(R.id.commentbox);
        send=findViewById(R.id.commentbtn);
        pref=Credentials.getInstance(this);
        rv=findViewById(R.id.rvieoffedback);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);

        al = new ArrayList<>();
        p = new commentmodel();
        agencyname=pref.getworkname();
db=FirebaseDatabase.getInstance();
db.getReference().child("Feedbacks").child(agencyname).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot dataloop : snapshot.getChildren())
        {   //Log.d("check",getcity);
            //  Log.d("case:",City+" "+getcity);
            //Toast.makeText(AvailableWorkers.this , "TEST" , Toast.LENGTH_LONG).show();
            commentmodel p = new commentmodel();
            //Toast.makeText(getApplicationContext() , "Value:" + child.child("Name").getValue().toString() , Toast.LENGTH_SHORT).show();

            p.setName(dataloop.child("name").getValue(String.class));
            p.setCommentedtext(dataloop.child("commentedtext").getValue(String.class));

           // if(dataloop.hasChild("urlToImage"))
            ///{
               // p.setUrl(dataloop.child("urlToImage").getValue(String.class));
                //  p.setImage(R.drawable.profile);

            //}
            //else
            //{
                p.setImag(R.drawable.profile);
            //}


            al.add(p);


        }
        md = new feedbackadapter(view_feedbacks.this , al);
        rv.setAdapter(md);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(comentbox.getText().toString().trim().length()>0){
            commentmodel cmt=new commentmodel(pref.getName(),comentbox.getText().toString());

            db.getReference().child("Feedbacks").child(agencyname).push().setValue(cmt).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(view_feedbacks.this,"Done",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(view_feedbacks.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(view_feedbacks.this,"Commentbox should not empty",Toast.LENGTH_SHORT).show();
        }
    }
});
    }
}