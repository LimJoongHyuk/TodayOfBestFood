package com.example.jh.todayofbestfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewInputFragment extends Fragment {
    public static final String KEY_REVIEWINPUT="REVIEW";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup fragment = (ViewGroup)inflater.inflate(R.layout.fragment_reviewinput, container, false);


        return fragment;
    }
}
