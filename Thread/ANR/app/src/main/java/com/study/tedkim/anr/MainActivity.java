package com.study.tedkim.anr;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvIncrease;
    Button btnIncrease, btnUpload;

    static final int EXECUTE = 101;
    static final int FAIL = 404;

    int mNum=0;
    boolean statusUpload = false;

    // 이렇게하면 어느 쓰레드에 부착이 된 것이라 볼 수 있는가?? (수신자 쓰레드는 무엇?)
    CustomHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mHandler = new CustomHandler();

    }

    public void initView(){

        tvIncrease = (TextView) findViewById(R.id.textView_increase);

        btnIncrease = (Button) findViewById(R.id.button_increase);
        btnIncrease.setOnClickListener(this);

        btnUpload = (Button) findViewById(R.id.button_upload);
        btnUpload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_increase:

                mNum++;
                tvIncrease.setText(mNum+"");

                break;

            case R.id.button_upload:

                if(statusUpload){
                    Toast.makeText(MainActivity.this, "uploading is fail...", Toast.LENGTH_SHORT).show();
                    return;
                }

                Anti_ANR_Thread mThread = new Anti_ANR_Thread();
                mThread.setDaemon(true);
                statusUpload = true;
                mThread.start();

                break;
        }
    }

    // 시간이 걸리는 작업은 Thread 로 분리
    public class Anti_ANR_Thread extends Thread{

        @Override
        public void run() {
            super.run();

            exeUpload();
            mHandler.sendEmptyMessageDelayed(EXECUTE, 100);
        }
    }

    // Thread 의 결과는 Handler 에서 출력/관리
    public class CustomHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what){

                // 결과
                case EXECUTE:

                    Toast.makeText(MainActivity.this, "uploading is complete!!", Toast.LENGTH_SHORT).show();

                    break;

            }
            statusUpload = false;
        }
    }

    // 작업
    public void exeUpload(){

        for(int i=0; i<20; i++) {

            try {
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
