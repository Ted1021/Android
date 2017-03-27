package com.study.tedkim.customadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DescActivity extends AppCompatActivity {

    int mPosition=0;

    ImageView ivPlanetImage;

    TextView tvName, tvDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        initView();

        setData();

    }

    public void initView(){

        ivPlanetImage = (ImageView) findViewById(R.id.imageView_planetImage);
        tvName = (TextView) findViewById(R.id.textView_planetName);
        tvDesc = (TextView) findViewById(R.id.textView_planetDesc);

    }

    public void setData(){

        mPosition = getIntent().getIntExtra("position", 0);

        String PlanetName = PlanetInfo.PLANET.get(mPosition);
        String PlanetDesc = PlanetInfo.DESC.get(mPosition);

        tvName.setText(PlanetName);
        tvDesc.setText(PlanetDesc);

    }


}
