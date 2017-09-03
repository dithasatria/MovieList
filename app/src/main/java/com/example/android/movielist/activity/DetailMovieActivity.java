package com.example.android.movielist.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.android.movielist.BuildConfig;
import com.example.android.movielist.R;
import com.example.android.movielist.database.DBOpenHelper;
import com.example.android.movielist.fragment.CastFragment;
import com.example.android.movielist.fragment.OverviewFragment;
import com.example.android.movielist.fragment.ReviewFragment;
import com.example.android.movielist.fragment.TrailerFragment;
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

import static com.bumptech.glide.Glide.with;

/**
 * Created by DITHA on 24/08/2017.
 */

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar TOOLBAR;
    @BindView(R.id.fab) FloatingActionButton FAB;
    @BindView(R.id.nestedScrollView) NestedScrollView NESTED_SCROLL;
    @BindView(R.id.layoutTabJudul) RelativeLayout LAYOUT_TAB_JUDUL;
    @BindView(R.id.tabLayout) TabLayout TAB_LAYOUT;
    @BindView(R.id.imgBackdrop) ImageView IMG_BACKDROP;
    @BindView(R.id.imgDiscoverMovie) ImageView IMG_POSTER;
    @BindView(R.id.tvTitle) TextView TV_TITLE;
    @BindView(R.id.tvGenreDetail) TextView TV_GENRE;

    private long movie_id;

    private ResultsItem item;
    private boolean isNewsFavorite = false;
    private DBOpenHelper dbOpenHelper;
    String genre = "";
    private int collapsingToolbarColor;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);

        setSupportActionBar(TOOLBAR);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        TV_TITLE.setSelected(true);

        dbOpenHelper = new DBOpenHelper(getApplicationContext());

        item = getIntent().getParcelableExtra("dataMovie");
        if(item == null){
            finish();
        }
        if (genre.endsWith(",")) {
            genre = genre.substring(0, genre.length() - 1);
        }
        movie_id = item.getId();

        //Toast.makeText(this, String.valueOf(movie_id), Toast.LENGTH_SHORT).show();

        viewPager = (ViewPager) findViewById(R.id.viewpager2);
        viewPager.setAdapter(new sliderAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        NESTED_SCROLL.setFillViewport (true);
        IMG_BACKDROP.setOnClickListener(this);

        collapsingToolbar();
        loadDetailMovie();
        setupFAB();
        getData();
        setupBackgroundPalette();

        dbOpenHelper = new DBOpenHelper(getApplicationContext());
        isNewsFavorite = dbOpenHelper.isNewsSavedAsFavorite(String.valueOf(item.getId()));
        if (isNewsFavorite){
            // news saved as favorite
            FAB.setImageResource(R.drawable.ic_favorite_fill);
        } else {
            FAB.setImageResource(R.drawable.ic_favorite);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBackdrop:
                Intent in = new Intent(DetailMovieActivity.this, PhotoActivity.class);
                in.putExtra("dataPhoto", item.getBackdropPath());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(this, IMG_BACKDROP, getString(R.string.activity_image_trans));
                    startActivity(in, options.toBundle());
                }
                else{
                    startActivity(in);
                }
                break;
        }
    }

    private class sliderAdapter extends FragmentPagerAdapter {

        final  String tabs[]={"OVERVIEW", "TRAILER", "CAST", "REVIEW"};
        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OverviewFragment tab0 = new OverviewFragment();
                    return tab0;
                case 1:
                    TrailerFragment tab1 = new TrailerFragment();
                    return tab1;
                case 2:
                    CastFragment tab2 = new CastFragment();
                    return tab2;
                case 3:
                    ReviewFragment tab3 = new ReviewFragment();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

    private void loadDetailMovie(){
        with(getApplicationContext()).load(Utility.URL_IMAGE + item.getBackdropPath()).into(IMG_BACKDROP);
        with(getApplicationContext()).load(Utility.URL_IMAGE + item.getPosterPath()).into(IMG_POSTER);

        TV_TITLE.setText(item.getTitle());
        //TV_GENRE.setText("");
        Utility.OVERVIEW = item.getOverview();
    }

    private void collapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbarCollapse);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(item.getTitle());
                    TOOLBAR.setBackgroundColor(collapsingToolbarColor);
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    TOOLBAR.setBackgroundColor(Color.TRANSPARENT);
                    isShow = false;
                }
            }
        });
    }

    private void setupFAB(){
        NESTED_SCROLL.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(oldScrollY > scrollY){
                    FAB.show();
                }
                else {
                    FAB.hide();
                }
            }
        });

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNewsFavorite){
                    boolean isDeleteSuccess = dbOpenHelper.deleteNewsItem(String.valueOf(item.getId()));
                    isNewsFavorite = !isDeleteSuccess;
                }
                else {
                    isNewsFavorite = dbOpenHelper.saveNewsItem(item) > 0;
                    String snackBarText = isNewsFavorite ? "Movie saved as favorite" : "Failed to favorite movie";
                    Snackbar.make(FAB, snackBarText, Snackbar.LENGTH_SHORT)
                            .show();

                }
                FAB.setImageResource(isNewsFavorite ? R.drawable.ic_favorite_fill : R.drawable.ic_favorite);
            }
        });
    }

    private void setupBackgroundPalette(){
        if(item.getPosterPath() != null) {
            Glide.with(this)
                    .asBitmap()
                    .load(Utility.URL_IMAGE + item.getPosterPath())
                    .into(new BitmapImageViewTarget(IMG_POSTER) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> anim) {
                            super.onResourceReady(bitmap, anim);
                            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    // Here's your generated palette
                                    Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();
                                    if (vibrantSwatch != null) {
                                        collapsingToolbarColor = vibrantSwatch.getRgb();
                                        LAYOUT_TAB_JUDUL.setBackgroundColor(vibrantSwatch.getRgb());
                                        TAB_LAYOUT.setBackgroundColor(vibrantSwatch.getRgb());
                                        FAB.setBackgroundTintList(ColorStateList.valueOf(vibrantSwatch.getRgb()));
                                    }
                                }
                            });
                        }
                    });
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                startActivity(new Intent(DetailMovieActivity.this, SearchActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void getData(){
        APIService apiService = APIClient.getRetrofitClient().create(APIService.class);
        final Call<APIResponseDetailMovie> apiResponseCall = apiService.getMovieId(movie_id, BuildConfig.API_KEY, Utility.KEY_LANGUAGE);

        apiResponseCall.enqueue(new Callback<APIResponseDetailMovie>() {
            @Override
            public void onResponse(Call<APIResponseDetailMovie> call, Response<APIResponseDetailMovie> response) {
                APIResponseDetailMovie apiResponse = response.body();
                if(apiResponse != null){

                    for (int i = 0; i < apiResponse.getGenres().size(); i++){
                        genre += apiResponse.getGenres().get(i).getName() + ", ";
                        TV_GENRE.setText(genre);
                    }


                    //Toast.makeText(getContext(), apiResponse.getOverview(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponseDetailMovie> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Parsing Gagal " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
