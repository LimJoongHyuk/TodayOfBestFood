package com.example.jh.todayofbestfood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.jh.todayofbestfood.R.id.btnAdd;
import static com.example.jh.todayofbestfood.R.id.btnSelect;

public class MainActivity extends AppCompatActivity {

    private Button button_select;
    private Button button_dataInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_select = (Button) findViewById(btnSelect);
        button_dataInput = (Button) findViewById(btnAdd);

    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch ( v.getId() ) {
                case btnSelect:

                    break;

                case btnAdd:

                    break;
            }
        }
    };
}
