package com.study.tedkim.dialogfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 *
 */

public class InternalDialogFragment extends DialogFragment {

    EditText etName;
    RadioGroup rgGender;

    Button btnSave, btnCancel;

    public InternalDialogFragment() {
        // Required empty public constructor
    }


    public static InternalDialogFragment newInstance(int style, int theme){

        InternalDialogFragment fragment = new InternalDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("style", style);
        bundle.putInt("theme", theme);
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_internal_dialog, container, false);

        initData();

        initView(view);



        return view;
    }

    public void initView(View view){

        etName = (EditText) view.findViewById(R.id.editText_name);
        rgGender = (RadioGroup) view.findViewById(R.id.radioGroup_gender);
        btnSave = (Button) view.findViewById(R.id.button_save);
        btnCancel = (Button) view.findViewById(R.id.button_cancel);

    }

    public void initData(){

        Bundle bundle = getArguments();

        int temp = bundle.getInt("style");
        int mTheme = bundle.getInt("theme");

        int mStyle;
        switch(temp){

            case 0:
                mStyle = STYLE_NORMAL;
                break;
            case 1:
                mStyle = STYLE_NO_TITLE;
                break;
            case 2:
                mStyle = STYLE_NO_FRAME;
                break;
            case 3:
                mStyle = STYLE_NO_INPUT;
                break;
            default:
                mStyle = STYLE_NORMAL;
        }

        setStyle(mStyle, mTheme);
    }

}
