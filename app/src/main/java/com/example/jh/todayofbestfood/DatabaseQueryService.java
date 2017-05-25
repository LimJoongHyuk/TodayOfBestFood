package com.example.jh.todayofbestfood;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by smh on 2017-05-22.
 */

public class DatabaseQueryService extends Activity{
    private static final String RESTAURANT_TABLE_NAME = "RestaurantInfo";
    private static final String REVIEW_TABLE_NAME = "RestaurantReview";


    private String SQL_QUERY;

    public static final String REGISTER_KEY = "REGISTER";
    public static final String REVIEW_KEY = "REVIEW";


    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    FoodofBestParcelable foodofBestParcelable;
    ReviewParcelable reviewParcelable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();






    }
    public void query_judgment(String key, Bundle bundle){
        switch (key){
            case REGISTER_KEY:
                restaurantInsert(bundle);
                break;
        }
    }

    public void excuteSqlQuery(){
        try {
            db = databaseHelper.getWritableDatabase();
            db.execSQL(SQL_QUERY);
            System.out.println("쿼리실행 완료");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("반환된결과:"+ SQL_QUERY);
        setResult(RESULT_OK);
        finish();
    }
    //음식점 리뷰남기기
    public String reviewInsert(Bundle bundle){

        reviewParcelable = (ReviewParcelable)bundle.getParcelable(REVIEW_KEY);

        SQL_QUERY = "Insert into " + REVIEW_TABLE_NAME + " values(null, '" + reviewParcelable.getscript().toString() + "' , " +
                " " + reviewParcelable.getgrade() + " ; ";

        return SQL_QUERY;
    }


    //DB에 저장 된 inputdata를 ArrayList에 저장 후
    public void getDataInput(){

    }


    // 음식점 테이블 생성
    public String onCreateTable_restaurant(){
        SQL_QUERY = "create table if not exists "+ RESTAURANT_TABLE_NAME +
                " (restaurant_id integer PRIMARY KEY autoincrement, " +
                " restaurant_name text, " +
                " restaurant_foodtag text, " +
                " restaurant_recommend_food text, " +
                " restaurant_grade text, " +
                " restaurant_latitude text, " +
                " restaurant_longitude text, " +
                " food_image_name text ); " ;
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



 //음식점 데이터 삽입
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



//    // 음식점 테이블 생성
//    public String onCreateTable_restaurant(){
//        SQL_QUERY = "create table if not exists "+ RESTAURANT_TABLE_NAME +
//                " (restaurant_id integer PRIMARY KEY autoincrement, " +
//                "restaurant_name text, " +
//                "restaurant_address text, " +
//                "restaurant_recommend_food text, " +
//                "restaurant_grade text, " +
//                "restaurant_latitude text, " +
//                "restaurant_longitude text, " +
//                "food_image_name text);" ;
//        return SQL_QUERY;
//    }
    //RestaurantInfo 테이블 묶음 가져오는 메소드
    private ArrayList<FoodofBestParcelable> _restaurantInfoTableArrayList = new ArrayList<>();


    public void getRestaurantInfoTableDataBundle(){
            //select 에 * 를 생성자 위치랑 맞춰준다.
        SQL_QUERY = " Select restaurant_id, " +
                    " restaurant_name, " +
                    " restaurant_foodtag, "+
                    " restaurant_recommend_food, " +
                    " restaurant_grade, " +
                    " restaurant_Latitude, " +
                    " restaurant_Longitude, "+
                    " restaurant_imgname "+
                    " from " + RESTAURANT_TABLE_NAME;

        Cursor cursor = db.rawQuery(SQL_QUERY, null);

        _restaurantInfoTableArrayList.clear();


        while(cursor.moveToNext()){
            FoodofBestParcelable foodofBestParcelable = new FoodofBestParcelable(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7));
            _restaurantInfoTableArrayList.add(foodofBestParcelable);

        }
    }//end getRestaurantInfoTableDataBundle


//    //리뷰 테이블 생성
//    public String onCreateTable_review(){
//        SQL_QUERY = "create table if not exists " + REVIEW_TABLE_NAME +
//                "(id integer PRIMARY KEY autoincrement, "+
//                "restaurant_id integer REFERENCES " + RESTAURANT_TABLE_NAME + "(restaurant_id) on delete cascade, "+
//                "food_postscript text, "+
//                "restaurant_add_grade text);";
//        return SQL_QUERY;
//    }


    //RestaurantReview 테이블 묶음 가져오는 메소드

    private ArrayList<String> _restaurantReviewTablePrimaryKeyArrayList;
    private ArrayList<String> _restaurantReviewTableKeyofReferenceArrayList;
    private ArrayList<String> _restaurantReviewTableFoodpostScriptArrayList;
    private ArrayList<Double> _restaurantReviewTableRestaurantGradeArrayList;

    public void getRestaurantReviewTableDataBundle(){
        SQL_QUERY = " Select * from " + REVIEW_TABLE_NAME;

        Cursor cursor = db.rawQuery(SQL_QUERY, null);

        _restaurantReviewTablePrimaryKeyArrayList.clear();
        _restaurantReviewTableKeyofReferenceArrayList.clear();
        _restaurantReviewTableFoodpostScriptArrayList.clear();
        _restaurantReviewTableRestaurantGradeArrayList.clear();

        while(cursor.moveToNext()){
            _restaurantReviewTablePrimaryKeyArrayList.add(cursor.getString(0));
            _restaurantReviewTableKeyofReferenceArrayList.add(cursor.getString(1));
            _restaurantReviewTableFoodpostScriptArrayList.add(cursor.getString(2));
            _restaurantReviewTableRestaurantGradeArrayList.add(cursor.getDouble(3));

        }

    }

    //

    //음식점 리뷰 전체보기
    public String onReviewClickedSelectData(Bundle bundle){
        reviewParcelable = (ReviewParcelable)bundle.getParcelable(REVIEW_KEY);
        SQL_QUERY = "select food_postscript,restaurant_add_grade " +
                "from "+ REVIEW_TABLE_NAME +" " +
                "where restaurant_name = '" + reviewParcelable./*외래키*/toString()+"'";
        return SQL_QUERY;
    }



}