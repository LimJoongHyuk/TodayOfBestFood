package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

/**
 * Created by smh on 2017-05-23.
 */

public class Camera extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

        // 이미지 잘라내기 위한 크기
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 500);
        intent.putExtra("aspectY", 500);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);

        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);

        intent = new Intent(getApplicationContext(), DataInputActivity.class);
        startActivity(intent);
    }
}
