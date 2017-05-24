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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        getReviewParcelable(intent);

    }

    private void getReviewParcelable(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras();

            reviewInsert(bundle);
        }
    }

    public String reviewInsert(Bundle bundle){

        ReviewParcelable reviewParcelable = (ReviewParcelable)bundle.getParcelable(REVIEW_KEY);

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


    //음식점 레코드 등록
    public String onInsertRestaurantData(){
        return SQL_QUERY;
    }


    private void getRestaurantParcelable(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras();

            RestaurantInsert(bundle);
        }
    }

    public String RestaurantInsert(Bundle bundle){

        FoodofBestParcelable foodofBestParcelable = (FoodofBestParcelable)bundle.getParcelable(REVIEW_KEY);

        SQL_QUERY = "Insert into " +
                RESTAURANT_TABLE_NAME +
                " values(null, '" +
                foodofBestParcelable.getRestaurantName().toString() +
                "', '" +
                foodofBestParcelable.getFoodTagName().toString() +
                "', '" +
                foodofBestParcelable.getRecommendMenu().toString() +
                "', '" +
                foodofBestParcelable.getRestaurantgrade() +
                "', '" +
                foodofBestParcelable.getLatitude().toString() +
                "', '" +
                foodofBestParcelable.getLongitude().toString() +
                "', '" +
                foodofBestParcelable.getFoodImgName().toString() +
                "');";

        return SQL_QUERY;
    }

    //리뷰 레코드 등록
    public String onInsertReviewData(FoodOfBestInfo foodOfBestInfo){
        return SQL_QUERY;
    }

    //평점 수정하기 위한 업데이트
    public String onUpdateData(){
        return SQL_QUERY;
    }

    //음식점 삭제를 위한 delete 구문
    public String onDeleteData(FoodOfBestInfo foodOfBestInfo){

        return SQL_QUERY;
    }

    //테이블삭제
    public String onDropTable(){
        return SQL_QUERY;
    }

    //음식점 상세정보 보기위함
    public String onMakerClickedSelectData(FoodOfBestInfo foodOfBestInfo){
        SQL_QUERY = "select restaurant_name , restaurant_recommend_food , restaurant_grade , food_image_name " +
                "from " + RESTAURANT_TABLE_NAME + " " +
                "where restaurant_name = '"+foodOfBestInfo.get_restaurant_name().toString()+"'";
        return SQL_QUERY;
    }

    //음식점 리뷰 전체보기
    public String onReviewClickedSelectData(ReviewInfo reviewInfo){
        SQL_QUERY = "select food_postscript,restaurant_add_grade " +
                "from "+ REVIEW_TABLE_NAME +" " +
                "where restaurant_name = '" + reviewInfo.get_restaurant_name().toString()+"'";
        return SQL_QUERY;
    }


}