package com.example.android.movielist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.movielist.R;
import com.example.android.movielist.model.ResultsItem;

public class SearchActivity extends AppCompatActivity {

    private ResultsItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        item = (ResultsItem) getIntent().getParcelableExtra("dataMovie");
        String title = item.getTitle();

        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
