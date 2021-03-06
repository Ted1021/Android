package com.study.tedkim.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditArticleActivity extends AppCompatActivity {

    EditText etTitle, etName, etContents;
    String mTitle, mName, mContents;

    Button btnSave;

    int mPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        initView();

        setData();
    }

    public void initView(){

        etTitle = (EditText) findViewById(R.id.editText_title);
        etName = (EditText) findViewById(R.id.editText_name);
        etContents = (EditText) findViewById(R.id.editText_contents);

        btnSave = (Button) findViewById(R.id.button_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                sendData();

            }
        });
    }

    public void setData(){

        Intent intent = getIntent();

        mPosition = intent.getIntExtra("POSITION", 0);

        etTitle.setText(ArticleData.TITLE.get(mPosition));
        etName.setText(ArticleData.NAME.get(mPosition));
        etContents.setText(ArticleData.CONTENTS.get(mPosition));

    }

    public void getData(){

        mTitle = etTitle.getText().toString();
        mName = etName.getText().toString();
        mContents = etContents.getText().toString();

    }

    public void sendData(){

        Intent intent = new Intent();

        intent.putExtra("TITLE", mTitle);
        intent.putExtra("NAME", mName);
        intent.putExtra("CONTENTS", mContents);
        intent.putExtra("POSITION", mPosition);

        setResult(RESULT_OK, intent);
        finish();

    }
}
