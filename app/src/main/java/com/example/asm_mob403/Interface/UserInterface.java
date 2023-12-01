package com.example.asm_mob403.Interface;

import com.example.asm_mob403.Model.Comment;
import com.example.asm_mob403.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInterface {


    @GET("/login/{username}")
    Call<List<User>> login(@Path("username") String username);

    @POST("/addUser")
    Call<User> addUser(@Body User user);

}
