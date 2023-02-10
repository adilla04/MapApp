package com.example.mapapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    @GET("allhazard.php")
    Call<ArrayList<News>> callNews_Json();
}


