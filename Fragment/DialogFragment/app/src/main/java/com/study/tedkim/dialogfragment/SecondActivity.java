package com.study.tedkim.dialogfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 *
 * setStyle() 을 이용한 다양한 Dialog 출력 형식 파악
 *
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNormal, btnNoTitle, btnNoFrame, btnNoInput, btnHolo, btnHoloLightDialog, btnHoloLight, btnHoloLightPanel;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    static final int STYLE_0 = InternalDialogFragment.STYLE_NORMAL;
    static final int STYLE_1 = InternalDialogFragment.STYLE_NO_TITLE;
    static final int STYLE_2 = InternalDialogFragment.STYLE_NO_FRAME;
    static final int STYLE_3 = InternalDialogFragment.STYLE_NO_INPUT;

    static final int THEME_HOLO = android.R.style.Theme_Holo;
    static final int THEME_HOLO_LIGHT_DIALOG = android.R.style.Theme_Holo_Light_Dialog;
    static final int THEME_HOLO_LIGHT = android.R.style.Theme_Holo_Light;
    static final int THEME_HOLO_LIGHT_PANEL = android.R.style.Theme_Holo_Light_Panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initView();

    }

    public void initView(){

        btnNormal = (Button) findViewById(R.id.button_normal);
        btnNormal.setOnClickListener(this);

        btnNoTitle = (Button) findViewById(R.id.button_no_title);
        btnNoTitle.setOnClickListener(this);

        btnNoFrame = (Button) findViewById(R.id.button_no_frame);
        btnNoFrame.setOnClickListener(this);

        btnNoInput = (Button) findViewById(R.id.button_no_input);
        btnNoInput.setOnClickListener(this);

        btnHolo = (Button) findViewById(R.id.button_holo);
        btnHolo.setOnClickListener(this);

        btnHoloLightDialog = (Button) findViewById(R.id.button_holo_light_dialog);
        btnHoloLightDialog.setOnClickListener(this);

        btnHoloLight = (Button) findViewById(R.id.button_holo_light);
        btnHoloLight.setOnClickListener(this);

        btnHoloLightPanel = (Button) findViewById(R.id.button_holo_light_panel);
        btnHoloLightPanel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button_normal:
                setFragment(STYLE_0, 0);
                break;

            case R.id.button_no_title:
                setFragment(STYLE_1, 0);
                break;

            case R.id.button_no_frame:
                setFragment(STYLE_2, 0);
                break;

            case R.id.button_no_input:
                setFragment(STYLE_3, 0);
                break;

            case R.id.button_holo:
                setFragment(STYLE_0, THEME_HOLO);
                break;

            case R.id.button_holo_light_dialog:
                setFragment(STYLE_0, THEME_HOLO_LIGHT_DIALOG);
                break;

            case R.id.button_holo_light:
                setFragment(STYLE_0, THEME_HOLO_LIGHT);
                break;

            case R.id.button_holo_light_panel:
                setFragment(STYLE_0, THEME_HOLO_LIGHT_PANEL);
                break;
        }
    }

    public void setFragment(int style, int theme){

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if(prev != null){
            fragmentTransaction.remove(prev);
        }

        InternalDialogFragment fragment = InternalDialogFragment.newInstance(style, theme);
        fragment.show(fragmentTransaction, "dialog");

    }
}
