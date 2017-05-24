package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private Button button_Search;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_DATA_INPUT = 102;
    private static final int REQUEST_DATA_SEARCH = 103;

    File file = null;
    private Button button_select;
    private Button button_dataInput;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView_logo);
        button_dataInput = (Button) findViewById(R.id.btnAdd);
        button_Search = (Button) findViewById(R.id.btnSearch);


        button_dataInput = (Button) findViewById(R.id.btnAdd);
        button_select = (Button) findViewById(R.id.btnSearch);

        button_dataInput.setOnClickListener(this::addButtonClick);


        button_Search.setOnClickListener(this::searchButtonClick);
    }

    private void addButtonClick(View view) {
        CameraActionService cameraActionService = new CameraActionService(this);
        cameraActionService .imageToInput();

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

    private File createFile() throws IOException {
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir,imageFileName);
        return curFile;
    }




}//end MainActivity
