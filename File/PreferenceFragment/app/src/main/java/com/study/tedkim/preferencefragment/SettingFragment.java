package com.study.tedkim.preferencefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * A simple {@link Fragment} subclass.
 */

// Preference Fragment 를 사용하기 위해서는 'PreferenceFragmentCompat' 을 상속 받아야 한다
public class SettingFragment extends PreferenceFragmentCompat {


    public SettingFragment() {
        // Required empty public constructor
    }

    // 단순히 'onCreate' 생명주기에서
    // res 폴더에 미리 준비해 둔 XML 파일을 preference 로 등록해 준다
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.setting);
    }

}
