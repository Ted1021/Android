package com.study.tedkim.sqlite_helper;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements FragmentImpl, View.OnClickListener {

    EditText etWord;
    Button btnInsert, btnDelete, btnUpdate, btnSelect, btnSelectAll;

    WordHelper mHelper;

    public static ArrayList<WordItem> mDataSet;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initView(view);
        mHelper = new WordHelper(getContext());

        return view;
    }

    public void initView(View view){

        etWord = (EditText) view.findViewById(R.id.editText_word);

        btnInsert = (Button) view.findViewById(R.id.button_insert);
        btnInsert.setOnClickListener(this);

        btnDelete = (Button) view.findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(this);

        btnUpdate = (Button) view.findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(this);

        btnSelect = (Button) view.findViewById(R.id.button_select);
        btnSelect.setOnClickListener(this);

        btnSelectAll = (Button) view.findViewById(R.id.button_selectAll);
        btnSelectAll.setOnClickListener(this);

    }

    @Override
    public void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {

        // TODO - 여기애서 SQLite DB 객체 생성
        // SQLiteDatabase 객체 - 이후에 DBHelper 에서 제공하는 두가지 메소드
        // getReadableDatabase(), getWritableDatabase() 로 초기화 시켜준 뒤 작업
        SQLiteDatabase db;
        // SQL 문을 사용하지 않을때 이용하는 방법 ... 알아는두자..
        ContentValues tuple;

        switch(v.getId()){

            case R.id.button_insert:

                db = mHelper.getWritableDatabase();

                // TODO - 데이터 초기화 클래스를 별도로 만들어 볼 것
                db.execSQL("INSERT INTO myDictionary VALUES (null, 'desk', '책상');");
                db.execSQL("INSERT INTO myDictionary VALUES (null, 'ted', '태원');");
                db.execSQL("INSERT INTO myDictionary VALUES (null, 'alice', '윤지');");

                mHelper.close();

                Toast.makeText(getActivity(), "Data is successfully inserted !!", Toast.LENGTH_SHORT).show();

                break;

            case R.id.button_delete:

                // TODO - 특정 단어만 선택적으로 삭제 할 수 있는 로직 구현 필요
                String deleteWord = etWord.getText().toString();

                db = mHelper.getWritableDatabase();
                db.execSQL("DELETE FROM myDictionary;");
//                db.execSQL("DELETE FROM myDictionary dir WHERE (dir.eng == '"+deleteWord+"'|| dir.kor == '"+deleteWord+"');");
                mHelper.close();

                Toast.makeText(getActivity(), "Data is Successfully deleted !! ", Toast.LENGTH_SHORT).show();

                break;

            case R.id.button_update:

                String updateWord = etWord.getText().toString();

                db = mHelper.getWritableDatabase();
                db.execSQL("UPDATE myDictionary SET eng='"+updateWord+"' WHERE kor='태원';");
                mHelper.close();

                Toast.makeText(getActivity(), "Data is Successfully updated !! ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_select:

                mDataSet = new ArrayList<>();
                String selectWord = etWord.getText().toString();

                db = mHelper.getReadableDatabase();
                // what is selection args??
                Cursor cursor = db.rawQuery("SELECT * FROM myDictionary dir WHERE dir.eng='"+selectWord+"';",null);
                selectData(cursor);

                cursor.close();
                mHelper.close();

                SearchFragment selectFragment = new SearchFragment();
                setFragment(selectFragment);

                break;

            case R.id.button_selectAll:

                mDataSet = new ArrayList<>();

                db = mHelper.getReadableDatabase();
                Cursor cursorAll = db.rawQuery("SELECT * FROM myDictionary dir", null);
                selectData(cursorAll);

                cursorAll.close();
                mHelper.close();

                SearchFragment selectAllFragment = new SearchFragment();
                setFragment(selectAllFragment);

                break;
        }
    }

    public void selectData(Cursor cursor){

        int pIndex = cursor.getColumnIndex("word_id");
        int pEng = cursor.getColumnIndex("eng");
        int pKor = cursor.getColumnIndex("kor");

        while(cursor.moveToNext()){

            WordItem item = new WordItem();
            item.index = cursor.getInt(pIndex);
            item.eng = cursor.getString(pEng);
            item.kor = cursor.getString(pKor);

            mDataSet.add(item);
        }
    }
}
