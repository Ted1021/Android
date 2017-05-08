package com.study.tedkim.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by tedkim on 2017. 5. 1..*/

public class CalThread extends Thread {

    Handler mCalHandler;
    public static Handler mOrderHandler;

    public CalThread(Handler handler){

        mCalHandler = handler;
    }

    @Override
    public void run() {
        super.run();

        // 외부 클래스에서 메세지를 핸들링 하기 위해서는
        // 큐를 관리하는 루퍼(Looper)가 필요하다
        // 사용법은 매우 간단!
        // 메세지를 받기 전에 루퍼를 준비(prepare) 하고
        // 마지막에 루퍼를 실행(loop) 시킴으로써 구현 가능하다

        // 1. 루퍼(Looper) 를 준비(Prepare) 시킨다
        Looper.prepare();

        mOrderHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Message rtnMsg = new Message();

                switch(msg.what){

                    case MainActivity.ROOT:

                        rtnMsg.what = MainActivity.ROOT;
                        rtnMsg.arg1 = (int)Math.sqrt(msg.arg1);

                        break;

                    case MainActivity.SQUARE:

                        rtnMsg.what = MainActivity.SQUARE;
                        rtnMsg.arg1 = (int)Math.pow(msg.arg1, 2);

                        break;

                    default:

                        rtnMsg.what = -1;
                        break;
                }

                mCalHandler.sendMessage(rtnMsg);
            }
        };

        // 2. 루퍼를 동작(loop) 시킨다
        Looper.loop();
    }
}