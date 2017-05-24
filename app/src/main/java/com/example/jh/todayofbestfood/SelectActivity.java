package com.example.jh.todayofbestfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SelectActivity extends AppCompatActivity {
    private static final String TESTPATH = "/storage/emulated/0/DCIM/Camera/20170524_112204.jpg";
    private static final String TAG = "SelectActivity";

    SupportMapFragment mapFragment;
    GoogleMap map;
    private MarkerOptions _markerOptions;
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    private ImageView imageView_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        imageView_food = (ImageView) findViewById(R.id.imageView_food);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is ready.");

                map = googleMap;

                _markerOptions = new MarkerOptions();
                _markerOptions.position(SEOUL);
                map.addMarker(_markerOptions);
                map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
                map.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Button button_location = (Button) findViewById(R.id.btnLocation);
        Button button_review = (Button) findViewById(R.id.btnReview);

        button_location.setOnClickListener(this::locationButtonClick);
        button_review.setOnClickListener(this::reviewButtonClick);

    }

    private void reviewButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
        startActivity(intent);
    }

    private void locationButtonClick(View view) {
        requestMyLocation();
        setImage();
    }

    private void requestMyLocation() {
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new android.location.LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                showCurrentLocation(lastLocation);
            }

            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new android.location.LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());

        _markerOptions = new MarkerOptions();
        _markerOptions.position(curPoint);
        _markerOptions.title("현재 위치");

        map.clear();
        map.addMarker(_markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(curPoint));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    private void setImage() {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(TESTPATH);
            imageView_food.setImageBitmap(bitmap);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
