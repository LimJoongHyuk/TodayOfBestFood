package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        final ReviewInputFragment reviewInputFragment = (ReviewInputFragment)fragmentManager.findFragmentById(R.id.reviewinput);
        //


        _recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        ArrayList<FoodOfBestInfo> foodOfBestInfos = loadData();
        //adapter와 foodofbestinfo 연결
        ReviewOutputAdapter adapter = new ReviewOutputAdapter(foodOfBestInfos);
        //recyclerview에 adapter 연결
        _recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());



    }//end onCreate

    private ArrayList<FoodOfBestInfo> loadData(){

        ArrayList<FoodOfBestInfo> foodOfBestInfos = new ArrayList<>();

        for(int i=0; i<10; i++){
            FoodOfBestInfo foodOfBestInfo = new FoodOfBestInfo();
            foodOfBestInfo.setFood_postscript("후기 " + i);
            foodOfBestInfo.setRestaurant_grade(1 + i);

            foodOfBestInfos.add(foodOfBestInfo);
        }


        return foodOfBestInfos;
    }




}

