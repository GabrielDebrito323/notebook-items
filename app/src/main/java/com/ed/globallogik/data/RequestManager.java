package com.ed.globallogik.data;

import android.content.Context;

import androidx.annotation.Nullable;

import com.ed.globallogik.R;
import com.ed.globallogik.base.ItemsRequestObserver;
import com.ed.globallogik.data.net.ApiService;
import com.ed.globallogik.model.Item;
import com.ed.globallogik.utils.CustomAlertDialog;
import com.ed.globallogik.utils.CustomAlertDialog.*;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    private static final String BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/";
    Retrofit retrofit;
    Context context;
    ApiService service;


    public RequestManager(Context context) {
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }


    public void requestItems(ItemsRequestObserver observer) {
        observer.onRequest();
        service.getItems().enqueue(new CustomCallback<ArrayList<Item>>() {
            @Override
            public void onError(String errorMessage) {
                super.onError(errorMessage);
                observer.onError(errorMessage);
            }

            @Override
            public void onSuccess(ArrayList<Item> responseBody) {
                super.onSuccess(responseBody);
                observer.onSuccess(responseBody);
            }
        });
    }

}
