package com.example.asm_mob403;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    TextView hi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        hi = findViewById(R.id.hi);

        Intent intent =getIntent();
        Bundle bundle = intent.getBundleExtra("hi");

        hi.setText(bundle.getString("content"));

    }
}