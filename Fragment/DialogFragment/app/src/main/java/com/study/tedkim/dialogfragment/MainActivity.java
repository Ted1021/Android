package com.study.tedkim.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogFragment.OnDataSetListener {

    Button btnDialog;

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment();
            }
        });
    }

    public void initView(){

        btnDialog = (Button) findViewById(R.id.button_dialog);
        btnDialog.setOnClickListener(this);

        tvData = (TextView) findViewById(R.id.textView_name);
    }

    public void setFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if(prev != null){
            fragmentTransaction.remove(prev);
        }

        DialogFragment dialog = DialogFragment.newInstance();
        dialog.show(fragmentTransaction, "dialog");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_dialog:
                setFragment();
                break;

        }
    }

    @Override
    public void onDataSave(Bundle bundle) {

        String name = bundle.getString("name");
        String gender = bundle.getString("gender");

        tvData.setText(name + " : " + gender);
    }

}
