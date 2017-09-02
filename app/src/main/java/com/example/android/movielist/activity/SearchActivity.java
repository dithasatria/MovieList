package com.example.android.movielist.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import static com.example.android.movielist.R.id.action_search;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.rvSearch) RecyclerView RV_SEARCH;

    private DiscoverMovieAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext());

        RV_SEARCH.setLayoutManager(new GridLayoutManager(getApplicationContext(), mNoOfColumns));

        adapter = new DiscoverMovieAdapter(items);

        RV_SEARCH.setAdapter(adapter);

        getSupportActionBar().setTitle("Search Movie");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final MenuItem searchItem = menu.findItem(action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData(String query){
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        final Call<APIResponse> apiResponseCall = apiService.getDataMovie(BuildConfig.API_KEY, Utility.KEY_LANGUAGE, query);

        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                if(apiResponse != null){
                    items = apiResponse.getResults();
                    adapter.setData(items);

                    //Toast.makeText(getContext(), apiResponse.getOverview(), Toast.LENGTH_SHORT).show();
                }

                Log.d("data search: ", items.toString());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
