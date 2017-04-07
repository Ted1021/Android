package com.study.tedkim.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewAdapter.OnRecyclerViewItemClickListener {

    RecyclerView rvArticleList;
    Button btnEdit, btnDel;
    ArrayList<Article> mDataSet = new ArrayList<>();

    RecyclerViewAdapter mAdapter;

    static final int REQ_EDIT = 101;
    static final int REQ_UPDATE = 102;

    int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        setRecyclerView();
    }

    public void initView(){

        rvArticleList = (RecyclerView) findViewById(R.id.recyclerView_articleList);

        btnDel = (Button) findViewById(R.id.button_delete);
        btnDel.setOnClickListener(this);

        btnEdit = (Button) findViewById(R.id.button_edit);
        btnEdit.setOnClickListener(this);

    }

    public void initData(){

        int initSize = ArticleData.CONTENTS.size();
        for(int i=0; i<initSize; i++){

            String title = ArticleData.TITLE.get(i);
            String name = ArticleData.NAME.get(i);
            String contents = ArticleData.CONTENTS.get(i);

            Article data = new Article();
            data.setTitle(title);
            data.setName(name);
            data.setContents(contents);

            mDataSet.add(data);
        }
    }

    public void setRecyclerView(){

        mAdapter = new RecyclerViewAdapter(this, R.layout.layout_article_item, mDataSet);
        rvArticleList.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvArticleList.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_edit:

                Intent editIntent = new Intent(this, CreateArticleActivity.class);
                startActivityForResult(editIntent, REQ_EDIT);

                break;

            case R.id.button_delete:

                // TODO - RecyclerView Item 에 checkList 부착해 연동 할 것
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case REQ_EDIT:
                if(resultCode == RESULT_OK){

                    Article article = new Article();

                    String title = data.getStringExtra("TITLE");
                    article.setTitle(title);

                    String name = data.getStringExtra("NAME");
                    article.setName(name);

                    String contents = data.getStringExtra("CONTENTS");
                    article.setContents(contents);

                    // Create Method
                    mDataSet.add(article);
                }
                break;

            case REQ_UPDATE:
                if(resultCode == RESULT_OK){

                    // Update Method 라기보다는 Override 하는 느낌이 강함
                    int itemPosition = data.getIntExtra("POSITION",0);
                    Log.e("check", ">>>>>>>>>>>>"+itemPosition+"");

                    String title = data.getStringExtra("TITLE");
                    mDataSet.get(itemPosition).setTitle(title);

                    String name = data.getStringExtra("NAME");
                    mDataSet.get(itemPosition).setName(name);

                    String contents = data.getStringExtra("CONTENTS");
                    mDataSet.get(itemPosition).setContents(contents);

                }

                break;
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(int position) {

        mPosition = position;

        Intent updateIntent = new Intent(MainActivity.this, EditArticleActivity.class);

        updateIntent.putExtra("POSITION", mPosition);
        startActivityForResult(updateIntent, REQ_UPDATE);

    }
}
