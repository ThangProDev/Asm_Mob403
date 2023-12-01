package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob403.Adapter.RecComment;
import com.example.asm_mob403.Interface.CommentInterface;
import com.example.asm_mob403.Model.Comment;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietTruyen extends AppCompatActivity {

    ImageView imgBook;
    Button addCmt;
    TextView name, describe, author, dateee;

    List<Comment> list = new ArrayList<Comment>();
    CommentInterface commentInterface;
    RecyclerView recyclerView;
    TextInputLayout cmt;
    Button doctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("book");
        String content = bundle.getString("content");
        String idBook = bundle.getString("idBook");

        doctruyen = findViewById(R.id.docTruyen);
        doctruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietTruyen.this, MainActivity2.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("content", content);
                intent1.putExtra("hi", bundle1);
                startActivity(intent1);
            }
        });

        imgBook = findViewById(R.id.ct_imgBook);
        name = findViewById(R.id.ct_nameBook);
        describe = findViewById(R.id.ct_describe);
        author = findViewById(R.id.ct_author);
        dateee = findViewById(R.id.ct_dateBook);
        addCmt = findViewById(R.id.addCmt);
        cmt = findViewById(R.id.Cmt);

        name.setText(bundle.getString("name"));
        describe.setText(bundle.getString("des"));
        author.setText("Tác giả: " + bundle.getString("tacgia"));
        dateee.setText("Ngày xuất bản: " + bundle.getString("date"));
        Picasso.get().load(bundle.getString("img")).into(imgBook);

        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        String n = preferences.getString("name", "");

        if(n.equals("Admin")){
            cmt.setVisibility(View.INVISIBLE);
            addCmt.setVisibility(View.INVISIBLE);
        }else {
            cmt.setVisibility(View.VISIBLE);
            addCmt.setVisibility(View.VISIBLE);

        }

        recyclerView = findViewById(R.id.recCmt);
//        DividerItemDecoration itemDecoration =  new DividerItemDecoration(ChiTietTruyen.this, DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(itemDecoration);

        commentInterface = RetrofitCC.getRetro().create(CommentInterface.class);
        getComment(idBook);

        addCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = cmt.getEditText().getText().toString();

                Calendar calendar =Calendar.getInstance();
                String datee = DateFormat.getDateInstance().format(calendar.getTime());

                SharedPreferences preferences =getSharedPreferences("Login", MODE_PRIVATE);

                String nameUser = preferences.getString("name", "");
                String idUser = preferences.getString("id", "");
                String img = preferences.getString("img", "");

                Comment comment1 = new Comment(comment, datee, idBook, idUser, nameUser, img);

                Call<Comment> call =commentInterface.addComment(comment1);
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        Toast.makeText(ChiTietTruyen.this, "Them thành công", Toast.LENGTH_LONG).show();
                        getComment(idBook);
                        cmt.getEditText().setText("");

                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Toast.makeText(ChiTietTruyen.this, "Cut", Toast.LENGTH_LONG).show();

                    }
                });



            }
        });


    }


    public void delCmt(String idUser, String idCmt){

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("book");
        String idBook = bundle.getString("idBook");

        commentInterface = RetrofitCC.getRetro().create(CommentInterface.class);

        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        String id = preferences.getString("id", "");

        if (id.equals(idUser)){

            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietTruyen.this);

            builder.setTitle("Delete!!");
            builder.setMessage("Bạn có muốn xóa?");
            builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Call<Void> call = commentInterface.deleteCmt(idUser,idCmt);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(ChiTietTruyen.this, "Xóa thành công", Toast.LENGTH_LONG).show();
                            getComment(idBook);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(ChiTietTruyen.this, "Cut", Toast.LENGTH_LONG).show();

                        }
                    });

                    dialog.dismiss();
                }
            });

            builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietTruyen.this);

            builder.setTitle("Lỗi!!");
            builder.setMessage("Không xóa được bình luận của người khác!!!");
            AlertDialog dialog = builder.create();
            dialog.show();
        }


    }

    public void putComment(String idUser, String idCmt, String cmtt){

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.sua_comment, null);

        TextInputLayout cmt = view.findViewById(R.id.suaCmt);

        commentInterface = RetrofitCC.getRetro().create(CommentInterface.class);

        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        String id = preferences.getString("id", "");

        cmt.getEditText().setText(cmtt);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("book");
        String idBook = bundle.getString("idBook");

        if(id.equals(idUser)){

            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietTruyen.this);
            builder.setView(view);
            builder.setTitle("Sửa bình luận");

            builder.setNegativeButton("Lưu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String cmtSua = cmt.getEditText().getText().toString();

                    Calendar calendar =Calendar.getInstance();
                    String datee = DateFormat.getDateInstance().format(calendar.getTime());

                    Comment comment = new Comment(cmtSua,datee);

                    Call<Comment> call = commentInterface.putComment(idUser, idCmt, comment);
                    call.enqueue(new Callback<Comment>() {
                        @Override
                        public void onResponse(Call<Comment> call, Response<Comment> response) {
                            getComment(idBook);
                            Toast.makeText(ChiTietTruyen.this, "Sửa thành công!", Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Call<Comment> call, Throwable t) {
                            Toast.makeText(ChiTietTruyen.this, "Cút", Toast.LENGTH_LONG).show();

                        }
                    });


                    dialog.dismiss();
                }
            });

            builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietTruyen.this);

            builder.setTitle("Lỗi!!");
            builder.setMessage("Không sửa được bình luận của người khác!!!");
            AlertDialog dialog = builder.create();
            dialog.show();
        }



    }

    public void getComment(String idBook){

        Call<List<Comment>> call = commentInterface.getComment(idBook);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                list = response.body();

                RecComment recComment = new RecComment((Context) ChiTietTruyen.this, (ArrayList<Comment>) list, ChiTietTruyen.this);
                recyclerView.setAdapter(recComment);


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(ChiTietTruyen.this, "Loi", Toast.LENGTH_LONG).show();

            }
        });
    }

}