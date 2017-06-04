package com.study.tedkim.httpurlconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload;
    TextView tvHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        tvHtml = (TextView) findViewById(R.id.textView_html);

        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(this);

    }

    public String downloadHtml(){

        StringBuilder html = new StringBuilder();

        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if(responseCode == HTTP_OK){

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(isr);

                String stringLine;
                while((stringLine = br.readLine()) != null){

                    html.append(stringLine);
                }

                return html.toString();
            }

        }catch(Exception e){

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_download:

                String html = downloadHtml();
                tvHtml.setText(html);

                break;

        }

    }
}
