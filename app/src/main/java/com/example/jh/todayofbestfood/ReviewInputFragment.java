package com.example.jh.todayofbestfood;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewInputFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup fragment = (ViewGroup) inflater.inflate(R.layout.fragment_reviewinput, container, false);

        TableLayout tableLayout = (TableLayout)fragment.findViewById(R.id.reviewTableLayout);


        return fragment;
    }
}
