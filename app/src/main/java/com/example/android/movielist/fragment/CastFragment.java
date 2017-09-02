package com.example.android.movielist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.movielist.BuildConfig;
import com.example.android.movielist.R;
import com.example.android.movielist.adapter.CastAdapter;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.model.cast.APIResponseCast;
import com.example.android.movielist.model.cast.CastItem;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CastFragment extends Fragment {

    @BindView(R.id.rvCast) RecyclerView RV_CAST;

    private CastAdapter adapter;
    private List<CastItem> items = new ArrayList<>();
    private ResultsItem item;
    private long movie_id;

    public CastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_cast, container, false);
        ButterKnife.bind(this, v);

        item = getActivity().getIntent().getParcelableExtra(Utility.KEY_INTENT);
        movie_id = item.getId();

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());

        RV_CAST.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));

        adapter = new CastAdapter(items);

        RV_CAST.setAdapter(adapter);

        getData();

        return v;
    }

    private void getData(){
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        final Call<APIResponseCast> apiResponseCastCall = apiService.getCast(movie_id, BuildConfig.API_KEY, Utility.KEY_LANGUAGE);

        apiResponseCastCall.enqueue(new Callback<APIResponseCast>() {
            @Override
            public void onResponse(Call<APIResponseCast> call, Response<APIResponseCast> response) {
                APIResponseCast apiResponseCast = response.body();
                if(apiResponseCast != null){
                    items = apiResponseCast.getCast();
                    adapter.setData(items);
                }

                Log.d("cast data", items.toString());
                Log.d("cast cek link", apiResponseCastCall.toString());
            }

            @Override
            public void onFailure(Call<APIResponseCast> call, Throwable t) {
                Toast.makeText(getActivity(), "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
