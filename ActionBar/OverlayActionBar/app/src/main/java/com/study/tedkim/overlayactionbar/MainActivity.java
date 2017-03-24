package com.study.tedkim.overlayactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnActionBarOverlay;
    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_overlay:

                mActionBar = getSupportActionBar();

                if(mActionBar.isShowing()){

                    mActionBar.hide();
                    btnActionBarOverlay.setText("Show this ActionBar");
                }
                else{

                    mActionBar.show();
                    btnActionBarOverlay.setText("Hide this ActionBar");
                }
                break;

        }
    }

    public void initView(){

        btnActionBarOverlay = (Button) findViewById(R.id.button_overlay);
        btnActionBarOverlay.setOnClickListener(this);

    }

}
