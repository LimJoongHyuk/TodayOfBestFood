package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

/**
 * Created by jh on 2017-05-22.
 */

public class DataInputActivity extends AppCompatActivity {


    ImageView imageView_goodfood;

    EditText editText_restaurantName;
    EditText editText_restaurant_recommend_food;
    EditText editText_postscript;

    RadioGroup rg;
    RadioButton rd;

    RatingBar ratingBar;

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

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap)intent.getParcelableExtra("bitmap");
        imageView_goodfood.setImageBitmap(bitmap);
        Button btn_dataInput = (Button)findViewById(R.id.button_input);
        btn_dataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }










}
