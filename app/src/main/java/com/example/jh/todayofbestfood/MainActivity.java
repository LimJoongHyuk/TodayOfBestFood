package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

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

        imageView = (ImageView)findViewById(R.id.imageView);

        button_dataInput = (Button) findViewById(R.id.btnAdd);
        button_select = (Button) findViewById(R.id.btnSearch);

        button_select.setOnClickListener(this::searchButtonClick);
        button_dataInput.setOnClickListener(this::addButtonClick);

        try {
        file = createFile();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void addButtonClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
/*
        if (intent.resolveActivity(getPackageManager()) != null) {

        }
*/
    }

    private void searchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivityForResult(intent, REQUEST_DATA_SEARCH);;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode == REQUEST_IMAGE_CAPTURE /* && resultCode == RESULT_OK*/){

            Bitmap temp = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(temp);
            /*
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
            imageView.setImageBitmap(bitmap);
*/
            Intent intent = new Intent(getApplicationContext(), DataInputActivity.class);
            startActivityForResult(intent, REQUEST_DATA_INPUT);
        }
    }

    private File createFile() throws IOException{
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir,imageFileName);
        return curFile;
    }




}//end MainActivity
