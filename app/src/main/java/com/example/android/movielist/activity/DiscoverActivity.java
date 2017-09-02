package com.example.android.movielist.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverActivity extends AppCompatActivity {

    @BindView(R.id.rvDiscover) RecyclerView RV_DISCOVER;
    @BindView(R.id.spYear) Spinner SP_YEAR;
    @BindView(R.id.spSortBy) Spinner SP_SORT_BY;
    ///@BindView(R.id.etGenre) EditText ET_GENRE;

    private DiscoverMovieAdapter adapter;
    private List<ResultsItem> items = new ArrayList<>();
    Context context;


   /* protected ArrayList<Integer> selectGenre;
    protected CharSequence[] genreList = {"Blackberry Z3", "Blackberry Z10", "Blackberry Z30"};
    boolean[] selectedGenre = new boolean[genreList.length];
    */

    String sortVal;
    String spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        ButterKnife.bind(this);

        context = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int mNoOfColumns = Utility.calculateNoOfColumns(context);

        RV_DISCOVER.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));

        adapter = new DiscoverMovieAdapter(items);

        RV_DISCOVER.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        // BETA
        SP_YEAR.setSelection(((ArrayAdapter<String>)SP_YEAR.getAdapter()).getPosition(year));
        SP_YEAR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner = SP_YEAR.getItemAtPosition(i).toString();
                getData(spinner, sortVal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SP_SORT_BY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        sortVal = "popularity.desc";
                        break;
                    case 1:
                        sortVal = "popularity.asc";
                        break;
                    case 2:
                        sortVal = "vote_average.desc";
                        break;
                    case 3:
                        sortVal = "vote_average.asc";
                        break;
                    case 4:
                        sortVal = "revenue.asc";
                        break;
                    case 5:
                        sortVal = "revenue.desc";
                        break;
                    case 6:
                        sortVal = "original_title.asc";
                        break;
                    case 7:
                        sortVal = "original_title.desc";
                        break;
                }
                getData(spinner, sortVal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*
        ET_GENRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGenre = new ArrayList<Integer>();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Genre")
                        .setMultiChoiceItems(genreList, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                                selectedGenre[i] = isChecked;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String dataPilih = "";
                                for (int f = 0; f < genreList.length; f++) {
                                    if (selectedGenre[f]) {
                                        dataPilih += genreList[f] + ", ";
                                        selectedGenre[f] = false;
                                    }
                                }

                                Toast.makeText(context, dataPilih, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();

            }
        }); */
    }

    private void getData(String year, String sortBy){
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        final Call<APIResponse> apiResponseCall = apiService.getMovie(BuildConfig.API_KEY, year, sortBy);

        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                if(apiResponse != null){
                    items = apiResponse.getResults();
                    adapter.setData(items);
                }
                Log.d("data", items.toString());
                Log.d("cek link", apiResponseCall.toString());
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(DiscoverActivity.this, "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
}
