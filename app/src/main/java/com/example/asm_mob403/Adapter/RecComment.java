package com.example.asm_mob403.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob403.ChiTietTruyen;
import com.example.asm_mob403.Model.Comment;
import com.example.asm_mob403.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecComment extends RecyclerView.Adapter<RecComment.viewHolder>{

    Context context;
    ArrayList<Comment> list;
    ChiTietTruyen chiTietTruyen;

    public RecComment(Context context, ArrayList<Comment> list, ChiTietTruyen chiTietTruyen) {
        this.context = context;
        this.list = list;
        this.chiTietTruyen = chiTietTruyen;
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Comment comment = list.get(position);

        Picasso.get().load(comment.getImg()).into(holder.imgAva);
        holder.name.setText(comment.getFullName());
        holder.cmt.setText(comment.getContent());
        holder.date.setText(comment.getDate());

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiTietTruyen.delCmt(comment.getIdUser(), comment.get_id());
            }
        });

        holder.put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chiTietTruyen.putComment(comment.getIdUser(), comment.get_id(), comment.getContent());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imgAva;
        TextView name, cmt, date, del, put;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imgAva = itemView.findViewById(R.id.imgUser);
            name = itemView.findViewById(R.id.nameUser);
            cmt = itemView.findViewById(R.id.cmt);
            date = itemView.findViewById(R.id.datCmt);
            del = itemView.findViewById(R.id.delCmt);
            put = itemView.findViewById(R.id.putCmt);
        }
    }

}
