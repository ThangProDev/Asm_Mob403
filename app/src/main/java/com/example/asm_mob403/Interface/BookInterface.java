package com.example.asm_mob403.Interface;

import com.example.asm_mob403.Model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookInterface {

    @GET("getBook")
    Call<List<Book>> getBook();

    @POST("addBook")
    Call<Book> addBook(@Body Book book);

    @DELETE("deleteBook/{idBook}")
    Call<Void> delBook(@Path("idBook") String idBook);

    @PUT("upBook/{idBook}")
    Call<Book> upBook(@Path("idBook") String idBook,@Body Book book);



}
