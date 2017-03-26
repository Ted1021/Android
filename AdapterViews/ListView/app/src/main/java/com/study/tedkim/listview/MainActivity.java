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

    // ListView 를 구현하고 다양한 옵션들을 적용하기 위한 메소드
    public void setListView(){

        // 1. ListView 를 선언한다
        listView = (ListView) findViewById(R.id.listView_list);
        // 2. ListView 와 미리 준비 된 DataSet 을 연결 해 줄 Adapter 선언하고 초기화 한다
        mAdapter = new ArrayAdapter<>(this,     // 현재 Context 정보
                android.R.layout.simple_list_item_checked, // 리스트 뷰를 표현 할 Layout 형식 (item_checked)
                listItem.cntName                // 리스트 뷰에 적용할 데이터 셋
        );
        // 3. 초기화 한 Adapter 를 ListView 에 부착
        listView.setAdapter(mAdapter);
        // 4. 현재 ListView 에 적용시킬 Layout Option 지정 - 이는 Xml 상으로도 가능하다
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        // 5. ListView 에서 발생하는 Item Click 이벤트를 처리하기위한 Listener 부착
        listView.setOnItemClickListener(this);

    }

    // ListItem Click 이벤트 정의
    @Override
    public void onClick(View v) {
        switch(v.getId()){

            // 1. DataSet 을 추가 할 때,
            case R.id.button_Add:
                // 1.1 EditText 에 입력된 문자열을 저장
                String str = etChoose.getText().toString();

                // 1.2 입력할 문자열의 공백 여부를 체크 한 뒤,
                if(str.equals(""))
                    // 1.2.1 공백이라면 아예 DataSet 에 입력하지 않음
                    Toast.makeText(this, "please Insert any Word that you wanna add ;)", Toast.LENGTH_SHORT).show();
                else
                    // 1.2.2 입력 받은 문자열을 DataSet 에 추가
                    listItem.cntName.add(str);
                break;

            // 2. DataSet 의 일부 Component 를 삭제 할 때,
            case R.id.button_Remove:
                // 2.1 준비해 둔 Item Check List 를 순차적으로 확인한다
                for(int i=0; i<listItem.cntName.size(); i++){
                    // 2.1.1 체크가 되어 있다면,
                    if(listView.getCheckedItemPositions().get(i))
                        // 2.1.2 해당 아이템의 position 을 DataSet 에서 삭제 한다
                        listItem.cntName.remove(i);
                }
                break;
        }
        // TODO - notifyDataSetChanged() 의 위치가 매우 중요!!
        // 주로 DataSet 의 변화가 발생하는 이벤트 직후 이 메소드를 호출하도록 한다
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, listItem.cntName.get(position)+" is clicked!", Toast.LENGTH_SHORT).show();
    }
}
