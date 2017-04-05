package com.study.tedkim.date_time_picker_dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDate, btnTime;
    int mYear = 0, mMonth = 0, mDay = 0;
    int mHour = 0, mMinute = 0;

    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener mTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setDialogs();

    }

    public void initView() {

        btnDate = (Button) findViewById(R.id.button_date);
        btnDate.setOnClickListener(this);

        btnTime = (Button) findViewById(R.id.button_time);
        btnTime.setOnClickListener(this);

    }

    public void setDialogs() {

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                mYear = year;
                mMonth = month+1;
                mDay = dayOfMonth;

                String temp = mYear+"" + " / " + mMonth+"" + " / " + mDay+"";

                Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
            }
        };

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                mHour = hourOfDay;
                mMinute = minute;

                String temp = mHour+"" + " : " + mMinute+"";
                Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_date:

                new DatePickerDialog(MainActivity.this, mDateSetListener, mYear, mMonth, mDay).show();

                break;

            case R.id.button_time:

                new TimePickerDialog(MainActivity.this, mTimeSetListener, mHour, mMinute, false).show();

                break;
        }
    }

}
