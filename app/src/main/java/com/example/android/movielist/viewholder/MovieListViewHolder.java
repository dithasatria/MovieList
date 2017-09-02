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

                for (int i = 0; i < item.getGenreIds().size(); i++) {
                    switch (item.getGenreIds().get(i)) {
                        case 12:
                            Utility.genre += " Adventure,";
                            break;
                        case 14:
                            Utility.genre += " Fantasy,";
                        case 16:
                            Utility.genre += " Animation,";
                            break;
                        case 18:
                            Utility.genre += " Drama,";
                            break;
                        case 27:
                            Utility.genre += " Horror,";
                            break;
                        case 28:
                            Utility.genre += " Action,";
                            break;
                        case 36:
                            Utility.genre += " History,";
                            break;
                        case 35:
                            Utility.genre += " Comedy,";
                            break;
                        case 37:
                            Utility.genre += " Western,";
                            break;
                        case 53:
                            Utility.genre += " Thriller,";
                            break;
                        case 80:
                            Utility.genre += " Crime,";
                            break;
                        case 99:
                            Utility.genre += " Documentary,";
                            break;
                        case 878:
                            Utility.genre += " Science Fiction,";
                            break;
                        case 9648:
                            Utility.genre += " Mystery,";
                            break;
                        case 10402:
                            Utility.genre += " Music,";
                            break;
                        case 10749:
                            Utility.genre += " Romance,";
                            break;
                        case 10751:
                            Utility.genre += " Family,";
                            break;
                        case 10752:
                            Utility.genre += " War,";
                            break;
                        case 10770:
                            Utility.genre += " TV Movie,";
                            break;
                        default:
                            Utility.genre += ",";
                            break;
                    }
                }

            }
        });
    }
}
