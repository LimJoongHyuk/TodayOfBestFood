package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by jh on 2017-05-22.
 */

public class FoodMap extends Activity {
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    private GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //   mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView)).getMap();
        Marker seoul = mMap.addMarker(new MarkerOptions().position(SEOUL).title("Seoul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


    }

}
