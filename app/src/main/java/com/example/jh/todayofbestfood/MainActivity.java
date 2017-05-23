package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    File file = null;
    private Button button_select;
    private Button button_dataInput;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_dataInput = (Button) findViewById(R.id.btnAdd);
        button_select = (Button) findViewById(R.id.btnSearch);

        button_select.setOnClickListener(this::searchButtonClick);
        button_dataInput.setOnClickListener(this::addButtonClick);
    }

    private void addButtonClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        }
        intent = new Intent(getApplicationContext(), DataInputActivity.class);
        startActivity(intent);
    }

    private void searchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
            imageView.setImageBitmap(bitmap);
        }
    }

    private File CreateFile() throws IOException{
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir,imageFileName);
        return curFile;
    }




}//end MainActivity
