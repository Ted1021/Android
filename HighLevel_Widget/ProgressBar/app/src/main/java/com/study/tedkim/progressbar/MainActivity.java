package com.study.tedkim.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar pbHorizontal, pbCircle;
    Button btnExcute, btnInitProg;

    TextView tvPercentage;

    int mPercentage;

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        pbHorizontal = (ProgressBar) findViewById(R.id.progressBar_horizontal);
        pbCircle = (ProgressBar) findViewById(R.id.progressBar_circle);

        tvPercentage = (TextView) findViewById(R.id.textView_percentage);

        btnExcute = (Button) findViewById(R.id.button_excute);
        btnExcute.setOnClickListener(this);

        btnInitProg = (Button) findViewById(R.id.button_init);
        btnInitProg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            // ProgressBar 를 실행시키는 Button 구현
            case R.id.button_excute:

                // 1. Percent Counting 을 위한 Thread 실행
                ProgThread thread = new ProgThread();
                thread.setDaemon(true);
                thread.start();

                break;

            // mPercentage 및 ProgressBar 들을 초기화 한다.
            case R.id.button_init:
                mPercentage = 0;
                tvPercentage.setText("0 %");
                pbHorizontal.setProgress(0);

                break;

        }

    }

    // 0.01 초 단위로 Percentage 가 1씩 증가하는 Thread 구현
    public class ProgThread extends Thread{
        @Override
        public void run() {
            super.run();

            // 1. 100 % 가 될 때까지 아래의 작업을 반복한다.
            while(mPercentage < 100){

                // 1.1 1% 씩 percent 를 증가시킨다.
                mPercentage++;

                // 1.2 UI Control 을 위해 Handler 를 선언한다.
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        // 1.2.1 Circle 형태의 ProgressBar 는 Thread 가 실행중인동안 VISIBLE 상태를 유지한다.
                        pbCircle.setVisibility(VISIBLE);
                        // 1.2.2 증가시킨 mPercentage 를 Horizontal 형 ProgressBar 에 적용시킨다.
                        pbHorizontal.setProgress(mPercentage);
                        // 1.2.3 Percentage 를 보여주는 TextView 또한 Update 시켜준다.
                        tvPercentage.setText(mPercentage+"" + " %");

                        // 1.2.4 Percentage 가 꽉 찼다면,
                        if(mPercentage == 100) {
                            // 1.2.4.1 Circle ProgressBar 를 없앤다.
                            pbCircle.setVisibility(GONE);
                            // 1.2.4.2 완료메세지 출력
                            Toast.makeText(getApplicationContext(), "Download Complete!!", Toast.LENGTH_SHORT).show();
//                            Thread.interrupted();
                        }
                    }
                });

                // 1.3 Thread.sleep() 메소드를 통해 0.01초 마다 한번씩 위의 작업들을 수행한다.
                try {
                    Thread.sleep(10);

                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
