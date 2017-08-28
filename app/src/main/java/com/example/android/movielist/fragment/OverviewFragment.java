package com.example.android.movielist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movielist.R;
import com.example.android.movielist.adapter.DiscoverMovieAdapter;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    @BindView(R.id.tvOverview) TextView TV_OVERVIEW;

    private DiscoverMovieAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, v);

        TV_OVERVIEW.setText(Utility.overView);

        return v;
    }

}
