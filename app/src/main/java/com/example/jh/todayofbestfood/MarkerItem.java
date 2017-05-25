package com.example.jh.todayofbestfood;

import java.io.Serializable;

/**
 * Created by smh on 2017-05-25.
 */

public class MarkerItem implements Serializable {
    private int restaurantId;
    private String restaurantName;
    private String foodTag;
    private String restaurantRecommendFood;
    private float restaurantGrade;
    private Double restaurantLatitude;
    private Double restaurantLongitude;
    private String foodImageName;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodTag() {
        return foodTag;
    }

    public void setFoodTag(String foodTag) {
        this.foodTag = foodTag;
    }

    public String getRestaurantRecommendFood() {
        return restaurantRecommendFood;
    }

    public void setRestaurantRecommendFood(String restaurantRecommendFood) {
        this.restaurantRecommendFood = restaurantRecommendFood;
    }

    public float getRestaurantGrade() {
        return restaurantGrade;
    }

    public void setRestaurantGrade(float restaurantGrade) {
        this.restaurantGrade = restaurantGrade;
    }

    public double getRestaurantLatitude() {
        return restaurantLatitude;
    }

    public void setRestaurantLatitude(Double restaurantLatitude) {
        this.restaurantLatitude = restaurantLatitude;
    }

    public double getRestaurantLongitude() {
        return restaurantLongitude;
    }

    public void setRestaurantLongitude(Double restaurantLongitude) {
        this.restaurantLongitude = restaurantLongitude;
    }

    public String getFoodImageName() {
        return foodImageName;
    }

    public void setFoodImageName(String foodImageName) {
        this.foodImageName = foodImageName;
    }

    public MarkerItem(int restaurantId, String restaurantName, String foodTag, String restaurantRecommendFood
            , float restaurantGrade, Double restaurantLatitude, Double restaurantLongitude, String foodImageName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.foodTag = foodTag;
        this.restaurantRecommendFood = restaurantRecommendFood;
        this.restaurantGrade = restaurantGrade;
        this.restaurantLatitude = restaurantLatitude;
        this.restaurantLongitude = restaurantLongitude;
        this.foodImageName = foodImageName;
    }
}
