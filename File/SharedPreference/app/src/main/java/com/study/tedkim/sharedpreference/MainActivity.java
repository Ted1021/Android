package com.study.tedkim.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvWelcomeWord;
    EditText etEditWord;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView() {

        tvWelcomeWord = (TextView) findViewById(R.id.textView_welcomeWord);
        etEditWord = (EditText) findViewById(R.id.editText_editWord);

        SharedPreferences pref = getSharedPreferences("prefTest", MODE_PRIVATE);
        String welcomeWord = pref.getString("WelcomeWord", "HI :)");

        tvWelcomeWord.setText(welcomeWord);

        btnSave = (Button) findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_save:

                // getSharedPreferences : XML 파일 명을 명시 해주어야 한다
                SharedPreferences pref = getSharedPreferences("prefTest", MODE_PRIVATE);
                SharedPreferences.Editor pEditor = pref.edit();

                String welcomeWord = etEditWord.getText().toString();
                pEditor.putString("WelcomeWord", welcomeWord);
                pEditor.commit();

                Toast.makeText(MainActivity.this, "Successfully Saved!", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
