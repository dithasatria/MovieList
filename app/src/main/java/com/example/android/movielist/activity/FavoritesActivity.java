package com.example.android.movielist.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.movielist.R;
import com.example.android.movielist.adapter.FavoriteAdapter;
import com.example.android.movielist.database.DBOpenHelper;
import com.example.android.movielist.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {

    @BindView(R.id.rvFavorites) RecyclerView RV_FAVORITES;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout SWIPE_REFRESH;
    @BindView(R.id.tvNoResult)
    TextView TV_NO_RESULT;

    private FavoriteAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();
    private ResultsItem item;

    private DBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favorites");

        dbOpenHelper = new DBOpenHelper(getApplicationContext());

        RV_FAVORITES.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        setAdapter();
        SWIPE_REFRESH.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter(){
        adapter = new FavoriteAdapter(items);

        RV_FAVORITES.setAdapter(new FavoriteAdapter(dbOpenHelper.getFavoriteMovie()));
        if(dbOpenHelper.getFavoriteMovie() != null){
            TV_NO_RESULT.setVisibility(View.INVISIBLE);
        }
        else{
            TV_NO_RESULT.setVisibility(View.VISIBLE);
        }
        SWIPE_REFRESH.setRefreshing(false);
    }
}
