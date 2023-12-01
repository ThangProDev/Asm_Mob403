package com.example.asm_mob403;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCC {
    private static Retrofit retrofit;

    public static Retrofit getRetro(){

        retrofit = new Retrofit.Builder()
                .baseUrl("http:// 192.168.43.241:9999/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
