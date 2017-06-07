package com.study.tedkim.json_parser2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnParse;
    TextView tvResult;

    static final String JSON = "[{\"Product\":\"Mouse\", \"Maker\":\"Samsung\", \"Price\":23000}," +
                                "{\"Product\":\"KeyBoard\", \"Maker\":\"LG\", \"Price\":12000}," +
                                "{\"Product\":\"HDD\", \"Maker\":\"WD\", \"Price\":156000}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

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

    // JSON Array 내에 있는 JSON Object 들에 접근하는 메소드
    public void parsingJsonObject(){

        String product, maker, price, result;
        StringBuilder sb = new StringBuilder();

        try{

            // 1. JSON Array 가 기록 된 String 변수 명으로 JSON Array 객체를 생성한다
            JSONArray ja = new JSONArray(JSON);
            // 2. JSON Array 내에 있는 JSON Object 에 순차적으로 접근한다
            for(int i=0; i<ja.length(); i++){

                // 2.1 기존의 JSONObject 또한 String 변수명으로 객체를 생성해야 한다
                // 하지만 getJSONObject 메소드는 JSON Array에 있는 Object 들을 접근하기 위한
                // 메소드들로써 index 를 이용해 접근 가능하다
                JSONObject jo = ja.getJSONObject(i);

                // 2.2 JSON Object 내의 elements 들 또한 변수명을 key 값으로 삼아 데이터를 가져온다
                product = jo.getString("Product");
                maker = jo.getString("Maker");
                price = jo.getString("Price");

                // 2.3 순차적으로 가져온 데이터 결과값을 정리해 준다
                result = "제품명 : " + product + " 제조사 : " + maker + " 가 격 : " + price + "\n";
                sb.append(result);

            }


        }catch(Exception e){
            e.printStackTrace();
        }

        tvResult.setText(sb);

    }
}
