package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button _SearchButton;
    private Button _inputButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _inputButton = (Button) findViewById(R.id.btninput);
        _SearchButton = (Button) findViewById(R.id.btnSearch);

        _inputButton.setOnClickListener(this::inputButtonClick);
        _SearchButton.setOnClickListener(this::searchButtonClick);
    }

    private void inputButtonClick(View view) {

    }

    private void searchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivity(intent);
    }




}//end MainActivity
