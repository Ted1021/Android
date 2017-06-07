package com.study.tedkim.json_parser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvResult;
    Button btnParse;

    static final String tempJsonArray = "[1,2,3,4,5,6,7,8,9,10]";


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

                int sum = 0;

                try {

                    JSONArray ja = new JSONArray(tempJsonArray);
                    for(int i=0; i<tempJsonArray.length(); i++){

                        sum += ja.getInt(i);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tvResult.setText(sum+"");

                break;

        }
    }
}

