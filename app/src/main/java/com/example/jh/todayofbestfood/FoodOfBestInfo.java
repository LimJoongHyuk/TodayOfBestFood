package com.example.jh.todayofbestfood;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jh on 2017-05-22.
 */

public class FoodOfBestInfo{
    private String _restaurant_name;
    private String _restaurant_address;
    private String _restaurant_recommend_food;
    private float _restaurant_grade;//ratingBar 평점
    private String _food_postscript;//한줄 평



    public String getRestaurant_name() {
        return _restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        _restaurant_name = restaurant_name;
    }

    public String getRestaurant_address() {
        return _restaurant_address;
    }

    public void setRestaurant_address(String restaurant_address) {
        _restaurant_address = restaurant_address;
    }

    public String getRestaurant_recommend_food() {
        return _restaurant_recommend_food;
    }

    public void setRestaurant_recommend_food(String restaurant_recommend_food) {
        _restaurant_recommend_food = restaurant_recommend_food;
    }

    public float getRestaurant_grade() {
        return _restaurant_grade;
    }

    public void setRestaurant_grade(float restaurant_grade) {
        _restaurant_grade = restaurant_grade;
    }

    public String getFood_postscript() {
        return _food_postscript;
    }

    public void setFood_postscript(String food_postscript) {
        _food_postscript = food_postscript;
    }
}
