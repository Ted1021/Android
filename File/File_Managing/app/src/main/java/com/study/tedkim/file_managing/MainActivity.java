package com.study.tedkim.file_managing;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRoot, btnUp;
    TextView tvCurrentPath;

    RecyclerView rvDirList;
    RecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> mDataSet = new ArrayList<>();

    String mRoot;
    public static String mCurrentPath;

    public static Handler mHandler;
    public static int EVENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setRecyclerView();

        initData();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if(msg.what == EVENT){
                    refreshFiles();
                }
            }
        };
    }

    public void initView(){

        tvCurrentPath = (TextView) findViewById(R.id.textView_currentPath);

        btnRoot = (Button) findViewById(R.id.button_root);
        btnRoot.setOnClickListener(this);

        btnUp = (Button) findViewById(R.id.button_up);
        btnUp.setOnClickListener(this);

    }

    public void initData(){

        mRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        mCurrentPath = mRoot;

        refreshFiles();
    }

    public void setRecyclerView(){

        rvDirList = (RecyclerView) findViewById(R.id.recyclerView_cardDir);

        mAdapter = new RecyclerViewAdapter(this, R.layout.list_layout, mDataSet);
        rvDirList.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        rvDirList.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_root:

                mCurrentPath = mRoot;
                refreshFiles();

                break;

            case R.id.button_up:

                if(!mCurrentPath.equals(mRoot)){

                    int end = mCurrentPath.lastIndexOf("/");
                    mCurrentPath = mCurrentPath.substring(0, end);
                    refreshFiles();
                }
                else
                    Toast.makeText(MainActivity.this, "This Directory is ROOT", Toast.LENGTH_SHORT).show();

                break;

        }
    }

    public void refreshFiles(){

        tvCurrentPath.setText(mCurrentPath);
        mDataSet.clear();

        // 여기가 핵심!!
        File currentState = new File(mCurrentPath);
        // Linux 로 치면 'ls-l' 과 같은 효과
        String[] files = currentState.list();

        if(files != null){
            for(int i=0; i<files.length; i++){

                String path = mCurrentPath + "/" + files[i];
                String name;

                File file = new File(path);
                if(file.isDirectory()){
                    name = "[" + files[i] + "]";
                }
                else
                    name = files[i];

                mDataSet.add(name);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
