package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by smh on 2017-05-23.
 */
//GPS 위도 경도 받고
    //input에서 입력 음식점 정보를 받고
    //
public class DatabaseManager {
    //


    private Activity _activity;
    private SQLiteDatabase _db;

    public DatabaseManager(Activity activity) {
        _activity = activity;
    }

    public void createDb() {
        DatabaseHelper databaseHelper = new DatabaseHelper(_activity);
        _db = databaseHelper.getWritableDatabase();
    }

    public void insertRestaurantData() {
        _db.execSQL(getSqlQuery());
    }

    public void insertReviewData() {
        _db.execSQL(getSqlQuery());
    }

    public String getSqlQuery() {
        String query = "";
        return query;
    }
}
