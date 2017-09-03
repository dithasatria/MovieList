package com.example.android.movielist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.movielist.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DITHA on 01/09/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iaknews";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NEWS =
            "CREATE TABLE " + DBContract.MovieContract.TABLE_NAME + " (" +
                    DBContract.MovieContract._ID + " INTEGER PRIMARY KEY, " +
                    DBContract.MovieContract.ID_MOVIE + " TEXT," +
                    DBContract.MovieContract.POSTER_PATH + " TEXT," +
                    DBContract.MovieContract.BACKDROP_PATH + " TEXT," +
                    DBContract.MovieContract.TITLE + " TEXT," +
                    DBContract.MovieContract.RATING + " TEXT," +
                    DBContract.MovieContract.OVERVIEW + " TEXT" +
                    "); ";

    private static final String SQL_DROP_TABLE_NEWS  =
            "DROP TABLE IF EXISTS " + DBContract.MovieContract.TABLE_NAME;

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DROP_TABLE_NEWS);
        onCreate(db);
    }

    public long saveNewsItem(ResultsItem articlesItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DBContract.MovieContract.ID_MOVIE, articlesItem.getId());
        cv.put(DBContract.MovieContract.POSTER_PATH, articlesItem.getPosterPath());
        cv.put(DBContract.MovieContract.BACKDROP_PATH, articlesItem.getBackdropPath());
        cv.put(DBContract.MovieContract.TITLE, articlesItem.getTitle());
        cv.put(DBContract.MovieContract.RATING, articlesItem.getVoteAverage());
        cv.put(DBContract.MovieContract.OVERVIEW, articlesItem.getOverview());

        long rowId = db.insert(DBContract.MovieContract.TABLE_NAME, null, cv);
        db.close();

        Log.d("Open Helper", "isSaveSuccess ? " +String.valueOf(rowId > 0));

        return rowId;
    }

    public boolean deleteNewsItem(String newsUrl){
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = DBContract.MovieContract.ID_MOVIE + "=?";
        String[] whereArgs = new String[]{ newsUrl };
        int rowEffected = db.delete(DBContract.MovieContract.TABLE_NAME, whereClause, whereArgs);

        db.close();

        return rowEffected > 0;
    }

    public boolean isNewsSavedAsFavorite(String newsUrl){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DBContract.MovieContract.TABLE_NAME,
                null,
                DBContract.MovieContract.ID_MOVIE + "=?",
                new String[]{ newsUrl },
                null,
                null,
                null
        );

        int totalRow = cursor.getCount();
        db.close();
        return totalRow > 0;
    }

    public List<ResultsItem> getFavoriteMovie(){
        SQLiteDatabase db = this.getReadableDatabase();

        /* select * from table*/
        Cursor cursor = db.query(
                DBContract.MovieContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        int resultCount = cursor.getCount();

        List<ResultsItem> articlesItems = new ArrayList<>();
        if (resultCount > 0){
            cursor.moveToFirst();
            do {
                String id_movie = cursor.getString(cursor.getColumnIndex(DBContract.MovieContract.ID_MOVIE));
                String poster_path = cursor.getString(cursor.getColumnIndex(DBContract.MovieContract.POSTER_PATH));
                String backdrop_path = cursor.getString(cursor.getColumnIndex(DBContract.MovieContract.BACKDROP_PATH));
                String title = cursor.getString(cursor.getColumnIndex(DBContract.MovieContract.TITLE));
                String rating = cursor.getString(cursor.getColumnIndex(DBContract.MovieContract.RATING));
                String overview = cursor.getString(cursor.getColumnIndex(DBContract.MovieContract.OVERVIEW));

                Double ratingDouble = Double.parseDouble(rating);

                ResultsItem item = new ResultsItem();
                item.setId(Integer.parseInt(id_movie));
                item.setPosterPath(poster_path);
                item.setBackdropPath(backdrop_path);
                item.setTitle(title);
                item.setVoteAverage(ratingDouble);
                item.setOverview(overview);

                articlesItems.add(item);
                Log.d("getFavoriteNews: news ", item.getTitle());
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return articlesItems;
    }
}
