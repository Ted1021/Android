package com.study.tedkim.sd_card;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTest, btnSave, btnLoad;
    TextView tvResult;
    String mSdPath;

    static final String FILENAME = "testFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            mSdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else
            mSdPath = Environment.MEDIA_UNMOUNTED;

        initView();
    }

    public void initView() {

        tvResult = (TextView) findViewById(R.id.textView_result);

        btnTest = (Button) findViewById(R.id.button_test);
        btnTest.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);

        btnLoad = (Button) findViewById(R.id.button_load);
        btnLoad.setOnClickListener(this);

    }

    public void findExternalPath() {

        String rootDir = Environment.getRootDirectory().getAbsolutePath();
        String dataDir = Environment.getDataDirectory().getAbsolutePath();
        String cacheDir = Environment.getDownloadCacheDirectory().getAbsolutePath();

        tvResult.setText(String.format("SD_Path = %s \n" + "root = %s \n" + "data = %s \n" + "cache = %s \n"
                , mSdPath, rootDir, dataDir, cacheDir));

    }

    public void writeFile() {

        String testContents = "This is 'File Writing' in SD Card";

        try {
            // make Dir in external storage
            File newDir = new File(mSdPath + "/dir");
            newDir.mkdir();

            Log.e("Dir_Check", ">>>>>>>>>" + newDir.getAbsolutePath());


            // TODO - need to make External Storage Runtime Permission Dialog
            // make File in new Directory
            File newFile = new File(newDir.getAbsolutePath()+"/"+FILENAME);
            if(!newFile.exists())
                newFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(newFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            bos.write(testContents.getBytes());

            bos.close();
            fos.close();

            Toast.makeText(MainActivity.this, "File Writing is Success....", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "File Writing is Failed....", Toast.LENGTH_SHORT).show();
        }
    }

    public void readFile() {

        File readFile = new File(mSdPath + "/dir/testFile.txt");

        try {

            FileInputStream fis = new FileInputStream(readFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] buf = new byte[bis.available()];
            while (bis.read(buf) != -1) ;

            bis.close();
            fis.close();

            tvResult.setText(new String(buf));

            Toast.makeText(MainActivity.this, "File Reading is Success....", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "File Reading is Failed....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_test:

                findExternalPath();

                break;

            case R.id.button_save:

                writeFile();

                break;

            case R.id.button_load:

                readFile();

                break;

        }
    }
}
