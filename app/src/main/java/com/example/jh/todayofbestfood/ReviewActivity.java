package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewActivity extends AppCompatActivity {
    private static final String SELF_KEY = "SELFKEY";
    private static final int DATABASE_SERVICE_REQUEST = 109;

    private RecyclerView _recyclerView;
    private int _id;

    TextView txtReview;
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent_received = getIntent();
        if(intent_received != null) {
            _id = intent_received.getExtras().getInt("res_id");
        }

        Intent intent = new Intent(this,DatabaseQueryService.class);
        intent.putExtra(SELF_KEY,"ReviewActivity");
        intent.putExtra("res_id", _id);
        startActivityForResult(intent,DATABASE_SERVICE_REQUEST);

        //fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        final ReviewInputFragment reviewInputFragment = (ReviewInputFragment)fragmentManager.findFragmentById(R.id.reviewinput);
        //

        txtReview = (TextView) findViewById(R.id.reviewSelect_TV);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar_recycle);

        _recyclerView = (RecyclerView)findViewById(R.id.recyclerView);





    }//end onCreate

    private ArrayList<ReviewParcelable> loadData(){
        return reviews;
    }

    private void showReview() {
        ArrayList<ReviewParcelable> reviewDatas = loadData();
        //adapter와 foodofbestinfo 연결
        ReviewOutputAdapter adapter = new ReviewOutputAdapter(reviewDatas);
        //recyclerview에 adapter 연결
        _recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_btn) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<ReviewParcelable> reviews = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DATABASE_SERVICE_REQUEST){

            reviews = (ArrayList<ReviewParcelable>) data.getSerializableExtra("select");
            for(ReviewParcelable reviewParcelable : reviews) {
                System.out.println(reviewParcelable.getscript() + reviewParcelable.getgrade());
            }
            showReview();
        }
    }
}

