package com.example.android.movielist.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movielist.R;
import com.example.android.movielist.model.trailer.ResultsItemTrailer;
import com.example.android.movielist.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 30/08/2017.
 */

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTrailerNum)
    TextView TV_TRAILER_NUM;
    @BindView(R.id.imgTrailer)
    ImageView IMG_TRAILER;

    public TrailerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ResultsItemTrailer item){
        TV_TRAILER_NUM.setText(item.getName());
        Glide.with(itemView.getContext()).load(Utility.URL_IMAGE_YOUTUBE + item.getKey() + Utility.IMAGE_QUALITY).into(IMG_TRAILER);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utility.URL_YOUTUBE + item.getKey()));
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
