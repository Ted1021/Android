package com.study.tedkim.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://openAPI.seoul.go.kr:8088/";
    public static final String AUTH_KEY = "59414779596b696d37364f594a596a";
    public static final String GET_COMMAND = "/xml/SearchParkInfoService/1/5/";

    RecyclerView rvParkList;
    ParkAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<ParkData.Row> mDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        initData();
    }

    // call retrofit2 here
    public void initData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchParkInfoService service = retrofit.create(SearchParkInfoService.class);
        Call<ParkData> getParkData = service.getParkData(AUTH_KEY);
        getParkData.enqueue(new Callback<ParkData>() {
            @Override
            public void onResponse(Call<ParkData> call, Response<ParkData> response) {

                if(response.isSuccessful()){

                    for(ParkData.Row data : response.body().getSearchParkInfoService().getRow()) {

                        mDataSet.add(data);
                        Log.e("CHECK_DATA", ">>>>>>>>>>>>>>>>"+data.getP_PARK());
                        mAdapter.notifyDataSetChanged();

                    }
                }

                Toast.makeText(MainActivity.this, "Download Complete....", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ParkData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failed.... ", Toast.LENGTH_SHORT).show();
            }
        });



    }

    // set park info here
    public void setRecyclerView(){

        rvParkList = (RecyclerView) findViewById(R.id.recyclerView_parkInfo);

        mAdapter = new ParkAdapter(this, R.layout.park_info_item, mDataSet);
        rvParkList.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        rvParkList.setLayoutManager(mLayoutManager);

    }
}
