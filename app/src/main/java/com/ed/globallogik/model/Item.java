package com.ed.globallogik.model;

import com.ed.globallogik.recycler_view.RecyclerItem;
import com.google.gson.annotations.*;

import java.io.Serializable;

public class Item implements Serializable, RecyclerItem {

    @SerializedName("title")
    private final String title;
    @SerializedName("description")
    private final String description;
    @SerializedName("image")
    private final String imageUrl;

    public Item(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
