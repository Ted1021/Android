package com.study.tedkim.file_input_output;

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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_save:

                mSave = etEditString.getText().toString();
                fileWrite();

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

    // 1. binary 파일 생성
    public void fileWrite(){

        String contents = "this is test of binary file I/O";

        try{

            // 1.1 File Descriptor 를 생성 할 때에는 꼭 "getFilesFir()" 을 매개 변수로 전달 해야 한다
            File testFile = new File(getFilesDir(), FILENAME);

            // 2. File 이 존재 하는지 판단한다
            if(!testFile.exists())
                testFile.createNewFile();

            // 3. 생성 된 파일을 바탕으로 FileStream 을 열어준다
            // way_1) "openFileOutput" Method 를 불러온다 (getFilesDir 을 이용해 Desc 를 생성하지 않는 경우,)
//            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            // way_2) 새로운 FileStream 을 생성 한다
            FileOutputStream fos = new FileOutputStream(testFile);
            // 4. 속도 효율을 위해 File Buffer 를 생성한다
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            // 5. 파일을 새로이 쓴다
            // 5.1 String 을 Binary 형태로 변환 한다
            fos.write(contents.getBytes());

            // 6. File Buffer 와 File Stream 을 닫아준다
            bos.close();
            fos.close();

            Log.i("TEST",">>>>>>>"+"AbsPath"+testFile.getAbsolutePath());

        }catch(Exception e){
            e.printStackTrace();
        }

        Log.i("TEST",">>>>>>>"+"File Write Complete !!");

    }

    // binary 파일 읽기
    public void fileRead(){
        try{

            // 1. File Write 와 마찬가지로 "getFilesDir()" 을 이용해 앱의 저장 경로를 가져온다
            File testFile = new File(getFilesDir(), FILENAME);

            // 2. File 이 존재 하는지 알아보고 예외 처리를 해 준다
            if(!testFile.exists()){
                Toast.makeText(MainActivity.this, "File doesn't exist", Toast.LENGTH_SHORT).show();
                return;
            }

            // 3. 존재하는 파일을 불러오는 File Stream 을 생성한다
            // way_1) "OpenFileInput()" Method 를 불러온다 ("getFilesDir()" 로 desc 를 생성하지 않은 경우,)
//            FileInputStream fis = openFileInput(FILENAME);

            // way_2) FileInputStream 을 새로이 생성한다
            FileInputStream fis = new FileInputStream(testFile);
            // 4. 읽기 속도 향상을 위해 File Buffer 를 생성한다
            BufferedInputStream bis = new BufferedInputStream(fis);

            // 5. binary File 을 읽어온다
            // 5.1 File Stream 의 크기만큼 byte 배열을 동적 생성 한다
            byte[] buf = new byte[fis.available()];
            // 5.2 File Stream 의 끝까지 순차적으로 읽어온다
            while(fis.read(buf) != -1);

            // 6. File Buffer 와 File Stream 을 순차적으로 닫아준다
            bis.close();
            fis.close();

            Log.i("TEST",">>>>>>>"+new String(buf));

        }catch(Exception e){
            e.printStackTrace();
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