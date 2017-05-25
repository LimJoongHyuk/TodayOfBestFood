package com.example.jh.todayofbestfood;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pdg on 2017-05-24.
 */

public class ReviewParcelable implements Parcelable {
    private int _choiceRestaurantId;
    private String _script;
    private float _grade;

    public int getChoiceRestaurantId() {
        return _choiceRestaurantId;
    }

    public float getgrade() {
        return _grade;
    }

    public String getscript() {
        return _script;
    }

    public ReviewParcelable(int choiceRestaurantId, String script, float grade) {
        _choiceRestaurantId = choiceRestaurantId;
        _script = script;
        _grade = grade;
    }

    public ReviewParcelable(Parcel src) {
        _choiceRestaurantId = src.readInt();
        _script = src.readString();
        _grade = src.readFloat();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public ReviewParcelable createFromParcel(Parcel in){
            return new ReviewParcelable(in);
        }

        public ReviewParcelable[] newArray(int size){
            return new ReviewParcelable[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_choiceRestaurantId);
        dest.writeString(_script);
        dest.writeFloat(_grade);
    }
}
