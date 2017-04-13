package com.study.tedkim.implicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCall = (Button) findViewById(R.id.button_call);
        btnCall.setOnClickListener(this);

        Button btnDial = (Button) findViewById(R.id.button_dial);
        btnDial.setOnClickListener(this);

        Button btnContact = (Button) findViewById(R.id.button_contact);
        btnContact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {

            case R.id.button_call:

                intent = new Intent(Intent.ACTION_CALL);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

                break;

            case R.id.button_dial:

                intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);

                break;

            case R.id.button_contact:

                Intent i = new Intent(Intent.ACTION_PICK);
                i.setData(Uri.parse("content://contacts/phones"));
                startActivityForResult(i,0);

                break;


        }

    }
}
