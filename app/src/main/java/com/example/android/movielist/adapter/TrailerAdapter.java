package com.example.android.movielist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movielist.R;
import com.example.android.movielist.model.trailer.ResultsItem;
import com.example.android.movielist.viewholder.TrailerViewHolder;

import java.util.List;

/**
 * Created by DITHA on 30/08/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private List<ResultsItem> resultsItems;

    public TrailerAdapter(List<ResultsItem> resultsItems) {
        this.resultsItems = resultsItems;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer_movie, parent, false);
        TrailerViewHolder viewHolder = new TrailerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        ResultsItem item = resultsItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public void setData(List<ResultsItem> datas){
        if(resultsItems != null){
            this.resultsItems.clear();
            resultsItems.addAll(datas);
        }
        else{
            resultsItems = datas;
        }
        notifyDataSetChanged();
    }
}
