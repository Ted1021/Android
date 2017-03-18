package com.study.tedkim.listfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordDetailFragment extends Fragment {

    ArrayList<String> WORDS;
    ArrayList<String> DESC;

    TextView tvWords;
    TextView tvDesc;

    String mWords, mDesc;

    public WordDetailFragment() {
        // Required empty public constructor
    }

    public static WordDetailFragment newInstance(String WORDS, String DESC){

        WordDetailFragment fragment = new WordDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putString("words", WORDS);
        bundle.putString("desc", DESC);

        fragment.setArguments(bundle);

        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_detail, container, false);

        initView(view);

        setData();


        return view;

    }

    public void initView(View view){

        tvWords = (TextView) view.findViewById(R.id.textView_word);
        tvDesc = (TextView) view.findViewById(R.id.textView_desc);

    }

    public void setData(){

        Bundle bundle = getArguments();
        mWords = bundle.getString("words");
        mDesc = bundle.getString("desc");

        tvWords.setText(mWords);
        tvDesc.setText(mDesc);

    }

}
