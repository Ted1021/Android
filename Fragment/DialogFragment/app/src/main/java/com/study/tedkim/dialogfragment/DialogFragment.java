package com.study.tedkim.dialogfragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 *
 *  Dialog Fragment
 *
 *  android 에서 제공하는 Dialog Fragment 를 상속 받음으로써
 *  프래그먼트의 레이아웃을 시스템 대화 상자처럼 사용할 수 있다
 *
 *  onCreateDialog() 를 사용하는것이 핵심이다.
 *
 */
public class DialogFragment extends android.support.v4.app.DialogFragment implements View.OnClickListener{

    OnDataSetListener mListener;

    EditText etName;
    RadioGroup rgGender;

    Button btnSave, btnCancel;

    String mName, mGender;

    public DialogFragment() {
        // Required empty public constructor
    }

    public interface OnDataSetListener{
        void onDataSave(Bundle bundle);
    }

    public static DialogFragment newInstance(){

        DialogFragment dialogFragment = new DialogFragment();

        return dialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mListener = (OnDataSetListener) context;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        initView(view);

        return view;
    }

    public void initView(View view){

        etName = (EditText) view.findViewById(R.id.editText_name);
        rgGender = (RadioGroup) view.findViewById(R.id.radioGroup_gender);

        btnSave = (Button) view.findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);

        btnCancel = (Button) view.findViewById(R.id.button_cancel);
        btnCancel.setOnClickListener(this);

    }

    // onCreateView() 에서 완전히 생성된 Fragment 의 레이아웃을 System Dialog 에 집어넣는 과정이다.
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 1. 새로운 Dialog 생성
        Dialog dialog = new Dialog(getActivity());
        // 2. 생성한 Dialog 에 onCreateView() 를 통해 inflate 된 fragment 의 layout 을 대입한다.
        dialog.setContentView(R.layout.fragment_dialog);

        return dialog;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_save:

                if(rgGender.getCheckedRadioButtonId() == R.id.radioButton_man)
                    mGender = "man";
                else
                    mGender = "woman";

                mName = etName.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", mName);
                bundle.putString("gender", mGender);

                mListener.onDataSave(bundle);

                break;

            case R.id.button_cancel:

                break;
        }
    }
}
