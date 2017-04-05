package com.study.tedkim.slidingpanelayout;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    SlidingPaneLayout slidingLayout;
    Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        slidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
        btnOpen = (Button) findViewById(R.id.button_open);
        btnOpen.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_open){

            slidingLayout.openPane();
        }
    }
}
