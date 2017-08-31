package com.example.android.movielist.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movielist.R;
import com.example.android.movielist.model.cast.CastItem;
import com.example.android.movielist.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 31/08/2017.
 */

public class CastViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgCast) ImageView IMG_CAST;
    @BindView(R.id.tvCastName) TextView TV_CAST_NAME;
    @BindView(R.id.tvCharacter) TextView TV_CHARACTER;

    public CastViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final CastItem castItem){
        Glide.with(itemView.getContext()).load(Utility.URL_IMAGE + castItem.getProfilePath()).into(IMG_CAST);
        TV_CAST_NAME.setText(castItem.getName());
        TV_CHARACTER.setText("as " + castItem.getCharacter());
    }
}
