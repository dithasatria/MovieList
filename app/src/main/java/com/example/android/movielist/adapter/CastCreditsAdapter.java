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
import com.example.android.movielist.model.cast.cast_credits.CastCreditsItem;
import com.example.android.movielist.util.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 06/09/2017.
 */

public class CastCreditsAdapter extends RecyclerView.Adapter<CastCreditsAdapter.CastCreditsViewHolder> {

    private List<CastCreditsItem> castCreditsItems;

    public CastCreditsAdapter(List<CastCreditsItem> castCreditsItems) {
        this.castCreditsItems = castCreditsItems;
    }

    @Override
    public CastCreditsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover_movie, parent, false);
        CastCreditsViewHolder viewHolder = new CastCreditsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CastCreditsViewHolder holder, int position) {
        CastCreditsItem news = castCreditsItems.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return castCreditsItems.size();
    }

    public void setData(List<CastCreditsItem> datas){
        this.castCreditsItems.clear();
        castCreditsItems.addAll(datas);
        notifyDataSetChanged();
    }

    public static class CastCreditsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgDiscoverMovie)
        ImageView IMG_DISCOVER_MOVIE;
        @BindView(R.id.tvMovieName)
        TextView TV_MOVIE_NAME;
        @BindView(R.id.tvStars)
        TextView TV_STARS;

        public CastCreditsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final CastCreditsItem item) {
            Glide.with(itemView.getContext()).load(Utility.URL_IMAGE + item.getPosterPath()).into(IMG_DISCOVER_MOVIE);
            TV_MOVIE_NAME.setText(item.getTitle());
            TV_STARS.setText(String.valueOf(item.getVoteAverage()));

            final ResultsItem items = new ResultsItem();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(itemView.getContext(), DetailMovieActivity.class);

                    items.setId(item.getId());
                    items.setTitle(item.getTitle());
                    items.setOverview(item.getOverview());
                    items.setPosterPath(item.getPosterPath());
                    items.setBackdropPath(item.getBackdropPath());
                    in.putExtra(Utility.KEY_INTENT, items);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation((Activity) itemView.getContext(), IMG_DISCOVER_MOVIE, itemView.getContext().getString(R.string.activity_image_trans));
                        itemView.getContext().startActivity(in, options.toBundle());
                    } else {
                        itemView.getContext().startActivity(in);
                    }

                }
            });
        }
    }
}
