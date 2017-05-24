package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import java.io.IOException;

/**
 * Created by jh on 2017-05-22.
 */

public class DataInputActivity extends AppCompatActivity {
    private static final int GETPHOTO_ACTIVITY = 104;

    ImageView imageView_goodfood;

    EditText editText_restaurantName;
    EditText editText_restaurant_recommend_food;
    EditText editText_postscript;

    RadioGroup rg;
    RadioButton rd;

    RatingBar ratingBar;
    CameraActionService cameraActionService = new CameraActionService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datainput);
        imageView_goodfood = (ImageView)findViewById(R.id.imageView_goodfood);
        editText_restaurantName = (EditText)findViewById(R.id.editText_restaurantName);
        editText_restaurant_recommend_food = (EditText)findViewById(R.id.editText_restaurant_recommend_food);
        editText_postscript = (EditText)findViewById(R.id.editText_postscript);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rd = (RadioButton) findViewById(rg.getCheckedRadioButtonId());

        Button btn_dataInput = (Button)findViewById(R.id.button_input);
        btn_dataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView_goodfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraActionService.getAlbumPhoto();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GETPHOTO_ACTIVITY) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imageView_goodfood.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /*public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }*/
}
