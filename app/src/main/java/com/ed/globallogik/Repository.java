package com.ed.globallogik;

import com.ed.globallogik.data.CustomCallback;
import com.ed.globallogik.data.net.ApiService;
import com.ed.globallogik.model.Item;
import java.util.ArrayList;
import javax.inject.Inject;
public class Repository {

    ApiService apiService;

    @Inject
    public Repository(ApiService apiService){
        this.apiService = apiService;
    }

    public void requestItems(CustomCallback<ArrayList<Item>> callback){
        apiService.getItems().enqueue(callback);
    }
}
