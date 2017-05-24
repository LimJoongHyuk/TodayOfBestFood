package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by jh on 2017-05-24.
 */

public class GPSService {

    Activity _activity;
    private Double latitude;
    private Double longitude;


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }



    public GPSService(Activity _activity) {
        this._activity = _activity;
    }

    public void startLocationService(){
        // 위치 관리자 객체 참조
        LocationManager manager = (LocationManager) _activity.getSystemService(Context.LOCATION_SERVICE);

        // 위치 정보를 받을 리스너 생성
        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        try {
            // GPS를 이용한 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 네트워크를 이용한 위치 요청
            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    gpsListener);

            // 위치 확인이 안되는 경우에도 최근에 확인된 위치 정보 먼저 확인
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                 latitude = lastLocation.getLatitude();
                 longitude = lastLocation.getLongitude();
            }
        } catch(SecurityException ex) {
            ex.printStackTrace();
        }

    }

    private class GPSListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
             latitude = location.getLatitude();
             longitude = location.getLongitude();
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
}
