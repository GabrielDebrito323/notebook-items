package com.ed.globallogik.recycler_view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ed.globallogik.R;
import com.ed.globallogik.model.Item;
import com.ed.globallogik.recycler_view.OnTouchListener;

public class ItemViewHolder extends ReusableViewHolder {

    TextView txtName;
    TextView txtDescription;
    Context context;
    ImageView imgThumbnail;
    Item currentItem;

    public ItemViewHolder(@NonNull View itemView, Context context, OnTouchListener onTouchListener) {
        super(itemView);

        this.context = context;
        txtName = itemView.findViewById(R.id.cell_text_view_name);
        txtDescription = itemView.findViewById(R.id.cell_text_view_description);
        imgThumbnail = itemView.findViewById(R.id.cell_image_view_thumbnail);


        itemView.setOnClickListener(view -> onTouchListener.onItemSelected(currentItem));
    }

    public void bindView(RecyclerItem recyclerItem) {
        currentItem = (Item) recyclerItem;
        txtName.setText(currentItem.getTitle());
        txtDescription.setText(currentItem.getDescription());
        Glide.with(context).load(currentItem.getImageUrl()).into(imgThumbnail);
    }
}
