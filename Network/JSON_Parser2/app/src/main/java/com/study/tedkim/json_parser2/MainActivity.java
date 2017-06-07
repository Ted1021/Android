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

    public void parsingJsonObject(){

        String product, maker, price, result;
        StringBuilder sb = new StringBuilder();

        try{

            JSONArray ja = new JSONArray(JSON);
            for(int i=0; i<ja.length(); i++){

                JSONObject jo = ja.getJSONObject(i);

                product = jo.getString("Product");
                maker = jo.getString("Maker");
                price = jo.getString("Price");

                result = "제품명 : " + product + " 제조사 : " + maker + " 가 격 : " + price + "\n";

                sb.append(result);

            }


        }catch(Exception e){
            e.printStackTrace();
        }

        tvResult.setText(sb);

    }
}
