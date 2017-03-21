package com.study.tedkim.handset_table;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListItemClickListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    int mPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment();

    }

    public void setFragment(){

        ListFragment listFragment = ListFragment.newInstance();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, listFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void setFragment(int position){

        DetailFragment detailFragment = DetailFragment.newInstance(position);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onListItemClicked(int position) {
        // 초기상태의 Detail Fragment 상태를 정의 하기 위함.
        mPosition = position;
        setFragment(mPosition);
    }
}
