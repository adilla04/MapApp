package com.example.mapapp;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class News_Json extends AppCompatActivity {

    ListView lv;
    private String URL = "http://192.168.0.12/Project_Hazard/";
    MyAPI MyApi;
    private ArrayList<News>modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_json);

        lv = (ListView) findViewById(R.id.lv);
        modelArrayList = new ArrayList<>();

        displayRetrofitData();
    }

    private void displayRetrofitData() {
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ( URL )
                .addConverterFactory ( GsonConverterFactory.create () )
                .build ();
        MyApi = retrofit.create ( MyAPI.class );
        Call<ArrayList<News>> arrayListCall = MyApi.callNews_Json();
        arrayListCall.enqueue ( new Callback<ArrayList<News>>() {

            @Override
            public void onResponse(Call<ArrayList<News>> call, Response<ArrayList<News>> response) {
                modelArrayList = response.body ();
                for (int i=0; i<modelArrayList.size ();i++);
                //create adapter
                Custom custom = new Custom(modelArrayList,News_Json.this,R.layout.activity_singleview);
                lv.setAdapter ( custom );
            }

            @Override
            public void onFailure(Call<ArrayList<News>> call, Throwable t) {
                Toast.makeText ( News_Json.this, "Failed to load data", Toast.LENGTH_SHORT ).show ();
            }
        } );
    }
}