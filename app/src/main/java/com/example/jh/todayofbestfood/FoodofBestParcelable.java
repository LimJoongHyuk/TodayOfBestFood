package com.example.jh.todayofbestfood;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pdg on 2017-05-24.
 */

public class FoodofBestParcelable implements Parcelable {

    private String _restaurantName;
    private String _recommendMenu;
    private float _restaurantgrade;
    private String _latitude;
    private String _longitude;
    private String _foodImgName;
    private String _foodTagName;

    public FoodofBestParcelable(String name, String menu, float grade,
                                String latitude, String longitude, String imgName,
                                String tagname) {
        _restaurantName = name;
        _recommendMenu = menu;
        _restaurantgrade = grade;
        _latitude = latitude;
        _longitude = longitude;
        _foodImgName = imgName;
        _foodTagName = tagname;
    }

    public FoodofBestParcelable(Parcel src){
        _restaurantName = src.readString();
        _recommendMenu = src.readString();
        _restaurantgrade = src.readFloat();
        _latitude = src.readString();
        _longitude = src.readString();
        _foodImgName = src.readString();
        _foodTagName = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public FoodofBestParcelable createFromParcel(Parcel in){
            return new FoodofBestParcelable(in);
        }

        public FoodofBestParcelable[] newArray(int size){
            return new FoodofBestParcelable[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_restaurantName);
        dest.writeString(_recommendMenu);
        dest.writeFloat(_restaurantgrade);
        dest.writeString(_latitude);
        dest.writeString(_longitude);
        dest.writeString(_foodImgName);
        dest.writeString(_foodTagName);
    }
}
