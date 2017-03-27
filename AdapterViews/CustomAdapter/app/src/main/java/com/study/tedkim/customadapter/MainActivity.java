package com.study.tedkim.customadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    ListView lvPlanetList;
    ArrayList<PlanetItem> mDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // static class 인 "PlanetInfo" 로 부터 DataSet 을 가져옵니다.
        initDataSet();
        // 준비 된 DataSet 과 CustomAdapter 를 이용해 ListView 를 세팅합니다.
        setListView();
    }

    // DataSet 만들기
    public void initDataSet(){

        // 1. PlanetInfo 에 저장되어 있는 데이터의 개수를 가져옵니다.
        int size = PlanetInfo.PLANET.size();
        // 2. 저장 된 정보를 PlanetItem 이라는 객체에 받아오고 이를 ArrayList 인 "mDataSet" 에 집어 넣습니다.
        for(int i=0; i<size; i++){
            // 2.1 PlanetItem 객체 하나를 생성해 PlanetInfo 에 있는 데이터로 초기화 시킵니다.
            PlanetItem newItem = new PlanetItem();
            newItem.name = PlanetInfo.PLANET.get(i);
            newItem.desc = PlanetInfo.DESC.get(i);

            // 2.2 새로 생성한 PlanetItem 을 mDateSet 에 삽입합니다.
            mDataSet.add(newItem);
        }
    }

    // ListView 세팅하기
    public void setListView(){

        // 1. ListView 의 레이아웃을 가져옵니다.
        lvPlanetList = (ListView) findViewById(R.id.listView_PlanetList);
        // 2. 해당 Activity의 정보(Context), 단일 아이템의 Layout, 리스트뷰에 적용 될 DataSet 을 이용해 Custom Adapter 를 제작합니다.
        CustomAdapter adapter = new CustomAdapter(this, R.layout.planet_item, mDataSet);
        // 3. 초기화 된 Adapter 를 ListView 에 부착합니다.
        lvPlanetList.setAdapter(adapter);
        // 4. ListView 의 ItemClickListener 를 상속받아 구현합니다.
        lvPlanetList.setOnItemClickListener(this);

    }

    // ListView 에 ItemClickListener 구현
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // 1. ListView 의 특정 Item 이 클릭되면 새로운 액티비티에서 상세 정보를 띄우도록 합니다.
        Intent intent = new Intent(this, DescActivity.class);
        // 2. 클릭 된 아이템의 position 정보를 전달합니다.
        intent.putExtra("position", position);
        // 3. 세팅 한 intent 를 기반으로 새로운 액티비티를 불러옵니다.
        startActivity(intent);
    }
}
