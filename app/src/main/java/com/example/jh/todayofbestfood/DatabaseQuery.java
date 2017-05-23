package com.example.jh.todayofbestfood;

/**
 * Created by smh on 2017-05-22.
 */

public class DatabaseQuery {
    private static final String RESTAURANT_TABLE_NAME = "RestaurantInfo";
    private static final String REVIEW_TABLE_NAME = "RestaurantReview";

    private String SQL_QUERY;

    private String _restaurant_name;
    private String _restaurant_address;
    private String _restaurant_recommend_food;
    private float _restaurant_grade;//ratingBar 평점
    private String _restaurant_latitude; // 위도
    private String _restaurant_longitude; // 경도
    private String _food_image_name; // 이미지 이름
    private String _postscript;


    private String _food_postscript;
    private String _restaurant_add_grade;

    //변수값 저장 생성자 오버로딩 2개


    public DatabaseQuery() {

    }

    public DatabaseQuery(String _restaurant_name, String _restaurant_address, String _restaurant_recommend_food,
                         float _restaurant_grade, String _restaurant_latitude,
                         String _restaurant_longitude, String _food_image_name, String _postscript) {
        this._restaurant_name = _restaurant_name;
        this._restaurant_address = _restaurant_address;
        this._restaurant_recommend_food = _restaurant_recommend_food;
        this._restaurant_grade = _restaurant_grade;
        this._restaurant_latitude = _restaurant_latitude;
        this._restaurant_longitude = _restaurant_longitude;
        this._food_image_name = _food_image_name;
        this._postscript = _postscript;
    }

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
                "restaurant_add_grade text,"+
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