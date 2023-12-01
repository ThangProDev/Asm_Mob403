package com.example.asm_mob403.ui.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.asm_mob403.Login;
import com.example.asm_mob403.R;
import com.squareup.picasso.Picasso;

public class UserFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    Context context;
    Button dx;
    TextView fullname;
    ImageView ava;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        dx = view.findViewById(R.id.dangxuat);
        fullname = view.findViewById(R.id.fullname);
        ava = view.findViewById(R.id.ava);


        SharedPreferences preferences = context.getSharedPreferences("Login", context.MODE_PRIVATE);
        String n = preferences.getString("name", "");
        String img = preferences.getString("img", "");

        fullname.setText(n);
        Picasso.get().load(img).into(ava);



        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = context.getSharedPreferences("Login", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("name", "");
                editor.commit();

                Intent intent = new Intent(context, Login.class);
                context.startActivity(intent);

            }
        });

    }
}