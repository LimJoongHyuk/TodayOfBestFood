package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by jh on 2017-05-22.
 */

public class DataInputActivity extends AppCompatActivity {

    public static final String REGISTER_KEY = "REGISTER";
    public static final String SELF_KEY = "SELFKEY";
    String isName = "DataInputActivity";

    private static final int GETPHOTO_ACTIVITY = 104;
    private static final int TO_PARCELABLE = 106;

    ImageView imageView_goodfood;

    EditText editText_restaurantName;
    EditText editText_restaurant_recommend_food;
    EditText editText_postscript;

    RadioGroup rg;
    RadioButton rd;

    RatingBar ratingBar;

    //서비스 클래스 사용


    String imgPath;
    String imgName;
    String tagName;
    CameraActionService cameraActionService;
    GPSService gpsService;
    private String _restaurantName;
    private String _recommendMenu;
    private float _restaurantgrade;
    private String _latitude;
    private String _longitude;
    private String _foodImgName;
    private String _foodTagName;
    private String _foodPostscript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datainput);

        imageView_goodfood = (ImageView)findViewById(R.id.imageView_goodfood);
        editText_restaurantName = (EditText)findViewById(R.id.editText_restaurantName);
        editText_restaurant_recommend_food = (EditText)findViewById(R.id.editText_restaurant_recommend_food);
        editText_postscript = (EditText)findViewById(R.id.editText_postscript);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rd = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        ratingBar = (RatingBar)findViewById(R.id.ratingBar_total_grade);
        cameraActionService = new CameraActionService(this);    //카메라 서비스 생성

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                          @Override
                                          public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                                              switch(checkedId) {
                                                  case R.id.radioButton_chinesefood:
                                                      tagName = "중식";
                                                      break;
                                                  case R.id.radioButton_koreanfood:
                                                      tagName = "한식";
                                                      break;
                                                  case R.id.radioButton_japansefood:
                                                      tagName = "일식";
                                                      break;
                                                  case R.id.radioButton_fastfood:
                                                      tagName = "패스트푸드";
                                                      break;
                                                  default:
                                                      tagName = "한식";
                                                      break;
                                              }
                                          }
                                      });

                gpsService = new GPSService(DataInputActivity.this);                       //gps 서비스 생성
        //위도 경도 GPS 가져오기
        gpsService.startLocationService();

        Button btn_dataInput = (Button)findViewById(R.id.button_input);
        imageView_goodfood.setOnClickListener(this::getAlbumphotoClick);

        btn_dataInput.setOnClickListener(this::dataInputClick);

    }


    // 등록버튼 클릭
    private void dataInputClick(View view) {
        sendData();
    }
    //이미지 가져오기
    private void getAlbumphotoClick(View view) {
        cameraActionService.getAlbumPhoto();
    }


    //데이터 DB실행
    private void sendData(){

        Intent intent = new Intent(this,DatabaseQueryService.class);
        getData();
        FoodofBestParcelable foodofBestParcelable = new FoodofBestParcelable(_restaurantName,_recommendMenu,_restaurantgrade,_foodPostscript,
                                                                                _latitude,_longitude,_foodImgName,tagName);

     //테스트코드
    //    FoodofBestParcelable foodofBestParcelable = new FoodofBestParcelable("1","2",1,"3","4","5","6","7");

        //자신을 알리는 키도 추가
        intent.putExtra(REGISTER_KEY,foodofBestParcelable);
        intent.putExtra(SELF_KEY,isName);
        startActivityForResult(intent,TO_PARCELABLE);






    }
    private void getData(){

          _restaurantName = editText_restaurantName.getText().toString();
          _recommendMenu = editText_restaurant_recommend_food.getText().toString();
          _restaurantgrade = ratingBar.getRating() ;
          _latitude = gpsService.getLatitude().toString();
          _longitude = gpsService.getLongitude().toString();
          _foodImgName = imgName;
          _foodTagName = rd.getText().toString();
          _foodPostscript = editText_postscript.getText().toString();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GETPHOTO_ACTIVITY) {
            try {
                imgPath = getImageNameToUri(data.getData());
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                imageView_goodfood.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(requestCode == TO_PARCELABLE){

            setResult(RESULT_OK);
            finish();
        }
    }

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        imgPath = cursor.getString(column_index);
        imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        Toast.makeText(getApplicationContext(), imgPath, Toast.LENGTH_SHORT).show();

        return imgPath;
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
