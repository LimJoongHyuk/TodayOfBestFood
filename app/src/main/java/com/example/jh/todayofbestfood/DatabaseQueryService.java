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
    String self_check;

    public static final String REGISTER_KEY = "REGISTER";
    public static final String REVIEW_KEY = "REVIEW";
    public static final String SELF_KEY = "SELFKEY";

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
        self_check = bundle.getString(SELF_KEY).toString();
        query_judgment(self_check,bundle);



    }
    public void query_judgment(String key, Bundle bundle){
        switch (key){
            case "DataInputActivity":
                restaurantInsert(bundle);
                excuteSqlQuery();
                break;
            case "ReviewInputFragment":
                reviewInsert(bundle);
                excuteSqlQuery();
                break;
            case "SelectActivity":
                getRestaurantInfoTableDataBundle();
                break;
        }
    }

    public void excuteSqlQuery(){
        try {
            db = databaseHelper.getWritableDatabase();
            db.execSQL(SQL_QUERY);
            System.out.println("실행된 엑티비티"+self_check);
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

        SQL_QUERY = "Insert into " + REVIEW_TABLE_NAME + " values(null,1, '" + reviewParcelable.getscript().toString() + "' , " +
                " " + reviewParcelable.getgrade() + " ); ";

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
                " values(, '" +
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


    //RestaurantInfo 테이블 묶음 가져오는 메소드
    private ArrayList<MarkerItem> _restaurantInfoTableArrayList = new ArrayList<>();


    public void getRestaurantInfoTableDataBundle(){
            //select 에 * 를 생성자 위치랑 맞춰준다.
        System.out.println("실행된 엑티비티"+self_check);
        db = databaseHelper.getWritableDatabase();
        SQL_QUERY = "select * from " + RESTAURANT_TABLE_NAME;
        /*    SQL_QUERY = " Select restaurant_id, " +
                    " restaurant_name, " +
                    " restaurant_foodtag, "+
                    " restaurant_recommend_food, " +
                    " restaurant_grade, " +
                    " restaurant_Latitude, " +
                    " restaurant_Longitude, "+
                    " restaurant_imgname "+
                    " from " + RESTAURANT_TABLE_NAME;
      */
        System.out.println("쿼리 :" + SQL_QUERY);
        Cursor cursor = db.rawQuery(SQL_QUERY, null);
        int recordCount = cursor.getCount();
        System.out.println("쿼리 카운터 : " + recordCount);
        _restaurantInfoTableArrayList.clear();


        for(int i = 0; i < recordCount ; i++){
            cursor.moveToNext();
            MarkerItem markerItem = new MarkerItem(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getFloat(4),
                    Double.parseDouble(cursor.getString(5).toString()),
                    Double.parseDouble(cursor.getString(6).toString()),
                    cursor.getString(7));
            _restaurantInfoTableArrayList.add(markerItem);
        }
        cursor.close();
        Intent intent = new Intent();
        intent.putExtra("select",_restaurantInfoTableArrayList);
        setResult(RESULT_OK,intent);
        finish();
    }


    public void getRestaurantReviewTableDataBundle(){
        SQL_QUERY = " Select * from " + REVIEW_TABLE_NAME;


        }

    }