package com.ed.globallogik.base;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ed.globallogik.App;
import com.ed.globallogik.ItemsViewModel;
import com.ed.globallogik.R;
import com.ed.globallogik.model.Item;
import javax.inject.Inject;

public class ItemDetailFragment extends Fragment {

    ImageView imgItem;
    TextView txtVTitle, txtVDescription;
    Item item;

    @Inject
    ItemsViewModel itemsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) requireContext().getApplicationContext()).getNetComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_item_detail, container, false);



        txtVTitle = view.findViewById(R.id.text_view_title_item_detail);
        txtVDescription = view.findViewById(R.id.text_view_description_detail);
        imgItem = view.findViewById(R.id.image_view_item_detail);

        item = itemsViewModel.getSelectedItem();
        txtVTitle.setText(item.getTitle());
        txtVDescription.setText(item.getDescription());
        Glide.with(ItemDetailFragment.this).load(item.getImageUrl()).into(imgItem);
        return view;
    }

    public static ItemDetailFragment newInstance() {
        return new ItemDetailFragment();
    }
}