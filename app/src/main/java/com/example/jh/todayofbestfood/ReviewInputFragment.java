package com.example.jh.todayofbestfood;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewInputFragment extends Fragment {

    private EditText inputReview_ET;
    private RatingBar inputGrade_RB;
    private Button inputOperation_BT;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup fragment = (ViewGroup)inflater.inflate(R.layout.fragment_reviewinput, container, false);
        EditText inputReview_ET = (EditText)fragment.findViewById(R.id.inputReview_ET);
        RatingBar inputGrade_RB = (RatingBar)fragment.findViewById(R.id.inputGrade_RB);
        Button inputOperation_BT =(Button)fragment.findViewById(R.id.inputOperation_BT);

        inputOperation_BT.setOnClickListener(this::inputOperationButtonClick);

        return fragment;
    }

    private void inputOperationButtonClick(View view) {

    }


}
