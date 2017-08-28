package com.example.android.movielist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.movielist.R;
import com.example.android.movielist.fragment.OverviewFragment;
import com.example.android.movielist.model.ResultsItem;
import com.example.android.movielist.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DITHA on 24/08/2017.
 */

public class ParalaxActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar TOOLBAR;
    @BindView(R.id.fab) FloatingActionButton FAB;
    @BindView(R.id.nestedScrollView) NestedScrollView NESTED_SCROLL;
    @BindView(R.id.imgBackdrop) ImageView IMG_BACKDROP;
    @BindView(R.id.imgPoster) ImageView IMG_POSTER;
    @BindView(R.id.tvTitle) TextView TV_TITLE;

    private static final String KEY_EXTRA_MOVIE = "movie";

    private ResultsItem item;

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

        item = getIntent().getParcelableExtra("dataMovie");
        String title = item.getTitle();

        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();

        viewPager = (ViewPager) findViewById(R.id.viewpager2);
        viewPager.setAdapter(new sliderAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        NESTED_SCROLL.setFillViewport (true);

        collapsingToolbar();
        loadDetailMovie();
    }

    private class sliderAdapter extends FragmentPagerAdapter {

        final  String tabs[]={"OVERVIEW", "TRAILER", "CAST", "REVIEW"};
        public sliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            OverviewFragment view2 = new OverviewFragment();
            return view2;
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
        Glide.with(getApplicationContext()).load(Utility.URL_IMAGE + item.getBackdropPath()).into(IMG_BACKDROP);
        Glide.with(getApplicationContext()).load(Utility.URL_IMAGE + item.getPosterPath()).into(IMG_POSTER);

        TV_TITLE.setText(item.getTitle());
        Utility.overView = item.getOverview();

        Toast.makeText(this, item.getGenreIds().get(1).toString(), Toast.LENGTH_SHORT).show();
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
                    collapsingToolbarLayout.setTitle("Title");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

}
