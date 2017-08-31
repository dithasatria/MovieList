package com.example.android.movielist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movielist.R;
import com.example.android.movielist.model.review.ResultsItem;
import com.example.android.movielist.viewholder.ReviewViewHolder;

import java.util.List;

/**
 * Created by DITHA on 31/08/2017.
 */

public class TampilReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<ResultsItem> resultsItems;

    public TampilReviewAdapter(List<ResultsItem> resultsItems) {
        this.resultsItems = resultsItems;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tampil_review, parent, false);
        ReviewViewHolder viewHolder = new ReviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        ResultsItem item = resultsItems.get(position);
        holder.bind(item);
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
