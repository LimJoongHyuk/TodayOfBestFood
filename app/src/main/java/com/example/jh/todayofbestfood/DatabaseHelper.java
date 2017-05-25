package com.example.jh.todayofbestfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static java.sql.DriverManager.println;

/**
 * Created by smh on 2017-05-23.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "TodayOfBestFood";
    private static int DB_VERSION = 1;
    private DatabaseQueryService db_query;

    public static final String REGISTER_KEY = "REGISTER";
    public static final String REVIEW_KEY = "REVIEW";

    private Context _context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        _context = context;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        println("버전" + oldVersion + " 에서 버전" + newVersion + " 으로 다운데이트 되었습니다.");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db_query = new DatabaseQueryService();
            db.execSQL(db_query.onCreateTable_restaurant().toString());
            db.execSQL(db_query.onCreateTable_review().toString());
            Toast.makeText(_context, "Table 생성완료", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            String test = db_query.onCreateTable_review().toString();
            Toast.makeText(_context, test, Toast.LENGTH_LONG).show();
            Toast.makeText(_context, "Table 생성실패", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("버전" + oldVersion + " 에서 버전" + newVersion + " 으로 업데이트 되었습니다.");
    }



}
