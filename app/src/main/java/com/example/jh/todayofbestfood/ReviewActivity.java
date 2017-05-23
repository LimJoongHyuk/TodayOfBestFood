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




    }

    private ArrayList<FoodOfBestInfo> loadData(){
        ArrayList<FoodOfBestInfo> foodOfBestInfoArrayList = new ArrayList<>();







        return foodOfBestInfoArrayList;
    }
}

