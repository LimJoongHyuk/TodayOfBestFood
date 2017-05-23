package com.example.jh.todayofbestfood;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import java.util.ArrayList;

/**
 * Created by jh on 2017-05-22.
 */

public class DataInputActivity extends AppCompatActivity {


    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private DatabaseQuery db_query;
    private ArrayList<FoodOfBestInfo> foodDataInfo ;

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
        editText_restaurantName = (EditText)findViewById(R.id.editText_restaurantName);
        editText_restaurant_recommend_food = (EditText)findViewById(R.id.editText_restaurant_recommend_food);
        editText_postscript = (EditText)findViewById(R.id.editText_postscript);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rd = (RadioButton) findViewById(rg.getCheckedRadioButtonId());







        Button btn_dataInput = (Button)findViewById(R.id.button_input);
        btn_dataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataBase();
            }
        });

    }







    private boolean openDataBase(){
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        return true;
    }
    private void insertData(){
        db_query = new DatabaseQuery();
        db.execSQL(db_query.onInsertRestaurantData().toString());


    }



}
