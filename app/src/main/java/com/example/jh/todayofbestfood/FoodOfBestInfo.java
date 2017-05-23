package com.example.jh.todayofbestfood;

/**
 * Created by jh on 2017-05-22.
 */

public class FoodOfBestInfo{
    private String _restaurant_name;
    private String _restaurant_address;
    private String _restaurant_recommend_food;
    private float _restaurant_grade;//ratingBar 평점
    private String _restaurant_latitude; // 위도
    private String _restaurant_longitude; // 경도
    private String _food_image_name; // 이미지 이름


    public String get_restaurant_latitude() {
        return _restaurant_latitude;
    }

    public void set_restaurant_latitude(String _restaurant_latitude) {
        this._restaurant_latitude = _restaurant_latitude;
    }

    public String get_restaurant_longitude() {
        return _restaurant_longitude;
    }

    public void set_restaurant_longitude(String _restaurant_longitude) {
        this._restaurant_longitude = _restaurant_longitude;
    }

    public String get_food_image_name() {
        return _food_image_name;
    }

    public void set_food_image_name(String _food_image_name) {
        this._food_image_name = _food_image_name;
    }

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


}
