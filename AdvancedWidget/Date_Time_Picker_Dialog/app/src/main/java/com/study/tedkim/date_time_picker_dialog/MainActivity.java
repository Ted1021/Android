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

    // 1. Date & Time Dialog 생성하기
    // 이전 예제의 방식과는 달리 레이아웃에 따로 해당 위젯을 생성 해 줄 필요가 없다.
    // java 코드 내에서 다이얼로그를 생성해 사용한다
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_date:
                // 1.1 DatePickerDialog() 생성
                new DatePickerDialog(MainActivity.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;

            case R.id.button_time:
                // 1.2 TimePickerDialog() 생성
                new TimePickerDialog(MainActivity.this, mTimeSetListener, mHour, mMinute, false).show();
                break;
        }
    }

    // 2. 각각의 다이얼로그가 사라진 직후의 작업들을 정의 한다.
    // mDataSetListener, mTimeSetListener 에서 해당 작업을 수행한다.
    public void setDialogs() {

        // 2.1 mDataSetListener.OnDateSetListner() 를 정의 한다.
        // 여기서 전달받는 각각의 파라미터를 이용해 원하는 작업을 진행 할 수 있다.
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

        // 2.2 mTimeSetListener.OnTimeSetListener() 를 정의 한다.
        // 위와 동일하다.
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

}
