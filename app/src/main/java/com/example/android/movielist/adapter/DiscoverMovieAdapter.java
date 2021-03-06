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
 * Created by DITHA on 23/08/2017.
 */

public class DiscoverMovieAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<ResultsItem> resultsItems;

    public DiscoverMovieAdapter(List<ResultsItem> resultsItems) {
        this.resultsItems = resultsItems;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover_movie, parent, false);
        MovieListViewHolder viewHolder = new MovieListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, final int position) {
        ResultsItem news = resultsItems.get(position);
        holder.bind(news);
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
