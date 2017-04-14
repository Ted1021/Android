package com.study.tedkim.intent_filter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCallAdder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallAdder = (Button) findViewById(R.id.button_callAdder);
        btnCallAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.study.tedkim.intent_filter.adder");

                intent.putExtra("FIRST", 3);
                intent.putExtra("SECOND", 7);

                startActivity(intent);
            }
        });
    }
}
