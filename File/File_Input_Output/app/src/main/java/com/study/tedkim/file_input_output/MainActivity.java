package com.study.tedkim.file_input_output;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEditString;
    TextView tvResult;
    Button btnExecute, btnConfirm, btnDelete;

    static String FILENAME = "FileStreamTest.txt";
    String mSave, mLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_save:

                mSave = etEditString.getText().toString();
                fileWrite(mSave);

                break;

            case R.id.button_load:

                fileRead();
                tvResult.setText(mLoad);

                break;

            case R.id.button_delete:

                File file = new File(FILENAME);
                file.delete();

                Toast.makeText(MainActivity.this, "Delete Compelete !!", Toast.LENGTH_SHORT).show();

                break;

        }

    }

    public void fileWrite(String str) {

        try {

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();

            Log.e("getPath >>>>>>>", getFileStreamPath(FILENAME).getAbsolutePath());
            Toast.makeText(MainActivity.this, "Save Success !!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Save Fail... !!", Toast.LENGTH_SHORT).show();

        }

    }

    public void fileRead() {

        try {

            FileInputStream fis = openFileInput(FILENAME);
            // TODO - Byte 가 아냐!! byte 다...
            byte[] buf = new byte[fis.available()];

            while (fis.read(buf) != -1) ;
            fis.close();

            mLoad = new String(buf);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "File Read Fail...", Toast.LENGTH_SHORT).show();

        }

    }

    public void initView() {

        etEditString = (EditText) findViewById(R.id.editText_EditString);
        tvResult = (TextView) findViewById(R.id.textView_result);

        btnExecute = (Button) findViewById(R.id.button_save);
        btnExecute.setOnClickListener(this);

        btnConfirm = (Button) findViewById(R.id.button_load);
        btnConfirm.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(this);

    }
}