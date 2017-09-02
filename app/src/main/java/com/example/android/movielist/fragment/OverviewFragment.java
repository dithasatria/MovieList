package com.example.android.movielist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movielist.BuildConfig;
import com.example.android.movielist.R;
import com.example.android.movielist.model.APIResponseDetailMovie;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.rest.APIClient;
import com.example.android.movielist.rest.APIService;
import com.example.android.movielist.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.media.MediaFormat.KEY_LANGUAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    private ResultsItem item;

    private long movie_id;

    @BindView(R.id.tvOverview) TextView TV_OVERVIEW;
    @BindView(R.id.tvProductionCompanies) TextView TV_PRODUCTION_COMPANY;
    @BindView(R.id.tvProductionContries) TextView TV_PRODUCTION_COUNTRY;
    @BindView(R.id.tvReleaseDate) TextView TV_RELEASE_DATE;
    @BindView(R.id.ratingBar) RatingBar RATING_BAR;
    @BindView(R.id.tvVoteAverage) TextView TV_VOTE_AVERAGE;
    @BindView(R.id.tvVoteCount) TextView TV_VOTE_COUNT;
    //@BindView(R.id.swipeRefreshFragmentOverview) SwipeRefreshLayout SWIPE_REFRESH;

    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, v);

        item = getActivity().getIntent().getParcelableExtra("dataMovie");
        movie_id = item.getId();

        TV_OVERVIEW.setText(Utility.OVERVIEW);
        RATING_BAR.setNumStars(5);

        getData();

        /*
        if(Utility.PRODUCTION_COMPANY == null && Utility.PRODUCTION_COUNTRY == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
        else{
            loadData();
        }*/

        /*
        SWIPE_REFRESH.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                //Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
            }
        });*/

        return v;

    }

    private void getData(){
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        final Call<APIResponseDetailMovie> apiResponseCall = apiService.getMovieId(movie_id, BuildConfig.API_KEY, KEY_LANGUAGE);

        apiResponseCall.enqueue(new Callback<APIResponseDetailMovie>() {
            @Override
            public void onResponse(Call<APIResponseDetailMovie> call, Response<APIResponseDetailMovie> response) {
                APIResponseDetailMovie apiResponse = response.body();
                if(apiResponse != null){
                    TV_PRODUCTION_COMPANY.setText( apiResponse.getProductionCompanies().get(0).getName().toString());
                    TV_PRODUCTION_COUNTRY.setText(apiResponse.getProductionCountries().get(0).getName().toString());
                    TV_RELEASE_DATE.setText(apiResponse.getReleaseDate());
                    float rating = (float) apiResponse.getVoteAverage();
                    RATING_BAR.setRating(rating / 2);
                    TV_VOTE_AVERAGE.setText(String.valueOf(apiResponse.getVoteAverage()));
                    TV_VOTE_COUNT.setText("From " + apiResponse.getVoteCount() + " votes");

                    for (int i = 0; i < apiResponse.getGenres().size(); i++){
                        Toast.makeText(getActivity(), apiResponse.getGenres().get(i).getName(), Toast.LENGTH_SHORT).show();
                    }


                    //Toast.makeText(getContext(), apiResponse.getOverview(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponseDetailMovie> call, Throwable t) {
                Toast.makeText(getActivity(), "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
