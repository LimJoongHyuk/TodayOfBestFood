package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button button_Search;
    private Button button_dataInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_dataInput = (Button) findViewById(R.id.btninput);
        button_Search = (Button) findViewById(R.id.btnSearch);

        button_dataInput.setOnClickListener(this::addButtonClick);
        button_Search.setOnClickListener(this::searchButtonClick);
    }

    private void addButtonClick(View view) {
            //
    }

    private void searchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivity(intent);
    }






}//end MainActivity
