package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by jh on 2017-05-24.
 */

public class CameraActionService {
    private static final int TAKEPICTURE_ACTIVITY = 101;
    private static final int GETPHOTO_ACTIVITY = 104;


    Activity _activity;

    public CameraActionService(Activity activity) {
        _activity = activity;
    }

    public void imageToInput(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
        _activity.startActivityForResult(intent,TAKEPICTURE_ACTIVITY);
    }

    public void getAlbumPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        _activity.startActivityForResult(intent, GETPHOTO_ACTIVITY);
    }



}
