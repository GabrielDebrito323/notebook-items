package com.ed.globallogik.base;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static com.ed.globallogik.recycler_view.RecyclerViewAdapter.CellType.ITEM;
import static com.ed.globallogik.utils.CustomAlertDialog.Type.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ed.globallogik.data.RequestManager;
import com.ed.globallogik.R;
import com.ed.globallogik.recycler_view.RecyclerItem;
import com.ed.globallogik.recycler_view.RecyclerViewAdapter;
import com.ed.globallogik.model.Item;
import com.ed.globallogik.utils.CustomAlertDialog;
import com.ed.globallogik.utils.CustomAlertDialog.Type;
import com.ed.globallogik.view.CustomAlert;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    ProgressBar pBar;
    RecyclerView rView;
    CustomAlert cAlert;

    RequestManager requestManager = new RequestManager(MainActivity.this);

    RecyclerViewAdapter adapter = new RecyclerViewAdapter(ITEM,MainActivity.this, item -> {
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
        intent.putExtra("selected_item", item);
        startActivity(intent);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar = findViewById(R.id.progress_bar);
        rView = findViewById(R.id.recycler_view);
        cAlert = findViewById(R.id.error_alert);

        rView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rView.setAdapter(adapter);

        cAlert.setOnButtonPressedListener(new CustomAlert.OnButtonPressed() {
            @Override
            public void onClick() {
                requestItems();
            }
        });

        requestItems();
    }



    public void requestItems() {
        requestManager.requestItems(new ItemsRequestObserver() {
            @Override
            public void onRequest() {
                pBar.setVisibility(VISIBLE);
                cAlert.setVisibility(GONE);
            }

            @Override
            public void onSuccess(ArrayList<Item> items) {
                pBar.setVisibility(View.GONE);
                cAlert.setVisibility(GONE);
                adapter.updateList(new ArrayList<>(items));
            }

            @Override
            public void onError(String errorMessage) {
                pBar.setVisibility(GONE);
                cAlert.setVisibility(VISIBLE);
                buildDialog(errorMessage, ERROR);
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buildDialog(String message, Type type) {
        new CustomAlertDialog(MainActivity.this).setType(type)
                .setMessage(message)
                .setNeutralButton(R.string.try_again, (dialogInterface, i) -> {
                    requestItems();
                })
                .setPositiveButton(R.string.accept, (dialogInterface, i) -> {
                })
                .show();
    }
}