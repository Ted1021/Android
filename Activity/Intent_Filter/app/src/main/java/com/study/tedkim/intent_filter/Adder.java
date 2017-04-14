package com.study.tedkim.intent_filter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Adder extends AppCompatActivity {

    TextView tvFirst, tvSecond, tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder);

        initView();

        setIntentData();
    }

    public void initView(){

        tvFirst = (TextView) findViewById(R.id.textView_first);
        tvSecond = (TextView) findViewById(R.id.textView_second);
        tvAnswer = (TextView) findViewById(R.id.textView_answer);

    }

    public void setIntentData(){

        Intent intent = getIntent();

        int first = intent.getIntExtra("FIRST", 0);
        int second = intent.getIntExtra("SECOND", 0);
        int answer = first + second;

        tvFirst.setText(first+"");
        tvSecond.setText(second+"");
        tvAnswer.setText(answer+"");

    }

}
