package com.study.tedkim.download_manager;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload;

    DownloadManager mDM;
    long mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_download:

                startDownloadManager();

                break;

        }
    }

    public void startDownloadManager() {


    }

    public void initView() {

        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(this);
    }


}
