package com.example.android.movielist.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movielist.R;
import com.example.android.movielist.activity.DetailMovieActivity;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.util.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 01/09/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<ResultsItem> resultsItems;

    public FavoriteAdapter(List<ResultsItem> resultsItems) {
        this.resultsItems = resultsItems;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        FavoriteViewHolder viewHolder = new FavoriteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        ResultsItem items = resultsItems.get(position);
        holder.bind(items);
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

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgCover) ImageView IMG_COVER;
        @BindView(R.id.tvMovieName) TextView TV_MOVIE_NAME;
        @BindView(R.id.tvStars) TextView TV_STARS;
        @BindView(R.id.tvOverview) TextView TV_OVERVIEW;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ResultsItem item){
            Glide.with(itemView.getContext()).load(Utility.URL_IMAGE + item.getPosterPath()).into(IMG_COVER);
            TV_MOVIE_NAME.setText(item.getTitle());
            TV_STARS.setText(String.valueOf(item.getVoteAverage()));
            TV_OVERVIEW.setText(item.getOverview());

            TV_MOVIE_NAME.setSelected(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(itemView.getContext(), DetailMovieActivity.class);
                    in.putExtra("dataMovie", item);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation((Activity) itemView.getContext(), IMG_COVER, itemView.getContext().getString(R.string.activity_image_trans));
                        itemView.getContext().startActivity(in, options.toBundle());
                    } else {
                        itemView.getContext().startActivity(in);
                    }
                }
            });
        }
    }
}
