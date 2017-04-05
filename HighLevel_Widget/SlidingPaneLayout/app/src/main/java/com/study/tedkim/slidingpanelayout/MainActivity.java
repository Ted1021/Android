package com.study.tedkim.slidingpanelayout;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SlidingPaneLayout slidingPannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingPannel = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);

    }

}
