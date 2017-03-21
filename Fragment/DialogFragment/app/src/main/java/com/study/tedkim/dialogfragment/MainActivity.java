package com.study.tedkim.dialogfragment;

import android.content.Intent;
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
    Button btnGoActivity;

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

        btnGoActivity = (Button) findViewById(R.id.button_go_activity);
        btnGoActivity.setOnClickListener(this);

        tvData = (TextView) findViewById(R.id.textView_name);
    }

    // 대화상자를 이용해 Fragment 를 불러 올 경우에는
    // Activity 내에 Fragment 를 위한 container 가 따로 존재하지 않으므로
    // Fragment Manager 에서 tag 를 이용한 별도의 관리가 필요하다.
    public void setFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 1. 이미 "dialog" 라는 fragment 가 있을 때에는 이를 hidden 상태로 변경한다.
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if(prev != null){
            fragmentTransaction.remove(prev);
        }

        // 2. 앞서서 dialog fragment 가 없으면 새로운 "dialog" fragment 를 생성한다.
        DialogFragment dialog = DialogFragment.newInstance();

        // 3. DialogFragment 를 상속받은 Fragment 는 show() 메소드를 사용할 수 있으며, show 함수의 내부 파라미터에 따라 동작이 달라진다.
        // transaction 을 사용한 경우 대화상자 fragment 를 백스택에 저장하게 된다.
        dialog.show(fragmentTransaction, "dialog");
        //dialog.show(fragmentManager, "dialog");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_dialog:
                setFragment();
                break;

            case R.id.button_go_activity:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);

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
