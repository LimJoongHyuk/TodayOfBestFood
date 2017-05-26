package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewInputFragment extends Fragment {

    private EditText _inputReview_ET;
    private RatingBar _inputGrade_RB;
    private Button _inputOperation_BT;

    private static final int DATABASE_SERVICE_REQUEST_INSERT = 110;

    public static final String KEY_REVIEWINPUT="REVIEW";
    public static final String SELF_KEY = "SELFKEY";
    String isName = "ReviewInputFragment";
    int select_id;


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

    public void getId(int id){
        select_id = id;
    }
    public void inputOperationButtonClick(View view) {


            System.out.println("입력 리뷰" + _inputReview_ET.getText().toString());

            System.out.println("입력 평점" + String.valueOf(_inputGrade_RB.getRating()));


            Intent intent = new Intent(view.getContext(),DatabaseQueryService.class);

            ReviewParcelable reviewParcelable = new ReviewParcelable(select_id,_inputReview_ET.getText().toString(), _inputGrade_RB.getRating());

            intent.putExtra(KEY_REVIEWINPUT, reviewParcelable);
            intent.putExtra(SELF_KEY,isName);
            _inputReview_ET.setText("");
            _inputGrade_RB.setRating(0);
            getActivity().startActivityForResult(intent,DATABASE_SERVICE_REQUEST_INSERT);

    }


}
