package com.example.jh.todayofbestfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private Button button_select;
    private Button button_dataInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_dataInput = (Button) findViewById(R.id.btninput);
        button_select = (Button) findViewById(R.id.btnSearch);

        button_select.setOnClickListener(this::addButtonClick);
        button_dataInput.setOnClickListener(this::searchButtonClick);
    }

    private void addButtonClick(View view) {

    }

    private void searchButtonClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivity(intent);
    }






}//end MainActivity
