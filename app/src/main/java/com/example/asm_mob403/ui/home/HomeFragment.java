package com.example.asm_mob403.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob403.Adapter.RecBook;
import com.example.asm_mob403.ChiTietTruyen;
import com.example.asm_mob403.Interface.BookInterface;
import com.example.asm_mob403.Model.Book;
import com.example.asm_mob403.R;
import com.example.asm_mob403.RetrofitCC;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    Context context;
    BookInterface bookInterface;
    List<Book> list = new ArrayList<Book>();
    RecyclerView rec;
    FloatingActionButton fab;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();


        rec = view.findViewById(R.id.recBook);
//        DividerItemDecoration itemDecoration =  new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
//        rec.addItemDecoration(itemDecoration);
        fab = view.findViewById(R.id.add);

        SharedPreferences preferences = context.getSharedPreferences("Login", context.MODE_PRIVATE);
        String n = preferences.getString("name", "");

        if(n.equals("Admin")){
            fab.setVisibility(View.VISIBLE);
        }else {
            fab.setVisibility(View.INVISIBLE);
        }

        bookInterface = RetrofitCC.getRetro().create(BookInterface.class);
        getBook();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }

    private void getBook(){

        Call<List<Book> > call = bookInterface.getBook();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {

                list = response.body();
                RecBook recBook = new RecBook(context, (ArrayList<Book>) list, HomeFragment.this);
                rec.setAdapter(recBook);
                getData(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(context, "Out", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void delBook(String idBook){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa?");

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<Void> call = bookInterface.delBook(idBook);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                        getBook();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "cut  " + t.toString(), Toast.LENGTH_LONG).show();
                    }
                });

                dialog.dismiss();
            }
        });
    }

    public void addBook(){
        bookInterface = RetrofitCC.getRetro().create(BookInterface.class);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_book, null);

        TextInputLayout nameBook = view.findViewById(R.id.nameBook);
        TextInputLayout desBook = view.findViewById(R.id.describeBook);
        TextInputLayout authorBook = view.findViewById(R.id.authorB);
        TextInputLayout dateBook = view.findViewById(R.id.dateB);
        TextInputLayout contentBook = view.findViewById(R.id.contentB);
        TextInputLayout imgBook = view.findViewById(R.id.imageBook);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Thêm truyện");

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Book book = new Book(
                        nameBook.getEditText().getText().toString(),
                        desBook.getEditText().getText().toString(),
                        authorBook.getEditText().getText().toString(),
                        imgBook.getEditText().getText().toString(),
                        dateBook.getEditText().getText().toString(),
                        contentBook.getEditText().getText().toString()
                );

                Call<Book> call = bookInterface.addBook(book);
                call.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        getBook();
                        Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Toast.makeText(context, "Out!  " + t.toString(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void upBook(String idBook, String img, String date, String author, String name, String content, String des){
        bookInterface = RetrofitCC.getRetro().create(BookInterface.class);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_book, null);

        TextInputLayout nameBook = view.findViewById(R.id.nameBook);
        TextInputLayout desBook = view.findViewById(R.id.describeBook);
        TextInputLayout authorBook = view.findViewById(R.id.authorB);
        TextInputLayout dateBook = view.findViewById(R.id.dateB);
        TextInputLayout contentBook = view.findViewById(R.id.contentB);
        TextInputLayout imgBook = view.findViewById(R.id.imageBook);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Thêm truyện");

        nameBook.getEditText().setText(name);
        desBook.getEditText().setText(des);
        authorBook.getEditText().setText(author);
        dateBook.getEditText().setText(date);
        contentBook.getEditText().setText(content);
        imgBook.getEditText().setText(img);


        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Book book = new Book(
                        nameBook.getEditText().getText().toString(),
                        desBook.getEditText().getText().toString(),
                        authorBook.getEditText().getText().toString(),
                        imgBook.getEditText().getText().toString(),
                        dateBook.getEditText().getText().toString(),
                        contentBook.getEditText().getText().toString()
                );

                Call<Book> call = bookInterface.upBook(idBook,book);
                call.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        getBook();
                        Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Toast.makeText(context, "Cut!  " + t.toString(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getData(List<Book> list){
        for (Book b : list){
            Log.i("Tag", b.toString());
        }
    }
}