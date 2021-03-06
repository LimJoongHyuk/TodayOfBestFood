package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener{
    private static final String PATH = "/storage/emulated/0/DCIM/Camera/";
    private static final String TAG = "SelectActivity";

    private static final int DATABASE_SERVICE_REQUEST = 106;
    private static final int REVIEW_ACTIVIEY = 107;

    public static final String SELF_KEY = "SELFKEY";
    public static final String REVIEW_SELECT_KEY = "ReViewSelect";
    public static final String RESTAURANT_SELECT_KEY = "RestaurantSelect";
    String isName = "SelectActivity";
    TextView txtRestaurantName, txtRestaurantTag, txtRecommendFood;
    RatingBar ratingBar;
    ImageView imgSelect;

    View markerView;
    ImageView markerImage;

    SupportMapFragment mapFragment;
    GoogleMap map;

    private MarkerOptions _markerOptions;
    private ArrayList<MarkerItem> _markerItems;

    private int curRestaurantId;
    private Button _button_location;
    private Button _button_review;
    private LatLng _latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        imgSelect = (ImageView) findViewById(R.id.imageView_inselect);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar_inselect);
        txtRestaurantName = (TextView)findViewById(R.id.textView_restaurantName);
        txtRestaurantTag = (TextView)findViewById(R.id.textView_tag);
        txtRecommendFood = (TextView)findViewById(R.id.textView_RecommendFood);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "GoogleMap is ready.");

                map = googleMap;

                setCustomMarkerView();

                requestMyLocation();
                map.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) SelectActivity.this);
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

        _button_location = (Button) findViewById(R.id.btnLocation);
        _button_review = (Button) findViewById(R.id.btnReview);

        _button_review.setVisibility(View.INVISIBLE);

        _button_location.setOnClickListener(this::locationButtonClick);
        _button_review.setOnClickListener(this::reviewButtonClick);

    }

    private void zoomCamera(LatLng latLng) {
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(18));
    }

    private void reviewButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
        intent.putExtra(REVIEW_SELECT_KEY,curRestaurantId);
        startActivityForResult(intent,REVIEW_ACTIVIEY);
    }

    private void locationButtonClick(View view) {
        requestMyLocation();
    }

    private void requestMyLocation() {
        try {
            GPSService gpsService = new GPSService(SelectActivity.this);
            gpsService.startLocationService();
            _latLng = gpsService.getlatLng();
            showCurrentLocation(_latLng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCurrentLocation(LatLng curPoint) {

        _markerOptions = new MarkerOptions();
        _markerOptions.position(curPoint);
        _markerOptions.title("현재 위치");

        map.clear();
        map.addMarker(_markerOptions);
        setCustomMarkerView();
        getMarkerItems();

        zoomCamera(curPoint);
    }



    private void setCustomMarkerView() {
        markerView = LayoutInflater.from(this).inflate(R.layout.marker_layout, null);
        markerImage = (ImageView) markerView.findViewById(R.id.markerImage);
    }

    private void getMarkerItems() {
     //   _markerItems = new ArrayList<>();

        //데이터 받아오기
        Intent intent = new Intent(this,DatabaseQueryService.class);
        intent.putExtra(SELF_KEY,isName);
        startActivityForResult(intent,DATABASE_SERVICE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DATABASE_SERVICE_REQUEST){

            _markerItems = (ArrayList<MarkerItem>) data.getSerializableExtra(RESTAURANT_SELECT_KEY);
            for(MarkerItem markerItem : _markerItems) {
                addMarker(markerItem);
                System.out.println("요청된 데이터 : " + markerItem.getRestaurantId() + " " + markerItem.getRestaurantName() + " " + markerItem.getFoodTag());
            }

        }
    }
        //마커찍기
    private void addMarker(MarkerItem markerItem) {
        LatLng position = new LatLng(markerItem.getRestaurantLatitude(), markerItem.getRestaurantLongitude());

        String restaurantName= markerItem.getRestaurantName();
        String food_tag = markerItem.getFoodTag();

        _markerOptions = new MarkerOptions();
        _markerOptions.title(restaurantName);
        _markerOptions.position(position);

        switch (food_tag.toString()) {
            case "중식" :
                markerImage.setImageResource(R.drawable.chinesefood_off);
                break;
            case "패스트푸드" :
                markerImage.setImageResource(R.drawable.festfood_off);
                break;
            case "한식" :
                markerImage.setImageResource(R.drawable.koreanfood_off);
                break;
            case "일식" :
                markerImage.setImageResource(R.drawable.japanesefood_off);
                break;
            default:
        }

        _markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, markerView)));
        map.addMarker(_markerOptions);
    }

    private Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        _button_review.setVisibility(View.VISIBLE);
        for(MarkerItem markerItem : _markerItems) {
            if(markerItem.getRestaurantName().equals(marker.getTitle())) {
                txtRestaurantName.setText(markerItem.getRestaurantName());
                txtRestaurantTag.setText("구분 : " + markerItem.getFoodTag());
                txtRecommendFood.setText("추천 메뉴 : " + markerItem.getRestaurantRecommendFood());
                ratingBar.setRating(markerItem.getRestaurantGrade());
                setImage(markerItem.getFoodImageName());
                curRestaurantId = markerItem.getRestaurantId();
            }
        }
        LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        zoomCamera(latLng);
        return true;
    }

    private void setImage(String string) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(PATH + string);
            imgSelect.setImageBitmap(bitmap);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
