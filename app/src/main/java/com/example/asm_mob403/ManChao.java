package com.example.asm_mob403;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.window.SplashScreen;

public class ManChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_chao);

        ImageView img = findViewById(R.id.logo);

        AnimatorSet animatorSet2 = (AnimatorSet) AnimatorInflater.loadAnimator(ManChao.this, R.animator.logo);
        animatorSet2.setTarget(img);
        animatorSet2.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);

                String name = preferences.getString("name", "");

                if(name.length() >0){
                    Intent intent = new Intent(ManChao.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {

                    Intent intent = new Intent(ManChao.this, Login.class);
                    startActivity(intent);
                    finish();
                }

            }
            }, 3000
        );
    }
}