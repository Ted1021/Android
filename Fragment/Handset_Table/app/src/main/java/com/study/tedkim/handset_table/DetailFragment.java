package com.study.tedkim.handset_table;


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

    TextView tvPlanetName, tvPlanetDesc;

    String mPlanetName, mPlanetDesc;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(int position){

        DetailFragment detailFragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);

        detailFragment.setArguments(args);

        return detailFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        initView(view);

        initData();

        return view;
    }

    public void initView(View view){

        tvPlanetName = (TextView) view.findViewById(R.id.textView_name);
        tvPlanetDesc = (TextView) view.findViewById(R.id.textView_describe);

    }

    public void initData(){

        int mPosition = getArguments().getInt("position");

        if(mPosition != -1) {

            mPlanetName = PlanetInfo.PLANET.get(mPosition);
            mPlanetDesc = PlanetInfo.DESC.get(mPosition);

            tvPlanetName.setText(mPlanetName);
            tvPlanetDesc.setText(mPlanetDesc);
        }
    }

}
