package com.study.tedkim.json_parser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvResult;
    Button btnParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            initView();
        }

    public void initView(){

        tvResult = (TextView) findViewById(R.id.textView_result);
        btnParse = (Button) findViewById(R.id.button_parseJson);
        btnParse.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_parseJson:

                
                break;

        }
    }
}

