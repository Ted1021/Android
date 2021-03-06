package com.study.tedkim.httpurlconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    public void initView() {

        tvHtml = (TextView) findViewById(R.id.textView_html);

        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(this);

    }

    public void downloadHtml() {

        new AsyncTask<Void, String, String>(){

            // initView
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // TODO - Progress Dial Set
            }

            // Background process using thread
            @Override
            protected String doInBackground(Void... params) {

                try{

                    // HtmlURLConnection 을 이용하기 위한 필수 메소드 네가지
                    // 1. URL.openConnection()  : 특정 URL 에 접근해 connection 객체를 얻는다
                    // 2. Connection.setRequestMethod()     : 전달받은 URL HOST 에게 요청 할 명령삽입 ex) GET
                    // 3. Connection.getResponseCode()      : 요청에 대한 Host 의 Response (결과값) 을 체크
                    // 4. Connection.getInputStream()        : HTML 파일을 InputStream 으로 읽어옴

                    URL url = new URL("http://www.google.com");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    if(connection.getResponseCode() == HTTP_OK){

                        StringBuilder html = new StringBuilder();

                        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                        BufferedReader br = new BufferedReader(isr);

                        String singleLine;
                        while((singleLine = br.readLine()) != null)
                            html.append(singleLine);

                        publishProgress(html.toString());

                        return html.toString();
                    }

                }catch(Exception e){

                    e.printStackTrace();
                }

                return null;
            }

            // control view component like handler
            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);

                String targetHtml = values[0];
                tvHtml.setText(targetHtml);
            }

            //
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s==null)
                    Toast.makeText(MainActivity.this, "Download Failed...", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Download Complete!", Toast.LENGTH_SHORT).show();


                // TODO - Progress bar dismissed
            }
        }.execute();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_download:

                downloadHtml();

                break;

        }

    }

}
