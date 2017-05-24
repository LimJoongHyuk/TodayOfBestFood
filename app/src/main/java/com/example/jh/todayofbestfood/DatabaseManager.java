package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;

/**
 * Created by smh on 2017-05-23.
 */
//GPS 위도 경도 받고
    //input에서 입력 음식점 정보를 받고
    //
public class DatabaseManager {

    private String _restaurantName;
    private String _recommendMenu;
    private float _restaurantgrade;
    private String _latitude;
    private String _longitude;
    private String _foodImgName;
    private String _foodTagName;


    private Activity _activity;

    public DatabaseManager(Activity activity) {
        _activity = activity;
    }

    //음식정 등록정보 가져오기
    public void getRestaurantRegisterInfo(String name, String recommendmenu,
                                           float grade, String latitude,
                                           String longitude, String foodimg,
                                           String foodtag){

        _restaurantName = name;
        _recommendMenu = recommendmenu;
        _restaurantgrade = grade;
        _latitude = latitude;
        _longitude = longitude;
        _foodImgName = foodimg;
        _foodTagName = foodtag;

    }



}
