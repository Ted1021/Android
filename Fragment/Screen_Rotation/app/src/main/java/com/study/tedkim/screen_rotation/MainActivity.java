package com.study.tedkim.screen_rotation;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListItemClickListener {

    Configuration config;

    int mPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면이 회전되게 되면 액티비티가 매번 새로이 onCreate 가 되는데
        // 이때 이전 액티비티의 현재 상태를 보존하고 싶다면 savedInstanceState 를 활용하면 된다.
        // onSavedInstanceState(Bundle outState) 를 오버라이드하게 되면
        // 현재 액티비티 (또는 프래그먼트)의 상태를 저장 할 수 있다.

        // 1. 이전 상태에 대한 저장 정보가 존재 한다면,
        if(savedInstanceState != null){
            // 1.1 savedInstance(=Bundle) 에 저장 된 내용으로 액티비티 (또는 프래그먼트) 를 초기화 한다.
            mPosition = savedInstanceState.getInt("position");
        }

        // 현재 액티비티의 config 를 획득하는 과정
        // 1. 시스템 리소스에 접근한다.
        Resources resource = Resources.getSystem();
        // 2. 리소스로부터 현재의 configuration 정보를 가져온다.
        config = resource.getConfiguration();
        // 3. onConfigurationChanged 로 현재 상태를 보내준다.
        onConfigurationChanged(config);

    }

    // 화면 회전 처리에 있어서 가장 중요한 함수
    // newConfig = 현재 화면의 상태 (회전, 사이즈의 변화, 키보드 출현 유무 등의 상태를 나타낸다)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 1. 전달받은 configuration 에서 orientation 정보를 가져온다.
        switch(newConfig.orientation){

            // 1.1 LANDSCAPE (가로 상태) 일 때,
            case Configuration.ORIENTATION_LANDSCAPE:
                // 1.1.1 가로 상태에 알맞는 레이아웃을 미리 설정한다.
                setContentView(R.layout.activity_main_landscape);

                // 1.1.2 가로 레이아웃에 맞는 Fragment 들의 초기상태를 설정한다.
                setFragment_landscape();

                break;

            // 1.2 PORTRAIT (세로 상태) 일 때,
            case Configuration.ORIENTATION_PORTRAIT:
                // 1.2.1 세로 상태에 알맞는 레이아웃을 미리 설정한다.
                setContentView(R.layout.activity_main);

                // 1.2.2 세로 레이아웃에 맞는 Fragment 들의 초기상태를 설정한다.
                setFragment_portrait();

                break;
        }
    }

    // onCreate 가 초기화 될 때마다 이전 액티비티의 상태를 보존하기 위한 메소드
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 1. 현재 프로젝트에서는 ListFragment 의 position 값이 보존 되어야 하므로
        // Bundle 에 position 에 관한 정보를 저장 한다.
        outState.putInt("position", mPosition);
    }

    public void setFragment_portrait(){

        ListFragment listFragment = ListFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, listFragment);
        fragmentTransaction.commit();
    }

    public void setFragment_landscape(){

        ListFragment listFragment = ListFragment.newInstance();
        DetailFragment detailFragment = DetailFragment.newInstance(mPosition);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.listFragment_container, listFragment);
        fragmentTransaction.add(R.id.detailFragment_container, detailFragment);

        fragmentTransaction.commit();
    }

    public void callDetailFragment(int position){

        DetailFragment detailFragment = DetailFragment.newInstance(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentTransaction.replace(R.id.fragment_container, detailFragment);
            fragmentTransaction.addToBackStack(null);
        }
        else
            fragmentTransaction.replace(R.id.detailFragment_container, detailFragment);

        fragmentTransaction.commit();

    }

    @Override
    public void onItemClicked(int position) {

        mPosition = position;

        callDetailFragment(mPosition);
    }
}
