package com.study.tedkim.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mGroupList = new ArrayList<>();
    ArrayList<ArrayList<String>> mChildList = new ArrayList<>();

    ExpandableListView elvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        setListView();

    }

    public void initData(){

        for(int i=0; i<PlanetInfo.PLANET.size(); i++){

            GroupItem groupItem = new GroupItem();
            ChildItem childItem = new ChildItem();
            childItem.desc = new ArrayList<>();

            groupItem.name = PlanetInfo.PLANET.get(i);
            childItem.desc.add(PlanetInfo.DESC.get(i));

            mGroupList.add(PlanetInfo.PLANET.get(i));
            mChildList.add(childItem.desc);
        }

    }

    public void setListView(){

        elvList = (ExpandableListView) findViewById(R.id.listView_expandableList);
        ExpandableListAdapter adapter = new ExpandableListAdapter(this, mGroupList, mChildList);

        elvList.setAdapter(adapter);

    }
}
