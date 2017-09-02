package com.example.android.movielist.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movielist.R;
import com.example.android.movielist.model.trailer.ResultsItemTrailer;
import com.example.android.movielist.viewholder.TrailerViewHolder;

import java.util.List;

/**
 * Created by DITHA on 30/08/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private List<ResultsItemTrailer> resultsItemTrailers;
    private Activity activity;

    public TrailerAdapter(List<ResultsItemTrailer> resultsItemTrailers, Activity activity) {
        this.resultsItemTrailers = resultsItemTrailers;
        this.activity = activity;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer_movie, parent, false);
        TrailerViewHolder viewHolder = new TrailerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        ResultsItemTrailer item = resultsItemTrailers.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return resultsItemTrailers.size();
    }

    public void setData(List<ResultsItemTrailer> datas){
        if(resultsItemTrailers != null){
            this.resultsItemTrailers.clear();
            resultsItemTrailers.addAll(datas);
        }
        else{
            resultsItemTrailers = datas;
        }
        notifyDataSetChanged();
    }
}
