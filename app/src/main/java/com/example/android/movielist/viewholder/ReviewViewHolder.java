package com.example.android.movielist.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.movielist.R;
import com.example.android.movielist.model.review.ResultsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 31/08/2017.
 */

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvReviewAuthor) TextView TV_REVIEW_AUTHOR;
    @BindView(R.id.tvReviewContent) TextView TV_REVIEW_CONTENT;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ResultsItem item){
        TV_REVIEW_AUTHOR.setText(item.getAuthor());
        TV_REVIEW_CONTENT.setText(item.getContent());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
