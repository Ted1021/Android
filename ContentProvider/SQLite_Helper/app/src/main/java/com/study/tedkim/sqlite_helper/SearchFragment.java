package com.study.tedkim.sqlite_helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<WordItem> mDataSet = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mDataSet = MainFragment.mDataSet;
        setRecyclerView(view);

        return view;
    }

    public void setRecyclerView(View view){

        RecyclerView rvList = (RecyclerView) view.findViewById(R.id.recyclerView_list);
        DictionaryAdapter adapter = new DictionaryAdapter(getContext(), R.layout.item_layout, mDataSet);
        rvList.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);
    }

}
