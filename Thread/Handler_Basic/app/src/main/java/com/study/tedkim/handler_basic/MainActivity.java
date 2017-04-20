package com.study.tedkim.handler_basic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final int MESSAGE = 0;

    TextView tvMainThread, tvMsgThread, tvRunThread;
    Button btnMessageThread, btnRunnableThread, btnMainThread;

    Handler mMsgHandler, mRunHandler;

    int mMainVal=0, mMsgVal=0, mRunVal=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        tvMainThread = (TextView) findViewById(R.id.textView_mainThread);
        tvMsgThread = (TextView) findViewById(R.id.textView_msgThread);
        tvRunThread = (TextView) findViewById(R.id.textView_runThread);

        btnMessageThread = (Button) findViewById(R.id.button_messageHandler);
        btnMessageThread.setOnClickListener(this);

        btnRunnableThread = (Button) findViewById(R.id.button_runnableHandler);
        btnRunnableThread.setOnClickListener(this);

        btnMainThread = (Button) findViewById(R.id.button_mainThread);
        btnMainThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            // "MESSAGE HANDLER" 버튼을 클릭하였을 때, Thread 와 Handler 의 작업을 정의한다
            case R.id.button_messageHandler:

                // 1. MainActivity 에 Message Handler 를 부착한다
                mMsgHandler = new MessageHandler();

                // 2. sendEmptyMessage() 가 정의 된 MessageThread 를 생성하고
                MessageThread mMsgThread = new MessageThread();
                mMsgThread.setDaemon(true);
                // 3. mMsgThread 를 실행시킨다
                mMsgThread.start();

                break;

            // "Runnable HANDLER" 버튼을 클릭하였을 때, Thread 와 Handler 의 작업을 정의한다
            case R.id.button_runnableHandler:

                // 1. MainActivity 에 Runnable Handler 를 부착한다
                mRunHandler = new Handler();

                // 2. handler.post() 가 정의 된 RunnableThread 를 생성하고
                RunnableThread mRunThread = new RunnableThread();
                mRunThread.setDaemon(true);
                // 3. mRunThread 를 실행한다
                mRunThread.start();

                break;

            // 단순히 Main Thread 에서 사용자가 직접 값을 증가시키는 동작을 정의한다
            case R.id.button_mainThread:

                mMainVal++;
                tvMainThread.setText(mMainVal+"");

                break;

        }
    }

    // Handler 를 이용해 Message 를 수신자 (MainActivity) 에 보낸다.
    public class MessageThread extends Thread{

        // Thread 내에서 sendEmptyMessage() 를 이용해 수신자에게 메세지를 실시간으로 전달한다
        @Override
        public void run() {
            super.run();

            // 1. 원하는 값을 증가시키고, UI Thread 를 직접 Control 하기위해 Message 를 발신한다
            while(true){

                // 1.1 mMsgVal 를 1씩 증가 시킨다
                mMsgVal++;
                // 1.2 MainActivity 에 부착된 mMsgHandler 에 특정 Message 를 전달한다
                // 전달하는 메세지의 종류에 따라 MainActivtiy 에서 서로 다르게 동작하도록 만들 수 있다
                mMsgHandler.sendEmptyMessage(MESSAGE);

                // 2. 1초 간격으로 위의 작업들을 반복적으로 수행한다
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // Handler 에 Runnable 객채를 전달하여(Post) Handler 내에서 UI 를 직접 Control 한다
    public class RunnableThread extends Thread{

        // Thread 내에서 handler.post() 를 이용해 직접 UI Component 에 접근한다
        @Override
        public void run() {
            super.run();

            // 1. 원하는 값을 증가 시키고, UI Thread 에 직접 접근해 값을 적용한다
            while(true){

                // 1.1 mRunVal 을 1씩 증가 시킨다
                mRunVal++;
                // 1.2 MainActivity 에 부착된 mRunHandler.post() 를 이용해 직접 UI 를 Control 한다
                mRunHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 1.2.1 텍스트 뷰인 tvRunThread 를 직접 접근한다
                        tvRunThread.setText(mRunVal+"");
                    }
                });

                // 2. 1초 간격으로 위의 작업들을 반복적으로 수행한다
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // 기존의 Handler 가 아닌 Message 기능을 처리 할 수 있는 MessageHandler 를 Custom 한다
    public class MessageHandler extends Handler{

        //
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what){

                case MESSAGE:

                    tvMsgThread.setText(mMsgVal+"");

                    break;

            }
        }
    }


}
