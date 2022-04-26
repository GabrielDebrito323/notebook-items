package com.ed.globallogik.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ed.globallogik.R;
import com.ed.globallogik.model.Item;

public class ItemDetailActivity extends AppCompatActivity {

    Item item;
    ImageView imgItem;
    TextView txtVTitle, txtVDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        txtVTitle = findViewById(R.id.text_view_title_item_detail);
        txtVDescription = findViewById(R.id.text_view_description_detail);
        imgItem = findViewById(R.id.image_view_item_detail);

        item = (Item) getIntent().getSerializableExtra("selected_item");
        txtVTitle.setText(item.getTitle());
        txtVDescription.setText(item.getDescription());
        Glide.with(ItemDetailActivity.this).load(item.getImageUrl()).into(imgItem);
    }
}