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
import com.example.android.movielist.adapter.TampilReviewAdapter;
import com.example.android.movielist.model.review.APIResponseReview;
import com.example.android.movielist.model.review.ResultsItem;
import com.example.android.movielist.rest.APIClientReviewMovie;
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
public class ReviewFragment extends Fragment {

    @BindView(R.id.rvTampilReview) RecyclerView RV_TAMPIL_REVIEW;

    private TampilReviewAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();

    private com.example.android.movielist.model.ResultsItem item;

    private long movie_id;

    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, v);

        item = getActivity().getIntent().getParcelableExtra("dataMovie");
        movie_id = item.getId();

        RV_TAMPIL_REVIEW.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new TampilReviewAdapter(items);

        RV_TAMPIL_REVIEW.setAdapter(adapter);

        getData();

        return v;
    }

    private void getData(){
        APIService apiService = APIClientReviewMovie.getRetrofitClientReview(movie_id).create(APIService.class);
        final Call<APIResponseReview> apiResponseCall = apiService.getReviewMovie(BuildConfig.API_KEY, Utility.KEY_LANGUAGE);

        apiResponseCall.enqueue(new Callback<APIResponseReview>() {
            @Override
            public void onResponse(Call<APIResponseReview> call, Response<APIResponseReview> response) {
                APIResponseReview apiResponseTrailerMovie = response.body();
                if(apiResponseTrailerMovie != null){
                    items = apiResponseTrailerMovie.getResults();
                    adapter.setData(items);
                }
                Log.d("review data", items.toString());
                Log.d("review cek link", apiResponseCall.toString());
            }

            @Override
            public void onFailure(Call<APIResponseReview> call, Throwable t) {
                Toast.makeText(getActivity(), "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
