package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asm_mob403.Interface.UserInterface;
import com.example.asm_mob403.Model.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    TextInputLayout username, pass, email, img, fullname;
    Button register;
    UserInterface userInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernamedk);
        pass = findViewById(R.id.passdk);
        email = findViewById(R.id.email);
        img = findViewById(R.id.imgdk);
        fullname = findViewById(R.id.fullnamedk);
        register = findViewById(R.id.regis);

        userInterface = RetrofitCC.getRetro().create(UserInterface.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = username.getEditText().getText().toString();
                String p = pass.getEditText().getText().toString();
                String e = email.getEditText().getText().toString();
                String i = img.getEditText().getText().toString();
                String f = fullname.getEditText().getText().toString();

                User user = new User(u,p,e,i,f);

                Call<User> call = userInterface.addUser(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(Register.this, "Đăng kí thành công!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Register.this, t.toString(), Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


    }
}