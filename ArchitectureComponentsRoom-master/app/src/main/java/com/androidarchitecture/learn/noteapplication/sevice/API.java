package com.androidarchitecture.learn.noteapplication.sevice;

import com.androidarchitecture.learn.noteapplication.model.Crop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API
{

//    String BASE_URL = "http://192.168.1.179/gpiservice/api/";

    @GET("crop")
    Call<List<Crop>> getData();

   // Call<String> checkLogin(@Header("Authorization")String authToken);

}
