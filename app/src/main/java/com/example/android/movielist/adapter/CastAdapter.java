package com.example.android.movielist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movielist.R;
import com.example.android.movielist.model.cast.CastItem;
import com.example.android.movielist.viewholder.CastViewHolder;

import java.util.List;

/**
 * Created by DITHA on 31/08/2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder> {

    private List<CastItem> castItems;

    public CastAdapter(List<CastItem> castItems) {
        this.castItems = castItems;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cast, parent, false);
        CastViewHolder viewHolder = new CastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        CastItem item = castItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return castItems.size();
    }

    public void setData(List<CastItem> datas){
        this.castItems.clear();
        castItems.addAll(datas);
        notifyDataSetChanged();
    }
}
