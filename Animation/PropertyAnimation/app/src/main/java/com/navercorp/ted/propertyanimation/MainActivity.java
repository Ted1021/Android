package com.navercorp.ted.propertyanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button translate, scale, rotation, alpha, set, next;

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

    @Override
    public void onClick(View view) {

        Animation anim;
        switch(view.getId()){

            case R.id.translate:
                anim = AnimationUtils.loadAnimation(this, R.anim.translate);
                translate.setAnimation(anim);
                break;

            case R.id.scale:
                anim = AnimationUtils.loadAnimation(this, R.anim.scale);
                scale.setAnimation(anim);
                break;

            case R.id.rotation:
                anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
                rotation.setAnimation(anim);
                break;

            case R.id.alpha:
                anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
                alpha.setAnimation(anim);
                break;

            case R.id.set:
                anim = AnimationUtils.loadAnimation(this, R.anim.set);
                set.setAnimation(anim);
                break;

            case R.id.next:

                break;
        }
    }
}
