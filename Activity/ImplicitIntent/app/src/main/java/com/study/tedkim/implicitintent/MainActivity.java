package com.study.tedkim.implicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = (Button) findViewById(R.id.button_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
            }
        });

        Button btnDial = (Button) findViewById(R.id.button_dial);
        btnDial.setOnClickListener(this);

        Button btnContact = (Button) findViewById(R.id.button_contact);
        btnContact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_call:

                Intent intent = new Intent(Intent.ACTION_CALL);
                break;

            case R.id.button_dial:

                break;

            case R.id.button_contact:

                break;


        }

    }
}
