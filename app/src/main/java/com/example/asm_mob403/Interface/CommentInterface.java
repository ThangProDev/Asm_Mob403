package com.example.asm_mob403.Interface;



import com.example.asm_mob403.Model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentInterface {

    @GET("getComment/{idBook}")
    Call<List<Comment>> getComment(@Path("idBook") String idBook);

    @DELETE("deleteComment/{idUser}/{id}")
    Call<Void> deleteCmt(@Path("idUser") String idUser, @Path("id") String _id);

    @POST("addComment")
    Call<Comment> addComment(@Body Comment comment);

    @PUT("putCmt/{idUser}/{id}")
    Call<Comment> putComment(@Path("idUser") String idUser, @Path("id") String id, @Body Comment comment);


}
