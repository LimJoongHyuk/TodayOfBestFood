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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-05-22.
 */

public class ReviewActivity extends AppCompatActivity {
    public static final String SELF_KEY = "SELFKEY";
    String isName = "ReviewActivityInsert";
    private static final int DATABASE_SERVICE_REQUEST = 109;
    private static final String KEY_REVIEW_INPUT = "review";

    private RecyclerView _recyclerView;
    private int res_id;

    private EditText _inputReview_ET;
    private RatingBar _inputGrade_RB;
    private Button _inputOperation_BT;

    TextView txtReview;
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Intent intent_received = getIntent();
        if(intent_received != null) {
            res_id = intent_received.getExtras().getInt("res_id");
        }

        Intent intent = new Intent(this,DatabaseQueryService.class);
        intent.putExtra(SELF_KEY,"ReviewActivity");
        intent.putExtra("res_id", res_id);
        startActivityForResult(intent,DATABASE_SERVICE_REQUEST);

        //fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        final ReviewInputFragment reviewInputFragment = (ReviewInputFragment)fragmentManager.findFragmentById(R.id.reviewinput);
        //

        txtReview = (TextView) findViewById(R.id.reviewSelect_TV);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar_recycle);

        _recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        _inputReview_ET = (EditText)findViewById(R.id.inputReview_ET);
        _inputGrade_RB = (RatingBar)findViewById(R.id.inputGrade_RB);
        _inputOperation_BT =(Button)findViewById(R.id.inputOperation_BT);

        _inputOperation_BT.setOnClickListener(this::inputOperationButtonClick);

        System.out.println("요청된 아이디 : " + res_id);
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
        }
        showReview();
    }

    public void inputOperationButtonClick(View view) {

        System.out.println("입력 리뷰" + _inputReview_ET.getText().toString());

        System.out.println("입력 평점" + String.valueOf(_inputGrade_RB.getRating()));

        Intent intent = new Intent(view.getContext(),DatabaseQueryService.class);

        ReviewParcelable reviewParcelable = new ReviewParcelable(res_id,_inputReview_ET.getText().toString(), _inputGrade_RB.getRating());

        intent.putExtra(KEY_REVIEW_INPUT, reviewParcelable);
        intent.putExtra(SELF_KEY,isName);
        startActivityForResult(intent, 2222);
    }
}

