package com.example.jh.todayofbestfood;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewOutputAdapter extends RecyclerView.Adapter<ReviewOutputAdapter.ViewHolder> {
//
    private ArrayList<ReviewParcelable> _reviewInfoArrayList;

    public ReviewOutputAdapter(ArrayList<ReviewParcelable> reviewInfoArrayList) {
        _reviewInfoArrayList = reviewInfoArrayList;
    }

    //ViewHolder를 만들때
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_reviewoutput, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewOutputAdapter.ViewHolder holder, int position) {
//        ReviewInfo reviewInfo = new ReviewInfo();
//        holder._reviewSelect_TV.setText(
//                reviewInfo.get_food_postscript() +
//                "\n"
//                +reviewInfo.get_restaurant_add_grade() );
    }

    @Override
    public int getItemCount() {
        return _reviewInfoArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView _reviewSelect_TV;

        public ViewHolder(View itemView) {
            super(itemView);
            _reviewSelect_TV = (TextView)itemView.findViewById(R.id.reviewSelect_TV);
        }


    }
}
