package com.example.jh.todayofbestfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by smh on 2017-05-23.
 */
//GPS 위도 경도 받고
    //input에서 입력 음식점 정보를 받고
    //
public class DatabaseManager {


    private DatabaseHelper _databaseHelper;
    private SQLiteDatabase _sqLiteDatabase;


    Context _context;

    public DatabaseManager(Context context) {
        this._context = context;
    }

    private boolean openDataBase(){
        _databaseHelper = new DatabaseHelper(_context);
        _sqLiteDatabase = _databaseHelper.getWritableDatabase();
        return true;
    }


    private void getLocationInfo(){
        //위도,경도
    }

    private void getrestaurantInfo(){
        //음식점 이름, 음식 정보, 추천 메뉴, 평점, 이미지 이름,
    }

    //등록된 맛집에 방문 후 리뷰정보

    private void getVisitReviewInfo(){

    }
}
