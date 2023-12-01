package com.example.asm_mob403.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.asm_mob403.ChiTietTruyen;
import com.example.asm_mob403.Model.Book;
import com.example.asm_mob403.R;
import com.example.asm_mob403.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecBook extends RecyclerView.Adapter<RecBook.viewHolder>{

    Context context ;
    ArrayList<Book> list;
    HomeFragment homeFragment;


    public RecBook(Context context, ArrayList<Book> list, HomeFragment homeFragment) {
        this.context = context;
        this.list = list;
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_book, parent, false);
        viewHolder viewHolder = new viewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        Book book = list.get(position);

        holder.name.setText(book.getName());
        holder.author.setText("Tác giả: "+ book.getAuthor());
        holder.dateee.setText("Ngày xuất bản: "+ book.getDate());

        Picasso.get().load(book.getImageBook()).into(holder.imgBook);

        SharedPreferences preferences = context.getSharedPreferences("Login", context.MODE_PRIVATE);
        String n = preferences.getString("name", "");

        if(n.equals("Admin")){
            holder.up.setVisibility(View.VISIBLE);
            holder.del.setVisibility(View.VISIBLE);

        }else {
            holder.up.setVisibility(View.INVISIBLE);
            holder.del.setVisibility(View.INVISIBLE);

        }


        holder.cardBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietTruyen.class);

                Bundle bundle = new Bundle();

                bundle.putString("name", book.getName());
                bundle.putString("idBook", book.get_Id());
                bundle.putString("tacgia", book.getAuthor());
                bundle.putString("date", book.getDate());
                bundle.putString("des", book.getDescribe());
                bundle.putString("img", book.getImageBook());
                bundle.putString("content", book.getContent());


                intent.putExtra("book", bundle);

                context.startActivity(intent);
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.delBook(book.get_Id());
            }
        });

        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.upBook(book.get_Id(), book.getImageBook(), book.getDate(), book.getAuthor(), book.getName(), book.getContent(), book.getDescribe());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imgBook, del, up;
        TextView name, describe, author, dateee;
        CardView cardBook;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.imgBook);
            name = itemView.findViewById(R.id.nameBook);
            author = itemView.findViewById(R.id.author);
            dateee = itemView.findViewById(R.id.dateBook);
            cardBook = itemView.findViewById(R.id.cardBook);
            del = itemView.findViewById(R.id.delBook);
            up = itemView.findViewById(R.id.upBook);

        }
    }
}
