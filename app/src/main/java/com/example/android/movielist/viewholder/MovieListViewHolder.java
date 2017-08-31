package com.example.android.movielist.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movielist.R;
import com.example.android.movielist.activity.DetailMovieActivity;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 24/08/2017.
 */

public class MovieListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imgDiscoverMovie)
    ImageView IMG_DISCOVER_MOVIE;
    @BindView(R.id.tvMovieName)
    TextView TV_MOVIE_NAME;
    @BindView(R.id.tvStars) TextView TV_STARS;

    public MovieListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ResultsItem item){
        Glide.with(itemView.getContext()).load(Utility.URL_IMAGE + item.getPosterPath()).into(IMG_DISCOVER_MOVIE);
        TV_MOVIE_NAME.setText(item.getTitle());
        TV_STARS.setText(String.valueOf(item.getVoteAverage()));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(itemView.getContext(), DetailMovieActivity.class);
                in.putExtra("dataMovie", item);
                Utility.idMovie = item.getId();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) itemView.getContext(), IMG_DISCOVER_MOVIE, itemView.getContext().getString(R.string.activity_image_trans));
                    itemView.getContext().startActivity(in, options.toBundle());
                }
                else{
                    itemView.getContext().startActivity(in);
                }

            }
        });
    }
}
