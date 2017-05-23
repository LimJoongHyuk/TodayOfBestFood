package com.example.jh.todayofbestfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by smh on 2017-05-23.
 */

public class DatabaseHandler  {


    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private DatabaseQuery db_query;
    private ArrayList<FoodOfBestInfo> foodDataInfo ;
    Context context;


    public DatabaseHandler(Context context) {
        this.context = context;

    }

    private boolean openDataBase(){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return true;
    }
    private void insertData(){
        db_query = new DatabaseQuery();
        db.execSQL(db_query.onInsertRestaurantData().toString());


    }
}
