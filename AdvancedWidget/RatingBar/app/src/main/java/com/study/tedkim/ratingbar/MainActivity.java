package com.study.tedkim.ratingbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RatingBar rbStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRatingBar();
    }

    // Rating Bar 를 선언하고 리스너를 정의한다.
    public void setRatingBar(){

        // 1. RatingBar 선언
        rbStars = (RatingBar) findViewById(R.id.ratingBar_stars);

        // 2. Listener 구현
        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                // 2.1 사용자가 별점을 드래그하거나 클릭 한 직후의 점수 상태를 rating 변수로 전달받는다.
                Toast.makeText(getApplicationContext(), "you've got "+rating+""+" points !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
