package com.study.tedkim.screen_rotation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    int mPosition=0;

    TextView tvName, tvDesc;
    String mName, mDesc;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(int position){

        DetailFragment fragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        initView(view);

        return view;
    }

    public void initView(View view){

        Bundle args = getArguments();
        mPosition = args.getInt("position");

        mName = PlanetInfo.PLANET.get(mPosition);
        mDesc = PlanetInfo.DESC.get(mPosition);

        tvName = (TextView) view.findViewById(R.id.textView_name);
        tvName.setText(mName);

        tvDesc = (TextView) view.findViewById(R.id.textView_desc);
        tvDesc.setText(mDesc);

    }

}
