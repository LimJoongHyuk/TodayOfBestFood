package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by jh on 2017-05-24.
 */

public class CameraActionService {
    private static final int DATAINPUT_ACTIVITY = 101;


    Activity _activity;

    public CameraActionService(Activity activity) {
        _activity = activity;
    }

    public void imageToInput(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        _activity.startActivityForResult(intent,DATAINPUT_ACTIVITY);
    }



}
