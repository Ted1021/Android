package com.study.tedkim.process_scheduling;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnUpload;
    Handler mHandler;

    static final int EXECUTE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(msg.what==0)
                    exeUpload();
            }
        };

        btnUpload = (Button) findViewById(R.id.button_upload);
        btnUpload.setOnClickListener(this);
    }

    public void createDialog(){

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle("Question");
        dialog.setMessage("Do you want to upload?");

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                exeUpload();
                mHandler.sendEmptyMessageDelayed(EXECUTE, 100);
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Upload is canceled", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_upload:

                createDialog();

                break;
        }

    }

    public void exeUpload() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Upload Complete", Toast.LENGTH_SHORT).show();
    }

}
