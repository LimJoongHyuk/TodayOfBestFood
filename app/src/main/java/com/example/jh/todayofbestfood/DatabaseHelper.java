package com.example.jh.todayofbestfood;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import static java.sql.DriverManager.println;

/**
 * Created by smh on 2017-05-23.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "TodayOfBestFood";
    private static int DB_VERSION = 1;
    private DatabaseQuery db_query;

    public static final String REGISTER_KEY = "REGISTER";
    public static final String REVIEW_KEY = "REVIEW";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        println("버전" + oldVersion + " 에서 버전" + newVersion + " 으로 다운데이트 되었습니다.");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db_query = new DatabaseQuery();
            db.execSQL(db_query.onCreateTable_restaurant().toString());
            db.execSQL(db_query.onCreateTable_review().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("버전" + oldVersion + " 에서 버전" + newVersion + " 으로 업데이트 되었습니다.");
    }

    public void createInputTable(){

    }

    public void createReviewTable(){

    }

    public void getReviewParcelable(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras();

            ReviewParcelable reviewParcelable = (ReviewParcelable)bundle.getParcelable(REVIEW_KEY);

            System.out.println("전달받은 데이터 : ");
        }
    }

}
