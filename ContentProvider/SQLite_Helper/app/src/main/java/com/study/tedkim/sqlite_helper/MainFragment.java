package com.study.tedkim.sqlite_helper;


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
public class MainFragment extends Fragment implements FragmentImpl, View.OnClickListener, SqlCommandImpl {

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

    public void initView(View view) {

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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_insert:

                sqlInsert();

                break;

            case R.id.button_delete:

                sqlDelete();

                break;

            case R.id.button_update:

                String targetWord = etWord.getText().toString();
                sqlUpdate(targetWord);

                break;

            case R.id.button_select:

                mDataSet = new ArrayList<>();
                String selectWord = etWord.getText().toString();

                sqlSelect(selectWord);

                SearchFragment selectFragment = new SearchFragment();
                setFragment(selectFragment);

                break;

            case R.id.button_selectAll:

                mDataSet = new ArrayList<>();

                sqlSelect();

                SearchFragment selectAllFragment = new SearchFragment();
                setFragment(selectAllFragment);

                break;
        }
    }

    @Override
    public void sqlInsert() {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("INSERT INTO mDictionary VALUES (null, 'desk', '책상')");
        db.execSQL("INSERT INTO mDictionary VALUES (null, 'laptop' '노트북'");
        db.execSQL("INSERT INTO mDictionary VALUES (null, 'smart phone' '스마트폰')");

        Toast.makeText(getContext(), "Data is Successfully inserted !! ", Toast.LENGTH_SHORT).show();

        mHelper.close();

    }

    @Override
    public void sqlDelete() {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("DELETE FROM mDictionary");

        Toast.makeText(getContext(), "Data is successfully deleted !!", Toast.LENGTH_SHORT).show();

        mHelper.close();

    }

    @Override
    public void sqlDelete(String targetWord) {

        // TODO - targetWord 삭제 구현
    }

    @Override
    public void sqlUpdate(String targetWord) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("UPDATE mDictionary SET eng='" + targetWord + "' WHERE kor='노트북'");

        Toast.makeText(getContext(), "Data is successfully updated !!", Toast.LENGTH_SHORT).show();

        mHelper.close();

    }

    @Override
    public void sqlSelect() {

        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mDictionary", null);

        // 우선 속성의 위치 값(Column Index) 부터 받아온다.
        int iIndex = cursor.getColumnIndex("word_id");
        int iEng = cursor.getColumnIndex("eng");
        int iKor = cursor.getColumnIndex("kor");

        while (cursor.moveToNext()) {

            WordItem item = new WordItem();

            // column index 를 이용해 각각의 data 를 가져온다
            int dIndex = cursor.getInt(iIndex);
            String dEng = cursor.getString(iEng);
            String dKor = cursor.getString(iKor);

            item.index = dIndex;
            item.eng = dEng;
            item.kor = dKor;

            mDataSet.add(item);
        }

        cursor.close();
        mHelper.close();
    }

    @Override
    public void sqlSelect(String targetWord) {

        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mDictionary WHERE eng = '" + targetWord + "'", null);

        // 우선 속성의 위치 값(Column Index) 부터 받아온다.
        int iIndex = cursor.getColumnIndex("word_id");
        int iEng = cursor.getColumnIndex("eng");
        int iKor = cursor.getColumnIndex("kor");

        while (cursor.moveToNext()) {

            WordItem item = new WordItem();

            // column index 를 이용해 각각의 data 를 가져온다
            int dIndex = cursor.getInt(iIndex);
            String dEng = cursor.getString(iEng);
            String dKor = cursor.getString(iKor);

            item.index = dIndex;
            item.eng = dEng;
            item.kor = dKor;

            mDataSet.add(item);

        }

        cursor.close();
        mHelper.close();
    }

    @Override
    public void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
