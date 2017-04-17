package com.study.tedkim.thread_basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvMainThread, tvSubThread;
    Button btnCheck, btnSetThread, btnSetRunnable, btnClear;

    public static int mMainCount=0, mSubCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        tvMainThread = (TextView) findViewById(R.id.textView_mainThread);
        tvSubThread = (TextView) findViewById(R.id.textView_subThread);

        btnCheck = (Button) findViewById(R.id.button_check);
        btnCheck.setOnClickListener(this);

        btnSetThread = (Button) findViewById(R.id.button_setThread);
        btnSetThread.setOnClickListener(this);

        btnSetRunnable = (Button) findViewById(R.id.button_setRunnable);
        btnSetRunnable.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.button_clear);
        btnClear.setOnClickListener(this);

    }

    // Thread 를 초기화하고 실행시킵니다
    public void setThread(){

        // 1. Custom 한 Thread 를 생성합니다
        TedThread mThread = new TedThread();
        // 2. setDaemon 을 활성화 하면 MainThread 와 함께 죽습니다
        mThread.setDaemon(true);
        // 3. Thread 를 시작합니다
        mThread.start();
    }

    // Runnable 을 초기화하고 실행시킵니다
    public void setRunnable(){

        // 1. Custom 한 Runnable 객체를 생성합니다
        TedRunnable runnable = new TedRunnable();
        // 2. 기본 Thread 를 생성하고 미리 만들어둔 Runnable 객체로 초기화 합니다
        Thread mRunnable = new Thread(runnable);
        // 3. 마찬가지로 setDeamon 을 활성화 시킵니다
        mRunnable.setDaemon(true);
        // 4. Thread 를 시작합니다
        mRunnable.start();

    }

    public void setTextView(){
        tvMainThread.setText(mMainCount+"");
        tvSubThread.setText(mSubCount+"");
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_check:

                mMainCount++;
                setTextView();

                break;

            case R.id.button_setThread:

                setThread();

                break;

            case R.id.button_setRunnable:

                setRunnable();

                break;

            case R.id.button_clear:

                mMainCount=0;
                mSubCount=0;

                setTextView();

                break;

        }
    }
}
