package com.navercorp.ted.propertyanimation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button translate, scale, rotation, alpha, next;
    private LoadingButton set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translate = findViewById(R.id.translate);
        translate.setOnClickListener(this);

        scale = findViewById(R.id.scale);
        scale.setOnClickListener(this);

        rotation = findViewById(R.id.rotation);
        rotation.setOnClickListener(this);

        alpha = findViewById(R.id.alpha);
        alpha.setOnClickListener(this);

        set = findViewById(R.id.set);
        set.setOnClickListener(this);

        next = findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        Animation anim;
        switch (view.getId()) {

            case R.id.translate:
                anim = AnimationUtils.loadAnimation(this, R.anim.translate);
                anim.setInterpolator(this, android.R.anim.anticipate_overshoot_interpolator);
                translate.startAnimation(anim);
                break;

            case R.id.scale:
                anim = AnimationUtils.loadAnimation(this, R.anim.scale);
                anim.setInterpolator(this, android.R.anim.anticipate_overshoot_interpolator);
                scale.startAnimation(anim);
                break;

            case R.id.rotation:
                anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
                anim.setInterpolator(this, android.R.anim.anticipate_overshoot_interpolator);
                rotation.startAnimation(anim);
                break;

            case R.id.alpha:
                anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
                anim.setInterpolator(this, android.R.anim.anticipate_overshoot_interpolator);
                alpha.startAnimation(anim);
                break;

            case R.id.set:
                set.startAnimation(() -> {
                    CircularAnimation.activityTransition(this, view)
                            .background(R.color.colorPrimaryDark)
                            .build(() -> startActivity(new Intent(MainActivity.this, SubActivity.class)));
                });
                break;

            case R.id.next:
                CircularAnimation.activityTransition(this, view)
                        .background(R.color.colorPrimaryDark)
                        .build(() -> startActivity(new Intent(MainActivity.this, SubActivity.class)));
                break;
        }
    }
}
