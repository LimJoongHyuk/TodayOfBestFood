package com.example.jh.todayofbestfood;

/**
 * Created by smh on 2017-05-22.
 */

public class DatabaseQuery {
    private static final String RESTAURANT_TABLE_NAME = "RestaurantInfo";
    private static final String REVIEW_TABLE_NAME = "RestaurantReview";

    private String SQL_QUERY;


    // 음식점 테이블 생성
    public String onCreateTable_restaurant(){
        SQL_QUERY = "create table "+ RESTAURANT_TABLE_NAME +
                "(restaurant_id integer PRIMARY KEY autoincrement," +
                "restaurant_name text PRIMARY KEY," +
                "restaurant_address text," +
                "restaurant_recommend_food text," +
                "restaurant_grade text," +
                "restaurant_latitude text," +
                "restaurant_longitude text," +
                "food_image_name text)" ;
        return SQL_QUERY;
    }

    //리뷰 테이블 생성
    public String onCreateTable_review(){
        SQL_QUERY = "create table " + REVIEW_TABLE_NAME +
                "(id integer PRIMARY KEY autoincrement,"+
                "restaurant_name text,"+
                "food_postscript text,"+
                "restaurant_add_grade,"+
                "FOREIGN KEY(restaurant_name)," +
                "REFERENCES "+RESTAURANT_TABLE_NAME + "(restaurant_name)"+
                ")";
        return SQL_QUERY;
    }


    //음식점 레코드 등록
    public String onInsertRestaurantData(){
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
                "where restaurant_name = '"+foodOfBestInfo.getRestaurant_name().toString()+"'";
        return SQL_QUERY;
    }

    //음식점 리뷰 전체보기
    public String onReviewClickedSelectData(FoodOfBestInfo foodOfBestInfo){
        SQL_QUERY = "select food_postscript,restaurant_add_grade " +
                "from "+ REVIEW_TABLE_NAME +" " +
                "where restaurant_name = '" + foodOfBestInfo.getRestaurant_name().toString()+"'";
        return SQL_QUERY;
    }


}