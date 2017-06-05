package com.study.tedkim.loadingimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload;
    ImageView ivImage;

    ImageHandler mHandler;

    static final int SEND_IMAGE = 1;

    static final int SUCCESS = 101;
    static final int FAILED = 404;
    static final String TARGET_URL = "https://www.fingo.vn/cdn/images/logo.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView() {

        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(this);

        ivImage = (ImageView) findViewById(R.id.imageView_image);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_download:

                getUrl(TARGET_URL);

                break;

        }

    }

    public void getUrl(String targetUrl) {

        new AsyncTask<String, Bitmap, Void>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(String... params) {

                try {

                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode() == HTTP_OK){

                        InputStream is = connection.getInputStream();
                        // TODO - Bitmap factory 에 대해 알아 볼 것
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        publishProgress(bitmap);

                        is.close();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Bitmap... values) {
                super.onProgressUpdate(values);

                ivImage.setImageBitmap(values[0]);

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


            }
        }.execute(targetUrl);
    }


    /*************************************************/

    public class ImageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == SEND_IMAGE){

                Bitmap bitmap = (Bitmap) msg.obj;
                ivImage.setImageBitmap(bitmap);

            }
        }
    }

    public class DownloadThread extends Thread{

        String mUrl;

        public DownloadThread(String targetUrl){

            mUrl = targetUrl;

        }
        @Override
        public void run() {
            super.run();

            getUrl(mUrl);
        }
    }
}
