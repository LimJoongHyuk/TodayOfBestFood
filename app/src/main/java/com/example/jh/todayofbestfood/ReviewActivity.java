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

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewActivity extends AppCompatActivity {

    public static final String SELF_KEY = "SELFKEY";
    public static final String REVIEW_SELECT_PUT_KEY = "ReViewSelectPut";
    public static final String REVIEW_SELECT_KEY = "ReViewSelect";
    public static final String REVIEW_SELECT_ID_KEY = "ReviewSelectIdKet";
    public static final String KEY_REVIEWINPUT="REVIEW";
    String isName = "ReviewActivity";
;
    private static final int DATABASE_SERVICE_REQUEST = 106;
    private static final int DATABASE_SERVICE_REQUEST_INSERT = 110;


    private RecyclerView _recyclerView;
    private int select_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        //fragment
        select_view();

    }//end onCreate




    private ArrayList<ReviewParcelable> loadData(){
        return reviews;
    }
    public void select_view(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        final ReviewInputFragment reviewInputFragment = (ReviewInputFragment)fragmentManager.findFragmentById(R.id.reviewinput);

        Intent get_intent = getIntent();
        select_id = get_intent.getIntExtra(REVIEW_SELECT_KEY,0);
        Intent intent = new Intent(this,DatabaseQueryService.class);
        System.out.println("리뷰 엑티비티에서 받은 아이디값" + select_id);
        reviewInputFragment.getId(select_id);
        intent.putExtra(SELF_KEY,isName);
        intent.putExtra(REVIEW_SELECT_ID_KEY,select_id);
        startActivityForResult(intent,DATABASE_SERVICE_REQUEST);

        _recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    public void showReviews(){
        ArrayList<ReviewParcelable> reviewDatas = loadData();
        //adapter와 foodofbestinfo 연결
        ReviewOutputAdapter adapter = new ReviewOutputAdapter(reviewDatas);
        //recyclerview에 adapter 연결
        _recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setItemAnimator(new DefaultItemAnimator());
    }



    private ArrayList<ReviewParcelable> reviews = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DATABASE_SERVICE_REQUEST){

            reviews = (ArrayList<ReviewParcelable>) data.getSerializableExtra(REVIEW_SELECT_PUT_KEY);
            for(ReviewParcelable reviewParcelable : reviews) {
                System.out.println(reviewParcelable.getscript());
            }
            showReviews();

        }
        if(requestCode == DATABASE_SERVICE_REQUEST_INSERT){
            select_view();


        }

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


}

