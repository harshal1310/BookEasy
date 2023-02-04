package com.harsh1310.bookeasily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class feedbackadapter extends RecyclerView.Adapter<feedbackadapter.MyHolder> {
    Context ct;
    ArrayList<commentmodel> al;
    Credentials pref;
    feedbackadapter(Context cont , ArrayList<commentmodel> al)
    {
        this.ct = cont;
        this.al = al;
        pref=Credentials.getInstance(cont);
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(ct);
        View v = li.inflate(R.layout.listoffeddbacks , parent , false);
        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull feedbackadapter.MyHolder holder, int position) {
        commentmodel u=al.get(position);
        holder.nameoffeedbacker.setText("Name:"+u.getName());
        holder.commenttext.setText(u.getCommentedtext());
       // holder.city.setText("City"+u.getCity());

    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView nameoffeedbacker,commenttext;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nameoffeedbacker=itemView.findViewById(R.id.feedbackername);
            commenttext=itemView.findViewById(R.id.comment);
        }
    }
}
