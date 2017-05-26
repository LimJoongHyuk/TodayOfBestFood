package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private Button button_Search;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_DATA_INPUT = 102;
    private static final int REQUEST_DATA_SEARCH = 103;

    private static final String CAMERA = "CAMERA";
    private static final String GPS = "GPS";

    private Button button_select;
    private Button button_dataInput;
    ImageView imageView;
    private GPSService _gpsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _gpsService = new GPSService(MainActivity.this);
        if(_gpsService.isGpsCheck() == false) createAlertDialog("GPS");

        imageView = (ImageView)findViewById(R.id.imageView_logo);
        button_dataInput = (Button) findViewById(R.id.btnAdd);
        button_Search = (Button) findViewById(R.id.btnSearch);


        button_dataInput = (Button) findViewById(R.id.btnAdd);
        button_select = (Button) findViewById(R.id.btnSearch);

        button_dataInput.setOnClickListener(this::addButtonClick);
        button_select.setOnClickListener(this::searchButtonClick);
    }

    private void addButtonClick(View view) {

        createAlertDialog("CAMERA");
    }

    private void searchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivityForResult(intent, REQUEST_DATA_SEARCH);;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == RESULT_OK){


            Intent intent = new Intent(this, DataInputActivity.class);

            startActivityForResult(intent, REQUEST_DATA_INPUT);

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

    public void createAlertDialog(String requester) {
        AlertDialog.Builder dig = new AlertDialog.Builder(MainActivity.this);
        dig.setTitle(requester);
        dig.setMessage(requester + "를 실행 하시겠습니까?");
        dig.setPositiveButton("예", (dialog, which) -> {
            if(requester.equals(CAMERA)) {
                CameraActionService cameraActionService = new CameraActionService(MainActivity.this);
                cameraActionService .imageToInput();
            } else if(requester.equals(GPS)) {
                _gpsService.turnOnGps();
            }
        });
        dig.setNegativeButton("아니요", (dialog, which) -> {

            if(requester.equals(CAMERA)) {
                Intent intent = new Intent(getApplicationContext(), DataInputActivity.class);
                startActivityForResult(intent,REQUEST_DATA_INPUT);
            } else if(requester.equals(GPS)) {
                finish();
            }
        });

        dig.show();
    }


}//end MainActivity
