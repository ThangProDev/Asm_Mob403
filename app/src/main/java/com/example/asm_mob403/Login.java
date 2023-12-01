package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob403.Interface.UserInterface;
import com.example.asm_mob403.Model.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextInputLayout user, pass;
    Button login;

    UserInterface userInterface;

    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.register);

        userInterface = RetrofitCC.getRetro().create(UserInterface.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = user.getEditText().getText().toString();
                String passW = pass.getEditText().getText().toString();

                if(username.length() == 0 || passW.length() == 0){
                    Toast.makeText(Login.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show();
                }
                else {
                    Call<List<User>> call = userInterface.login(username);
                    call.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if(response.body().stream().count() == 0){
                                Toast.makeText(Login.this, "Nhập sai tên đăng nhập", Toast.LENGTH_LONG).show();
                            }else {
                                User user1 = response.body().get(0);
                                if (passW.equals(user1.getPasswork()) ){

                                    remember(user1.getFullname(),user1.get_Id(), user1.getImageUser());

                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Login.this, "Nhập sai mật khẩu", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(Login.this, "Out " + t.toString(), Toast.LENGTH_LONG).show();
                            Log.d("TAG", "onFailure: " + t.toString());
                        }
                    });
                }


            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }

    public void remember(String u, String id, String img){

        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", u);
        editor.putString("id", id);
        editor.putString("img", img);

        editor.commit();

    }
}