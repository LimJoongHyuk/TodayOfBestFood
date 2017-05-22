package com.example.jh.todayofbestfood;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jh on 2017-05-22.
 */

public class FoodOfBestInfo implements Parcelable {
    private String _restaurant_name;
    private String _restaurant_address;
    private String _restaurant_recommend_food;
    private float _restaurant_grade;
    private String _food_postscript;

    public FoodOfBestInfo(String restaurant_name, String restaurant_address, String restaurant_recommend_food, float restaurant_grade, String food_postscript) {
        _restaurant_name = restaurant_name;
        _restaurant_address = restaurant_address;
        _restaurant_recommend_food = restaurant_recommend_food;
        _restaurant_grade = restaurant_grade;
        _food_postscript = food_postscript;
    }

    protected FoodOfBestInfo(Parcel in) {
        _restaurant_name = in.readString();
        _restaurant_address = in.readString();
        _restaurant_recommend_food = in.readString();
        _restaurant_grade = in.readFloat();
        _food_postscript = in.readString();
    }

    public static final Creator<FoodOfBestInfo> CREATOR = new Creator<FoodOfBestInfo>() {
        @Override
        public FoodOfBestInfo createFromParcel(Parcel in) {
            return new FoodOfBestInfo(in);
        }

        @Override
        public FoodOfBestInfo[] newArray(int size) {
            return new FoodOfBestInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_restaurant_name);
        dest.writeString(_restaurant_address);
        dest.writeString(_restaurant_recommend_food);
        dest.writeFloat(_restaurant_grade);
        dest.writeString(_food_postscript);
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

    public String getFood_postscript() {
        return _food_postscript;
    }

    public void setFood_postscript(String food_postscript) {
        _food_postscript = food_postscript;
    }
}
