package com.study.tedkim.fragment_parameter_sending;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etCounter;
    Button btnSetCounter;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    int mCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        etCounter = (EditText) findViewById(R.id.editText_counter);
        btnSetCounter = (Button) findViewById(R.id.button_setCounter);
        btnSetCounter.setOnClickListener(this);

        // Fragment 초기화
        setFragment(0);

    }

    public void setFragment(int count){

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        MainFragment mainFragment = MainFragment.newInstance(count);
        mFragmentTransaction.replace(R.id.layout_fragment_container, mainFragment);

        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_setCounter:

                mCounter = Integer.parseInt(etCounter.getText().toString());
                setFragment(mCounter);

                break;

        }
    }
}
