package com.study.tedkim.firebase_database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference mDatabase;

    EditText etId, etName, etEmail;
    Button btnInsert, btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase database Root 참조 가져오기
        mDatabase = FirebaseDatabase.getInstance().getReference();

        initView();

    }

    public void initView(){

        etId = (EditText) findViewById(R.id.editText_id);
        etName = (EditText) findViewById(R.id.editText_name);
        etEmail = (EditText) findViewById(R.id.editText_email);

        btnInsert = (Button) findViewById(R.id.button_insert);
        btnInsert.setOnClickListener(this);

        btnCheck = (Button) findViewById(R.id.button_check);
        btnCheck.setOnClickListener(this);

    }

    // 특정 Child (Category) 에 새로운 Data Insert
    public void addUser(){

        String userId, userName, email;

        userId = etId.getText().toString();
        userName = etName.getText().toString();
        email = etEmail.getText().toString();

        Users newUser = new Users(userName, email);

        mDatabase.child("users").child(userId).setValue(newUser);
    }

    // 특정 Category 내의 특정 값을 찾아 Data Select 하는 메소드
    public void getUser(){

        String userId = etId.getText().toString();
        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);

                Toast.makeText(MainActivity.this, user.userName + " / " + user.email, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "file loading is failed" ,Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 특정 Category 내의 모든 객체들을 Iterator 로 가져와 Select 하는 메소드
    public void getUsers(){

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();

                while(iterator.hasNext()){

                    Users user = iterator.next().getValue(Users.class);
                    Log.d("CHECK_DATA", ">>>>>>>>>>>"+user.userName+"/"+user.email);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.button_insert:

                addUser();

                break;

            case R.id.button_check:

                getUsers();

                break;

        }
    }
}
