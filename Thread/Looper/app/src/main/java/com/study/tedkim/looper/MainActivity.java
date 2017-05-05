package com.study.tedkim.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvResult;
    EditText etNumber;
    Button btnSquare, btnRoot;

    Handler mHandler;

    public static final int SQUARE = 0;
    public static final int ROOT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setThread();
    }

    public void initView(){

        tvResult = (TextView) findViewById(R.id.textView_result);
        etNumber = (EditText) findViewById(R.id.editText_number);

        btnSquare = (Button) findViewById(R.id.button_square);
        btnSquare.setOnClickListener(this);

        btnRoot = (Button) findViewById(R.id.button_root);
        btnRoot.setOnClickListener(this);

    }

    public void setThread(){

        CalThread calThread = new CalThread(mHandler);
        calThread.setDaemon(true);
        calThread.start();

    }

    @Override
    public void onClick(View v) {

        Message msgOperation=null;
        int num = Integer.parseInt(etNumber.getText().toString());

        switch(v.getId()){

            case R.id.button_square:

                msgOperation.what = SQUARE;
                msgOperation.arg1 = num;

                mHandler.sendMessage(msgOperation);

                break;

            case R.id.button_root:

                msgOperation.what = ROOT;
                msgOperation.arg1 = num;

                mHandler.sendMessage(msgOperation);

                break;
        }
    }
}
