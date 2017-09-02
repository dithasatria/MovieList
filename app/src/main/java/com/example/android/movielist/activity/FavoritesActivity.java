package com.example.android.movielist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.movielist.R;
import com.example.android.movielist.adapter.FavoriteAdapter;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {

    @BindView(R.id.rvFavorites) RecyclerView RV_FAVORITES;

    private FavoriteAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();
    private ResultsItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());

        RV_FAVORITES.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));

        adapter = new FavoriteAdapter(items);

        RV_FAVORITES.setAdapter(adapter);
    }
}
