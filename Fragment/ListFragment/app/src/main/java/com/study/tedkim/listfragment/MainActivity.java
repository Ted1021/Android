package com.study.tedkim.listfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements WordListFragment.OnWordListItemClickListener {

    public ArrayList<String> WORDS = new ArrayList<>(Arrays.asList("Boy", "Girl", "School", "Go", "Away"));
    public ArrayList<String> DESC = new ArrayList<>(Arrays.asList("(명) 소년, 남자(사내)아이",
                                                                  "(명) 소녀, 여자(계집)아이",
                                                                    "(명) 학교 (주로 초,중,고등학교)",
                                                                    "(동) 가다, 나아가다",
                                                                    "(형) ~로 부터 멀리 떨어진"));

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WordListFragment fragment = WordListFragment.newInstance(WORDS, DESC);
        setFragment(fragment);

    }

    @Override
    public void onItemClicked(int position) {

        WordDetailFragment fragment = WordDetailFragment.newInstance(WORDS.get(position), DESC.get(position));
        setFragment(fragment);
    }

    public void setFragment(Fragment fragment){

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
