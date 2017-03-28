package com.study.tedkim.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvList;
    ArrayList<ListItem> mDataSet = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        setRecyclerView();

    }

    public void initData(){
        int size = PlanetInfo.PLANET.size();
        for(int i=0; i<size; i++){
            String planetName = PlanetInfo.PLANET.get(i);
            String planetDesc = PlanetInfo.DESC.get(i);

            ListItem item = new ListItem(planetName, planetDesc);
            mDataSet.add(item);
        }

    }

    // RecyclerView 를 선언하고, RecyclerViewAdapter 와 LayoutManager 를 정의해 RecyclerView 에 부착한다.
    public void setRecyclerView(){

        // 1. RecyclerView 선언
        rvList = (RecyclerView) findViewById(R.id.recyclerView_list);

        // 2. RecyclerViewAdapter 생성
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, R.layout.item_layout, mDataSet);
        // 3. LinearLayoutManager 로 LayoutManager 초기화
        mLayoutManager = new LinearLayoutManager(this);

        // 4. 생성한 Adapter 를 RecyclerView 에 부착
        rvList.setAdapter(adapter);
        // 5. 생성한 LayoutManager 를 RecyclerView 에 부착
        rvList.setLayoutManager(mLayoutManager);

    }
}
