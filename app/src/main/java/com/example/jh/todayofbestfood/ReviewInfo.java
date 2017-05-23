package com.example.jh.todayofbestfood;

/**
 * Created by jh on 2017-05-23.
 */

public class ReviewInfo {
    private String _restaurant_name;
    private String _food_postscript;
    private float _restaurant_add_grade;

    public String get_restaurant_name() {
        return _restaurant_name;
    }

    public void set_restaurant_name(String _restaurant_name) {
        this._restaurant_name = _restaurant_name;
    }

    public String get_food_postscript() {
        return _food_postscript;
    }

    public void set_food_postscript(String _food_postscript) {
        this._food_postscript = _food_postscript;
    }

    public float get_restaurant_add_grade() {
        return _restaurant_add_grade;
    }

    public void set_restaurant_add_grade(float _restaurant_add_grade) {
        this._restaurant_add_grade = _restaurant_add_grade;
    }
}
