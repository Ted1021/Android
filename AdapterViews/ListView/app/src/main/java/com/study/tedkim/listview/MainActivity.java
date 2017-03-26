package com.study.tedkim.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListView.OnItemClickListener{

    ListView listView;
    EditText etChoose;
    Button btnAdd, btnDel;
    ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListView();

    }

    public void initView(){

        listView = (ListView) findViewById(R.id.listView_list);

        etChoose = (EditText) findViewById(R.id.editText_Select);

        btnAdd = (Button) findViewById(R.id.button_Add);
        btnAdd.setOnClickListener(this);
        btnDel = (Button) findViewById(R.id.button_Remove);
        btnDel.setOnClickListener(this);

    }

    public void setListView(){

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, listItem.cntName);
        listView.setAdapter(mAdapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_Add:

                String str = etChoose.getText().toString();

                if(str.equals(""))
                    Toast.makeText(this, "please Insert any Word that you wanna add ;)", Toast.LENGTH_SHORT).show();
                else
                    listItem.cntName.add(str);
                break;

            case R.id.button_Remove:
                for(int i=0; i<listItem.cntName.size(); i++){
                    if(listView.getCheckedItemPositions().get(i))
                        listItem.cntName.remove(i);
                }
                break;
        }
        // TODO - notifyDataSetChanged() 의 위치가 매우 중요!!
        // mAdapter 를 전역변수화 해야하는 문제가 발생
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, listItem.cntName.get(position)+" is clicked!", Toast.LENGTH_SHORT).show();
    }
}
