package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by jh on 2017-05-24.
 */

public class GPSService {
    private static final int REQUEST_GPS_SERVICE = 105;

    Activity _activity;
    private Double latitude;
    private Double longitude;
    private LatLng _latLng;
    private LocationManager _locationManager;


    public Double getLatitude() {
        latitude = _latLng.latitude;
        return latitude;
    }

    public Double getLongitude() {
        longitude = _latLng.longitude;
        return longitude;
    }

    public LatLng getlatLng() { return _latLng;}

    public GPSService(Activity _activity) {
        this._activity = _activity;
        _locationManager = (LocationManager) _activity.getSystemService(Context.LOCATION_SERVICE);
    }

    public void startLocationService(){
        // 위치 관리자 객체 참조

        System.out.println("start 로컬 서비스 실행");
        // 위치 정보를 받을 리스너 생성
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        try {
            // GPS를 이용한 위치 요청
            _locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 네트워크를 이용한 위치 요청
            _locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 위치 확인이 안되는 경우에도 최근에 확인된 위치 정보 먼저 확인
            Location lastLocation = _locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                _latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            }
        } catch(SecurityException ex) {
            ex.printStackTrace();
        }

    }

    private class GPSListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            _latLng = new LatLng(location.getLatitude(), location.getLongitude());
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

    public boolean isGpsCheck() {
        if(!_locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) return false;
        else return true;
    }

    public void turnOnGps() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        _activity.startActivityForResult(intent, REQUEST_GPS_SERVICE);
    }
}