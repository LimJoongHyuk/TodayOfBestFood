package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        try {
            GPSService gpsService = new GPSService(SelectActivity.this);
            gpsService.startLocationService();
            LatLng latLng = gpsService.getlatLng();
            showCurrentLocation(latLng);
        } catch (Exception e) {

        }
    }

    private void showCurrentLocation(LatLng curPoint) {

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

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_btn) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
