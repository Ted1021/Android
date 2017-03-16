package com.study.tedkim.backstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = (Button) findViewById(R.id.button_action);
        btnChange.setOnClickListener(this);

        FirstFragment firstFragment = new FirstFragment();
        setFragment(firstFragment);

    }

    public void setFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);

        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_action:

                SecondFragment secondFragment = new SecondFragment();
                setFragment(secondFragment);

                break;

        }
    }
}
