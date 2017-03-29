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

    public void setRatingBar(){

        rbStars = (RatingBar) findViewById(R.id.ratingBar_stars);

        rbStars.setNumStars(5);
        rbStars.setStepSize(0.5f);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Toast.makeText(getApplicationContext(), "you've got "+rating+""+" points !!", Toast.LENGTH_SHORT).show();


            }
        });
    }

}
