package com.ed.globallogik.base;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ed.globallogik.App;
import com.ed.globallogik.ListItemsFragment;
import com.ed.globallogik.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getNetComponent().inject(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,
                ListItemsFragment.newInstance()).commit();
    }


}