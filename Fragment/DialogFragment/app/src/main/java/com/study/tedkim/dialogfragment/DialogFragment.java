package com.study.tedkim.dialogfragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());
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
