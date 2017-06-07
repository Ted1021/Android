package com.study.tedkim.download_manager;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload;

    DownloadManager mDM;
    long mId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDM = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        initView();

    }

    public void initView() {

        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mId != 0) {
            unregisterReceiver(mDownloadComplete);
            mId = 0;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_download:

                new AsyncTask<Void, Void, Void>(){

                    @Override
                    protected Void doInBackground(Void... params) {

                        startDownloadManager();
                        return null;
                    }

                }.execute();

                break;

        }
    }

    public void startDownloadManager() {


        Uri uri = Uri.parse("http://soen.kr/data/child2.jpg");
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("Test Download");
        request.setDescription("Now on downloading image file from URL ...");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

        mId = mDM.enqueue(request);

        try{
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DownloadManager.ACTION_DOWNLOAD_COMPLETE");

        registerReceiver(mDownloadComplete, intentFilter);

        Log.e("TEST", ">>>>>>>>>>>> end");
    }


    BroadcastReceiver mDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("DOWNLOAD", ">>>>>> Download Complete!! ");
            Toast.makeText(MainActivity.this, "Download complete !!", Toast.LENGTH_LONG).show();

        }
    };


}
