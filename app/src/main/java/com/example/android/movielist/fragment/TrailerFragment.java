package com.example.android.movielist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.movielist.BuildConfig;
import com.example.android.movielist.R;
import com.example.android.movielist.adapter.TrailerAdapter;
import com.example.android.movielist.model.trailer.APIResponseTrailerMovie;
import com.example.android.movielist.model.trailer.ResultsItem;
import com.example.android.movielist.rest.APIClientTrailerMovie;
import com.example.android.movielist.rest.APIService;
import com.example.android.movielist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrailerFragment extends Fragment {

    @BindView(R.id.rvTrailerMovie) RecyclerView RV_TRAILER_MOVIE;

    private TrailerAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();

    private long movie_id;

    private com.example.android.movielist.model.ResultsItem item;

    public TrailerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_trailer, container, false);
        ButterKnife.bind(this, v);

        item = getActivity().getIntent().getParcelableExtra("dataMovie");
        movie_id = item.getId();

        RV_TRAILER_MOVIE.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new TrailerAdapter(items);

        RV_TRAILER_MOVIE.setAdapter(adapter);

        getData();

        Toast.makeText(getActivity(), "" +movie_id, Toast.LENGTH_SHORT).show();

        return v;
    }

    private void getData(){
        APIService apiService = APIClientTrailerMovie.getRetrofitClientTrailer(movie_id).create(APIService.class);
        final Call<APIResponseTrailerMovie> apiResponseCall = apiService.getTrailerMovie(BuildConfig.API_KEY, Utility.KEY_LANGUAGE);

        apiResponseCall.enqueue(new Callback<APIResponseTrailerMovie>() {
            @Override
            public void onResponse(Call<APIResponseTrailerMovie> call, Response<APIResponseTrailerMovie> response) {
                APIResponseTrailerMovie apiResponseTrailerMovie = response.body();
                if(apiResponseTrailerMovie != null){
                    items = apiResponseTrailerMovie.getResults();
                    adapter.setData(items);
                }
                Log.d("trailer data", items.toString());
                Log.d("trailer cek link", apiResponseCall.toString());
            }

            @Override
            public void onFailure(Call<APIResponseTrailerMovie> call, Throwable t) {
                Toast.makeText(getActivity(), "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
