package com.study.tedkim.seekbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sbPercent;
    TextView tvPercent;

    int mPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){

        tvPercent = (TextView) findViewById(R.id.textView_percent);

        sbPercent = (SeekBar) findViewById(R.id.seekBar_percent);

        // setOnSeekBarChangeListener() 구현
        sbPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPercent = progress;
                tvPercent.setText(mPercent+""+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


}
