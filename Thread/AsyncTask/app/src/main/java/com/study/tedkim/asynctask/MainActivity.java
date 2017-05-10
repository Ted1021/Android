package com.study.tedkim.asynctask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvAdder;
    Button btnAdder;

    int mValue;

    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){

        tvAdder = (TextView) findViewById(R.id.textView_adder);

        btnAdder = (Button) findViewById(R.id.button_adder);
        btnAdder.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_adder:

                AccumulateTask mAsyncTask = new AccumulateTask();
                mAsyncTask.execute(100);

                break;

        }

    }

    // 각 제네릭의 의미 잘 파악 할 것
    public class AccumulateTask extends AsyncTask<Integer, Integer, Integer>{

        // Initiate values & UI Components
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mValue = 0;

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.setTitle("Updating ... ");
            mDialog.setMessage("Wait... ");
            mDialog.setCancelable(false);
            mDialog.setProgress(0);
            mDialog.setButton("Cancel", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancel(true);
                }
            });
            mDialog.show();

        }

        // Background Thread
        @Override
        protected Integer doInBackground(Integer... params) {

            while(mValue < 100) {

                mValue++;

                //TODO - onProgressUpdate 를 동작시키기 위해서는 무조건 호출 해야 함
                publishProgress(mValue);

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return mValue;

        }

        // Handler
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mDialog.setProgress(values[0]);
            tvAdder.setText(mValue+"");

        }

        // Result
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            mDialog.dismiss();
            Toast.makeText(MainActivity.this, "Uploading is completed !! ", Toast.LENGTH_SHORT).show();

        }
    }
}
