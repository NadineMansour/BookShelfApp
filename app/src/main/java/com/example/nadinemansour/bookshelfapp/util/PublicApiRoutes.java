package com.example.nadinemansour.bookshelfapp.util;

import com.example.nadinemansour.bookshelfapp.UserData;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


public interface PublicApiRoutes {



	@POST("/sessions")
	@FormUrlEncoded
	void login(@Field("session[fbtoken]") String token, Callback<UserData> callback);



	/*
	@GET("/products")
	void getProducts(Callback<List<Product>> callback);
	*/
}
