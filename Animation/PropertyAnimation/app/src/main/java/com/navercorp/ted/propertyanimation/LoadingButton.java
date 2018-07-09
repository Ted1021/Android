package com.navercorp.ted.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class LoadingButton extends AppCompatButton {

    private State mState;
    private boolean mIsMorphingInProgress;
    private Drawable mGradientDrawable;
    private AnimatorSet mMorphingAnimatorSet;
    private OnButtonAnimationEndListener mButtonAnimationEndLisntener;

    private enum State {
        PROGRESS, IDLE
    }

    public LoadingButton(Context context) {
        super(context);
        init(context);
    }

    public LoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoadingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mGradientDrawable = getBackground();
    }

    public void startAnimation(OnButtonAnimationEndListener listener){

        if(mState != State.IDLE){
            return;
        }

        mButtonAnimationEndLisntener = listener;

        int initialWidth = getWidth();
        int initialHeight = getHeight();

        int initialCornerRadius = 0;
        int finalCornerRadius = 1000;

        mState = State.PROGRESS;
        mIsMorphingInProgress = true;
        this.setText(null);
        setClickable(false);

        int toWidth = 300; //some random value...
        int toHeight = toWidth; //make it a perfect circle

        ObjectAnimator cornerAnimation =
                ObjectAnimator.ofFloat(mGradientDrawable,
                        "cornerRadius",
                        initialCornerRadius,
                        finalCornerRadius);

        ValueAnimator widthAnimation = ValueAnimator.ofInt(initialWidth, toWidth);
        widthAnimation.addUpdateListener(valueAnimator -> {

            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = val;
            setLayoutParams(layoutParams);
        });

        ValueAnimator heightAnimation = ValueAnimator.ofInt(initialHeight, toHeight);
        heightAnimation.addUpdateListener(valueAnimator -> {

            int val = (Integer) valueAnimator.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = val;
            setLayoutParams(layoutParams);
        });

        mMorphingAnimatorSet = new AnimatorSet();
        mMorphingAnimatorSet.setDuration(300);
        mMorphingAnimatorSet.playTogether(cornerAnimation, widthAnimation, heightAnimation);
        mMorphingAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                listener.onButtonAnimationEnd();
                mIsMorphingInProgress = false;
            }
        });
        mMorphingAnimatorSet.start();
    }

    public interface OnButtonAnimationEndListener {
        void onButtonAnimationEnd();
    }
}
