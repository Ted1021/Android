package com.study.tedkim.sqlite_helper;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        mHelper = new WordHelper(getContext());
        initView(view);

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

        SQLiteDatabase db=null;

        switch (v.getId()) {

            case R.id.button_insert:

                mHelper.sqlInsert(db);
                mHelper.close();

                break;

            case R.id.button_delete:

                mHelper.sqlDelete(db);
                mHelper.close();

                break;

            case R.id.button_update:

                String targetWord = etWord.getText().toString();
                mHelper.sqlUpdate(db, targetWord);

                break;

            case R.id.button_select:

                mDataSet = new ArrayList<>();
                String selectWord = etWord.getText().toString();

                mHelper.sqlSelect(db, selectWord);

                SearchFragment selectFragment = new SearchFragment();
                setFragment(selectFragment);

                mHelper.close();

                break;

            case R.id.button_selectAll:

                mDataSet = new ArrayList<>();

                mHelper.sqlSelect(db);

                SearchFragment selectAllFragment = new SearchFragment();
                setFragment(selectAllFragment);

                mHelper.close();

                break;
        }
    }

    @Override
    public void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
