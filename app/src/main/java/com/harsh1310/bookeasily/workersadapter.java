package com.harsh1310.bookeasily;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class workersadapter  extends RecyclerView.Adapter<workersadapter.MyHolder>
{Context ct;
    ArrayList<workermodel> al;
Credentials pref;
    workersadapter(Context cont , ArrayList<workermodel> al)
    {
        this.ct = cont;
        this.al = al;
        pref=Credentials.getInstance(cont);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(ct);
        View v = li.inflate(R.layout.listofworkers , parent , false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
workermodel u=al.get(position);
        holder.Name.setText("Name:"+u.getName());
        holder.number.setText(u.getMobnum());
        holder.city.setText("City"+u.getCity());
        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:" + "91"+u.getMobnum());
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                //startActivity(Intent.createChooser(i, ""));
                ct.startActivity(Intent.createChooser(i,""));
            }
        });


    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView WorkImage;
        TextView Name;
        TextView Rating,city,number;
        CardView cld;
        Button view_feedback,msg;
        public MyHolder(View itemView)
        {
            super(itemView);
            WorkImage = (ImageView)itemView.findViewById(R.id.work_image);
            Name = (TextView)itemView.findViewById(R.id.worker_name);
            Rating = (TextView)itemView.findViewById(R.id.work_rating);
            city = itemView.findViewById(R.id.worker_city);
            number=itemView.findViewById(R.id.worker_number);
            msg=itemView.findViewById(R.id.msg);
        // view_feedback=itemView.findViewById(R.id.view_feedback);
        }
    }

}
/*package com.harsh1310.bookeasily;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class workersadapter  extends FirebaseRecyclerAdapter<workermodel,workersadapter.MyHolder> {
    Context ct;

    public workersadapter(@NonNull FirebaseRecyclerOptions<workermodel> options, Context ct) {

        super(options);
        this.ct = ct;
        }



    @Override
    protected void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull workermodel model) {
    holder.Name.setText("Name:"+model.getName());
 holder.number.setText("Number:"+model.getMobnum());
 holder.city.setText("City:"+model.getCity());
 holder.Rating.setText("Rating:"+model.getRating());
 Log.d("check",model.getName());
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(ct);
        View v = li.inflate(R.layout.listofworkers , parent , false);
        return new MyHolder(v);
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView WorkImage;
        TextView Name;
        TextView Rating,city,number;
        CardView cld;
        Button registerforagencct;
        public MyHolder(View itemView)
        {
            super(itemView);
            WorkImage = (ImageView)itemView.findViewById(R.id.work_image);
            Name = (TextView)itemView.findViewById(R.id.worker_name);
            Rating = (TextView)itemView.findViewById(R.id.work_rating);
            city = itemView.findViewById(R.id.worker_city);
         //   registerforagencct=itemView.findViewById(R.id.registerforagency);
        }
    }

}
*/