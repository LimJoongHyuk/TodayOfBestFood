package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;

/**
 * Created by smh on 2017-05-22.
 */

public class DatabaseQuery extends Activity{
    private static final String RESTAURANT_TABLE_NAME = "RestaurantInfo";
    private static final String REVIEW_TABLE_NAME = "RestaurantReview";

    private String SQL_QUERY;

    public static final String REGISTER_KEY = "REGISTER";
    public static final String REVIEW_KEY = "REVIEW";


    FoodofBestParcelable foodofBestParcelable;
    ReviewParcelable reviewParcelable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        restaurantInsert(bundle);
        System.out.println("반환된결과:"+ SQL_QUERY);
        setResult(RESULT_OK);
        finish();


    }

    public String reviewInsert(Bundle bundle){

        reviewParcelable = (ReviewParcelable)bundle.getParcelable(REVIEW_KEY);

        SQL_QUERY = "Insert into " + REVIEW_TABLE_NAME + " values(null, '" + reviewParcelable.getscript().toString() + "' , " +
                " " + reviewParcelable.getgrade() + " ; ";

        return SQL_QUERY;
    }

    // 음식점 테이블 생성
    public String onCreateTable_restaurant(){
        SQL_QUERY = "create table if not exists "+ RESTAURANT_TABLE_NAME +
                " (restaurant_id integer PRIMARY KEY autoincrement, " +
                "restaurant_name text, " +
                "restaurant_address text, " +
                "restaurant_recommend_food text, " +
                "restaurant_grade text, " +
                "restaurant_latitude text, " +
                "restaurant_longitude text, " +
                "food_image_name text);" ;
        return SQL_QUERY;
    }

    //리뷰 테이블 생성
    public String onCreateTable_review(){
        SQL_QUERY = "create table if not exists " + REVIEW_TABLE_NAME +
                "(id integer PRIMARY KEY autoincrement, "+
                "restaurant_id integer REFERENCES " + RESTAURANT_TABLE_NAME + "(restaurant_id) on delete cascade, "+
                "food_postscript text, "+
                "restaurant_add_grade text);";
        return SQL_QUERY;
    }




    public String restaurantInsert(Bundle bundle){

        foodofBestParcelable = (FoodofBestParcelable)bundle.getParcelable(REGISTER_KEY);

        SQL_QUERY = "Insert into " +
                RESTAURANT_TABLE_NAME +
                " values(null, '" +
                foodofBestParcelable.get_restaurantName().toString() +
                "', '" +
                foodofBestParcelable.get_foodTagName().toString() +
                "', '" +
                foodofBestParcelable.get_recommendMenu().toString() +
                "', '" +
                foodofBestParcelable.get_restaurantgrade() +
                "', '" +
                foodofBestParcelable.get_latitude().toString() +
                "', '" +
                foodofBestParcelable.get_longitude().toString() +
                "', '" +
                foodofBestParcelable.get_foodImgName().toString() +
                "');";

        return SQL_QUERY;
    }

    //리뷰 레코드 등록
    public String onInsertReviewData(Bundle bundle){
        return SQL_QUERY;
    }

    //평점 수정하기 위한 업데이트
    public String onUpdateData(){
        return SQL_QUERY;
    }

    //음식점 삭제를 위한 delete 구문
    public String onDeleteData(Bundle bundle){

        return SQL_QUERY;
    }

    //테이블삭제
    public String onDropTable(){
        return SQL_QUERY;
    }

    //음식점 상세정보 보기위함
    public String onMakerClickedSelectData(Bundle bundle){
        foodofBestParcelable = (FoodofBestParcelable)bundle.getParcelable(REGISTER_KEY);
        SQL_QUERY = "select restaurant_name , restaurant_recommend_food , restaurant_grade , food_image_name " +
                "from " + RESTAURANT_TABLE_NAME + " " +
                "where restaurant_name = '"+foodofBestParcelable.get_restaurantName().toString()+"'";
        return SQL_QUERY;
    }

    //음식점 리뷰 전체보기
    public String onReviewClickedSelectData(Bundle bundle){
        reviewParcelable = (ReviewParcelable)bundle.getParcelable(REVIEW_KEY);
        SQL_QUERY = "select food_postscript,restaurant_add_grade " +
                "from "+ REVIEW_TABLE_NAME +" " +
                "where restaurant_name = '" + reviewParcelable./*외래키*/toString()+"'";
        return SQL_QUERY;
    }


}