package com.study.tedkim.loadingimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){

        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.imageView_image);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_download:

                break;

        }

    }
}
