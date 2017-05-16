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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

        testFileWrite();
        testFileRead();
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

        String targetPath = getFileStreamPath(FILENAME).getAbsolutePath();
        File file = new File(targetPath);
        Log.e("check absolutePath >>>>", targetPath);

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

            while (fis.read(buf) != -1);
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

    public void testFileWrite(){

        String contents = "this is test of binary file I/O";
        try{

            File testFile = new File(getFilesDir(), FILENAME);

            if(!testFile.exists())
                testFile.createNewFile();

//            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            FileOutputStream fos = new FileOutputStream(testFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            fos.write(contents.getBytes());

            bos.close();
            fos.close();

            Log.i("TEST",">>>>>>>"+"AbsPath"+testFile.getAbsolutePath());

        }catch(Exception e){
            e.printStackTrace();
        }

        Log.i("TEST",">>>>>>>"+"File Write Complete !!");

    }

    public void testFileRead(){
        try{

            File testFile = new File(getFilesDir(), FILENAME);

            if(!testFile.exists())
                return;

//            FileInputStream fis = openFileInput(FILENAME);
            FileInputStream fis = new FileInputStream(testFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] buf = new byte[fis.available()];
            while(fis.read(buf) != -1);

            bis.close();
            fis.close();

            Log.i("TEST",">>>>>>>"+new String(buf));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
