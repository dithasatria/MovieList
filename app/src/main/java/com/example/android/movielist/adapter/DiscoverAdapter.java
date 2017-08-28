package com.example.android.movielist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movielist.R;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.viewholder.MovieListViewHolder;

import java.util.List;

/**
 * Created by DITHA on 24/08/2017.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<ResultsItem> resultsItems;

    public DiscoverAdapter(List<ResultsItem> resultsItems) {
        this.resultsItems = resultsItems;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover_movie, parent, false);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {
        ResultsItem movie = resultsItems.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public void setData(List<ResultsItem> datas){
        this.resultsItems.clear();
        resultsItems.addAll(datas);
        notifyDataSetChanged();
    }
}
