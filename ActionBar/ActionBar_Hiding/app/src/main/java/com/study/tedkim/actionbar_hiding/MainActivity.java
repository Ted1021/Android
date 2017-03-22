package com.study.tedkim.actionbar_hiding;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ListView lvList;
    ArrayList<Integer> mDataSet = new ArrayList<>();
    int mLastFirstVisibleItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList = (ListView) findViewById(R.id.listView_list);

        initData();

        lvList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                final ActionBar mActionBar = getSupportActionBar();

                int currentFirstVisibleItem = lvList.getFirstVisiblePosition();
                if(currentFirstVisibleItem > mLastFirstVisibleItem){
                    if(mActionBar.isShowing()){
                        mActionBar.hide();
                    }
                }
                if(currentFirstVisibleItem < mLastFirstVisibleItem){
                    if(!mActionBar.isShowing()){
                        mActionBar.show();
                    }
                }
                mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_hiding, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // None !!

        return true;
    }

    public void initData(){

        int sum=0;

        for(int i=0; i<100; i++){
            mDataSet.add(i,sum);
            sum+=i;
        }

        ArrayAdapter<Integer> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDataSet);
        lvList.setAdapter(mAdapter);

    }

}
