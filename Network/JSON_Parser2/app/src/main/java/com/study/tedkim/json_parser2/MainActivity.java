package com.study.tedkim.json_parser2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnParse;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initView(){

        tvResult = (TextView) findViewById(R.id.textView_result);
        btnParse = (Button) findViewById(R.id.button_parse);
        btnParse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_parse:

                parsingJsonObject();

                break;

        }

    }

    public void parsingJsonObject(){


    }
}
