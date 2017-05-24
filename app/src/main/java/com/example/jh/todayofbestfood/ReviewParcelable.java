package com.example.jh.todayofbestfood;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pdg on 2017-05-24.
 */

public class ReviewParcelable implements Parcelable {

    private String _script;
    private float _grade;

    public float getgrade() {
        return _grade;
    }

    public String getscript() {
        return _script;
    }

    public ReviewParcelable(String script, float grade) {
        _script = script;
        _grade = grade;
    }

    public ReviewParcelable(Parcel src) {
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
        dest.writeString(_script);
        dest.writeFloat(_grade);
    }
}
