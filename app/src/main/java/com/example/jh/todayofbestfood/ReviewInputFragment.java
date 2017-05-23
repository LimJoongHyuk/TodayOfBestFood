package com.example.jh.todayofbestfood;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewInputFragment extends Fragment {

    private EditText _inputReview_ET;
    private RatingBar _inputGrade_RB;
    private Button _inputOperation_BT;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup fragment = (ViewGroup)inflater.inflate(R.layout.fragment_reviewinput, container, false);
        _inputReview_ET = (EditText)fragment.findViewById(R.id.inputReview_ET);
        _inputGrade_RB = (RatingBar)fragment.findViewById(R.id.inputGrade_RB);
        _inputOperation_BT =(Button)fragment.findViewById(R.id.inputOperation_BT);

        _inputOperation_BT.setOnClickListener(this::inputOperationButtonClick);

        return fragment;
    }

    public void inputOperationButtonClick(View view) {
        DatabaseManager databaseManager = new DatabaseManager();

//        databaseManager.getVisitReviewInfo(
//                _inputReview_ET.getText().toString(),
//                _inputGrade_RB.getStepSize());

        System.out.println("입력 리뷰" + _inputReview_ET.getText().toString());

        System.out.println("입력 평점" + String.valueOf(_inputGrade_RB.getRating()));
    }


}
