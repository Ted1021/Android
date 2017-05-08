package com.study.tedkim.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    EditText etNumber;
    Button btnSquare, btnRoot;

    Handler mHandler;

    CalThread calThread;

    public static final int SQUARE = 0;
    public static final int ROOT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setThread();
    }

    public void initView() {

        tvResult = (TextView) findViewById(R.id.textView_result);
        etNumber = (EditText) findViewById(R.id.editText_number);

        btnSquare = (Button) findViewById(R.id.button_square);
        btnSquare.setOnClickListener(this);

        btnRoot = (Button) findViewById(R.id.button_root);
        btnRoot.setOnClickListener(this);

    }

    public void setThread() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch(msg.what){

                    case ROOT:
                        Toast.makeText(MainActivity.this, "ROOT is executed", Toast.LENGTH_SHORT).show();
                        break;

                    case SQUARE:
                        Toast.makeText(MainActivity.this, " SQUARE is executed", Toast.LENGTH_SHORT).show();
                        break;

                }
                String val = msg.arg1+"";
                tvResult.setText(val);
            }

        };

        calThread = new CalThread(mHandler);
        calThread.setDaemon(true);
        calThread.start();

    }

    @Override
    public void onClick(View v) {

        Message msgOperation = new Message();
        int num = Integer.parseInt(etNumber.getText().toString());

        switch (v.getId()) {

            case R.id.button_square:

                msgOperation.what = SQUARE;
                msgOperation.arg1 = num;

                calThread.mOrderHandler.sendMessage(msgOperation);

                break;

            case R.id.button_root:

                msgOperation.what = ROOT;
                msgOperation.arg1 = num;

                calThread.mOrderHandler.sendMessage(msgOperation);

                break;
        }
    }
}
