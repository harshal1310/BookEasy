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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class requestsadapter extends RecyclerView.Adapter<requestsadapter.MyHolder>
{
    Credentials pref;
    Context ct;
    ArrayList<User> al;

    requestsadapter(Context cont , ArrayList<User> al)
    {
        this.ct = cont;
        this.al = al;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent , int viewType)
    {
        //Toast.makeText(ct , "Inside Adapter" , Toast.LENGTH_SHORT).show();
        LayoutInflater li = LayoutInflater.from(ct);
        View v = li.inflate(R.layout.listofrequests , parent , false);
        return new MyHolder(v);
    }


    @Override
    public void onBindViewHolder(MyHolder holder , final int position)
    {


        // SharedPreferences sharedPreferences= getSharedPreferences("Categories", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor=sharedPreferences.edit();
        //String categorie;
        SharedPreferences sharedPreferences;
        final SharedPreferences.Editor editor;
        sharedPreferences = ct.getSharedPreferences("Getkey" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        final String key;

        final User p1 = al.get(position);
        holder.Name.setText("Name:"+p1.getName());
    //    Log.d("1st check" , String.valueOf(0));

        key = p1.getId();

       // holder.Rating.setText(p1.getRating());
        //Log.d("2nd check" , String.valueOf(1));

        //holder.WorkImage.setImageResource(p1.getImage());
        if(p1.getUrl()!=null)
            Picasso.get().load(p1.getUrl()).into(holder.WorkImage);
        else
            holder.WorkImage.setImageResource(R.drawable.profile);
       // Log.d("3rd check" , String.valueOf(2));
holder.number.setText("Num:"+p1.getContact_Number());
        holder.city.setText("City:"+p1.getCity());
     //   p1.g
        holder.address.setText("Address:"+p1.getProfession());
holder.done.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
pref=Credentials.getInstance(ct);
/*Log.d("klk",pref.getName());
Log.d("klk",pref.getuserid());
FirebaseDatabase.getInstance().getReference().child("Agenciesreq").child(pref.getName()+" "+"1").child(pref.getuserid()).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String x=snapshot.child("name").getValue(String.class);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});*/
        int newPosition = holder.getAdapterPosition();
        al.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, al.size());
      // User x= al.get(position);
       //Log.d("oo",x.getId());
        if(holder.done.getText().equals("DONE"))
        {
          //  holder.done.setText("UNDONE");
        }
        //else
         //   holder.done.setText("DONE");
    }
});
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
        TextView number,city,address;
        CardView cld;

        Button done;

        public MyHolder(View itemView)
        {
            super(itemView);
            WorkImage = (ImageView)itemView.findViewById(R.id.user_image);
            Name = (TextView)itemView.findViewById(R.id.userreq_name);
            city = (TextView)itemView.findViewById(R.id.user_city);
done=itemView.findViewById(R.id.done);
number=itemView.findViewById(R.id.user_number);
            address = itemView.findViewById(R.id.user_address);

        }
    }


}
