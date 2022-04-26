package com.ed.globallogik.data.net;

import com.ed.globallogik.model.Item;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET("/list")
    Call<ArrayList<Item>> getItems();

}
