package com.study.tedkim.screen_rotation;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ListView lvPlanetList;

    OnListItemClickListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(){

        ListFragment fragment = new ListFragment();

        return fragment;
    }

    public interface OnListItemClickListener{
        void onItemClicked(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mListener = (OnListItemClickListener) context;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        setListView(view);

        return view;
    }

    public void setListView(View view){

        lvPlanetList = (ListView) view.findViewById(R.id.listView_planetList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, PlanetInfo.PLANET);
        lvPlanetList.setAdapter(adapter);

        lvPlanetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mListener.onItemClicked(position);
            }
        });
    }

}
