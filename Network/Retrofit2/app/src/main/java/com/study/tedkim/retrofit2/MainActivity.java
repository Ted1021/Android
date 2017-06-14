package com.study.tedkim.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://openAPI.seoul.go.kr:8088/";
    public static final String AUTH_KEY = "59414779596b696d37364f594a596a";
    public static final String GET_COMMAND = "/xml/SearchParkInfoService/1/5/";

    RecyclerView rvParkList;
    ParkAdapter mAdapter;
    RecyclerView.LayoutManager mManager;

    ArrayList<ParkData> mDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // call retrofit2 here
    public void initData(){

    }

    // set park info here
    public void setRecyclerView(){



    }
}
