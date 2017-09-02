package com.example.android.movielist.database;

import android.provider.BaseColumns;

/**
 * Created by DITHA on 01/09/2017.
 */

public class DBContract {
    public static class MovieContract implements BaseColumns {
        public static final String TABLE_NAME = "movie_favorites";

        public static final String _ID = "id";
        public static final String ID_MOVIE = "id_movie";
        public static final String TITLE = "title";
        public static final String RATING = "rating";

    }
}
