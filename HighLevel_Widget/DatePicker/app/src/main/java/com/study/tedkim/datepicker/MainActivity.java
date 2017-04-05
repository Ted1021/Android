package com.study.tedkim.datepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatePicker datePicker;
    Button btnSave;

    String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setDatePicker();

    }

    public void initView(){

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        btnSave = (Button) findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);

    }

    // DatePicker 의 기능을 정의한다
    public void setDatePicker(){

        // 1. DatePicker 의 init 이 필요하다
        // 각 파라미터의 이름 그대로 각각 현재 선택된 지점의 년, 월, 일을 받아온다
        datePicker.init(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        // 2. 날짜 선택에 변경이 생겼을 때 이를 감지하는 리스너를 달아준다
                        new DatePicker.OnDateChangedListener(){

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // 2.1 바뀐 날짜의 정보를 저장 해 둔다
                        mDate = year+"" + " / " + monthOfYear+1+"" + " / " + dayOfMonth+"";
                    }
                }
        );

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_save:
                Toast.makeText(getApplicationContext(), mDate, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
