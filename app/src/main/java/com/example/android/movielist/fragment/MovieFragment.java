package com.example.android.movielist.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.movielist.BuildConfig;
import com.example.android.movielist.R;
import com.example.android.movielist.adapter.DiscoverMovieAdapter;
import com.example.android.movielist.model.APIResponse;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.rest.APIClient;
import com.example.android.movielist.rest.APIService;
import com.example.android.movielist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieFragment extends Fragment {

    @BindView(R.id.rvDiscoverMovie) RecyclerView RV_DISCOVER_MOVIE;
    @BindView(R.id.swiperefresh) SwipeRefreshLayout SWIPE_REFRESH;

    private DiscoverMovieAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();
    private ProgressDialog progressDialog;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, v);

        progressDialog = new ProgressDialog(getActivity());

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());

        RV_DISCOVER_MOVIE.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));

        adapter = new DiscoverMovieAdapter(items);

        RV_DISCOVER_MOVIE.setAdapter(adapter);

        getData();

        SWIPE_REFRESH.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        return v;
    }

    private void getData(){
        //progressDialog.setMessage("Loading....");
        //progressDialog.show();
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        final Call<APIResponse> apiResponseCall = apiService.getTopMovie(BuildConfig.API_KEY);

        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                if(apiResponse != null){
                    items = apiResponse.getResults();
                    adapter.setData(items);
                }
                Log.d("Movie data: ", items.toString());
                Log.d("Movie cek link: ", apiResponseCall.toString());
                progressDialog.hide();
                SWIPE_REFRESH.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                SWIPE_REFRESH.setRefreshing(false);
            }
        });
    }
}
