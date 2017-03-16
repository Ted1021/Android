package com.study.tedkim.fragment_parameter_sending;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    TextView tvCounter;
    Button btnIncreaseCount;

    public MainFragment() {
        // Required empty public constructor
    }

    // fragment 의 생성전에 bundle 을 미리 생성해 두어야 한다
    // fragment 의 생성자는 아무 인자도 받을 수 없으므로 이를 위한 정적 메소드를 새로이 정의 해 주어야 한다.
    public static MainFragment newInstance(int count){

        MainFragment fragment = new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("Count", count);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initView(view);

        return view;

    }

    public void initView(View view){

        tvCounter = (TextView) view.findViewById(R.id.textView_counter);
        btnIncreaseCount = (Button) view.findViewById(R.id.button_increase_count);

        Bundle bundle = getArguments();
        int mCount = bundle.getInt("Count");
        Log.e("Text",">>>>>>>>>"+mCount);

        tvCounter.setText(mCount+"");

    }

}
