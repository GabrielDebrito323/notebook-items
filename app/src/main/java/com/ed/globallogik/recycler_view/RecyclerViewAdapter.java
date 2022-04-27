package com.ed.globallogik.recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ed.globallogik.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ReusableViewHolder> {

    Context context;
    OnTouchListener onTouchListener;
    CellType cellType;
    private ArrayList<RecyclerItem> list = new ArrayList<>();

    public RecyclerViewAdapter(CellType cellType, Context context, OnTouchListener onTouchListener) {
        this.cellType = cellType;
        this.context = context;
        this.onTouchListener = onTouchListener;
    }

    @Override
    public int getItemViewType(int position) {
        int res;
        
        switch (cellType){
            case ITEM:
                res = R.layout.cell_item;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cellType);
        }
        
        return res;
    }

    @NonNull
    @Override
    public ReusableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReusableViewHolder vh;
        switch (cellType) {
            case ITEM:
                vh = new ItemViewHolder(LayoutInflater.from(context).inflate(
                        R.layout.cell_item,
                        parent,
                        false),
                        context,
                        onTouchListener);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cellType);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReusableViewHolder holder, int position) {
        RecyclerItem item = list.get(position);
        holder.bindView(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<RecyclerItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public enum CellType {
        ITEM;
    }

}
