package com.study.tedkim.listfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordListFragment extends Fragment{

    ArrayList<String> WORDS;
    ArrayList<String> DESC;

    ListView listView;
    ArrayAdapter<String> adapter;

    OnWordListItemClickListener mEventListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mEventListener = (OnWordListItemClickListener) context;
        }catch(Exception e){
            Log.e("Interface Error","OnWordListItemClickListener is not implement on activity yet");
        }
    }

    public WordListFragment() {
        // Required empty public constructor
    }

    public interface OnWordListItemClickListener{
        void onItemClicked(int position);
    }

    public static WordListFragment newInstance(ArrayList<String> WORDS, ArrayList<String> DESC){

        WordListFragment fragment = new WordListFragment();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("word", WORDS);
        bundle.putStringArrayList("desc", DESC);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        initView(view);

        initData();

        return view;

    }

    public void initView(View view){

        listView = (ListView) view.findViewById(R.id.listView);
    }

    public void initData(){

        Bundle bundle = getArguments();
        this.WORDS = bundle.getStringArrayList("word");
        this.DESC = bundle.getStringArrayList("desc");

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, WORDS);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mEventListener.onItemClicked(position);
            }
        });


    }

}
