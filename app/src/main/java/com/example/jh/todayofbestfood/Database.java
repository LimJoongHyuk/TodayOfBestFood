package com.example.jh.todayofbestfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by smh on 2017-05-22.
 */

public class Database {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    private static String TABLE_NAME = "student";
    private Context _context;

    private void createDb(Context context) {
        _context = context;
        dbHelper = new DatabaseHelper(_context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB 생성 실패");
        }
    }

}