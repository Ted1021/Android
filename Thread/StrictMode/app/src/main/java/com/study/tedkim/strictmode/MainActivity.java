package com.study.tedkim.strictmode;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    static final boolean DEBUG_MODE = true;

    Button btnFileIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (DEBUG_MODE) {

            StrictMode.ThreadPolicy mThreadPolicy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyDialog()
                    .build();
            StrictMode.setThreadPolicy(mThreadPolicy);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFileIO = (Button) findViewById(R.id.button_file_IO);
        btnFileIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomThread mThread = new CustomThread();
                mThread.setDaemon(true);
                mThread.start();

            }
        });

    }

    public class CustomThread extends Thread {

        @Override
        public void run() {
            super.run();

            try {

                FileOutputStream fos = openFileOutput("text.txt", Context.MODE_PRIVATE);
                String str = "Android File IO Test";
                fos.write(str.getBytes());
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
