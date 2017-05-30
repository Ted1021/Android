package com.study.tedkim.preferencefragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvGender, tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = pref.getString("userName", " - ");
        Boolean gender = pref.getBoolean("userGender", false);

        tvUserName = (TextView) findViewById(R.id.textView_userName);
        tvUserName.setText(userName);

        tvGender = (TextView) findViewById(R.id.textView_gender);
        if(gender)
            tvGender.setText("Mr. ");
        else
            tvGender.setText("Miss. ");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.item_setting:

                Intent intent = new Intent(this, SettingActivity.class);
                startActivityForResult(intent, 0);

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==0){

//            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences pref = getSharedPreferences("setting", MODE_PRIVATE);
            String userName = pref.getString("userName", " - ");
            Boolean gender = pref.getBoolean("userGender", false);

            tvUserName.setText(userName);

            tvGender = (TextView) findViewById(R.id.textView_gender);
            if(gender)
                tvGender.setText("Mr. ");
            else
                tvGender.setText("Miss. ");
        }
    }
}
