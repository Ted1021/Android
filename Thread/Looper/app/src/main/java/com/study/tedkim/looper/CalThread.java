package com.study.tedkim.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by tedkim on 2017. 5. 1..*/

public class CalThread extends Thread {

    Handler mMainHandler, mSubHandler;


    public CalThread(Handler handler){

        mMainHandler = handler;
        math();

    }

    public void math(){

        Looper.prepare();
        mSubHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                double num = msg.arg1;
                double result=0;

                Message returnMsg = new Message();

                switch(msg.what){

                    case MainActivity.SQUARE:

                        result = Math.pow(num,2);
                        returnMsg.what = 1;
                        returnMsg.obj = result;

                        break;

                    case MainActivity.ROOT:

                        result = Math.sqrt(num);
                        returnMsg.what = 1;
                        returnMsg.obj = result;

                        break;
                }

                mMainHandler.sendMessage(returnMsg);
            }
        };
        Looper.loop();
    }
}