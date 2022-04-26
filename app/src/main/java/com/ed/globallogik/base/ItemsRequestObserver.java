package com.ed.globallogik.base;

import com.ed.globallogik.model.Item;

import java.util.ArrayList;

public interface ItemsRequestObserver {

    void onRequest();
    void onSuccess(ArrayList<Item> items);
    void onError(String errorMessage);
}
