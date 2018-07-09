package com.navercorp.ted.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CircularAnimation {

    private Activity mActivity; // 시작 액티비티
    private View mTriggerView;  // 트리거 뷰
    private int mColorOrImage;  // 확장 컬러 또는 이미지

    private int mEnterAnim;     // 진입 애니메이션
    private int mEndAnim;       // 퇴장 애니메이션

    private long mDuration      = 1000;        // 애니메이션 지속 시간
    private float mStartRadius  = 17432576;    // 애니메이션 시작 지름
    private float mEndRadius    = 17432577;    // 애니메이션 종료 지름

    public CircularAnimation() { }

    public static CircularAnimation.Builder activityTransition (Activity startActivity, View triggerView) {
        return new Builder(startActivity, triggerView);
    }

    public static class Builder {

        private Activity mStartActivity; // 시작 액티비티
        private View mTriggerView;       // 트리거 뷰
        private int mColorOrImage;       // 확장 컬러 또는 이미지

        private int mEnterAnim;          // 진입 애니메이션
        private int mEndAnim;            // 퇴장 애니메이션

        private long mDuration      = 500;         // 애니메이션 지속 시간
        private float mStartRadius  = 17432576;    // 애니메이션 시작 지름
        private float mEndRadius    = 17432577;    // 애니메이션 종료 지름

        private CircularAnimation.OnAnimationEndListener mAnimationEndListener; // 확장 애니메이션 종료 리스너



        public Builder(Activity startActivity, View triggerView) {
            mStartActivity = startActivity;
            mTriggerView = triggerView;
        }

        public Builder background (int colorOrImage) {
            mColorOrImage = colorOrImage;
            return this;
        }

        public Builder startRadius (float startRadius) {
            mStartRadius = startRadius;
            return this;
        }

        public Builder endRadius (float endRadius) {
            mEndRadius = endRadius;
            return this;
        }

        public Builder duration (long duration) {
            mDuration = duration;
            return this;
        }

        public Builder animation (int enterAnim, int endAnim) {
            mEnterAnim = enterAnim;
            mEndAnim = endAnim;
            return this;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void build(OnAnimationEndListener listener) {

            mAnimationEndListener = listener;

            // 애니메이션 뷰 이미지 생성
            final ImageView animView = new ImageView(mStartActivity);
            animView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            animView.setImageResource(mColorOrImage);

            // 현재 액티비티에 추가
            final ViewGroup decorView = (ViewGroup) mStartActivity.getWindow().getDecorView();
            int parentWidth = decorView.getWidth();
            int parentHeight = decorView.getHeight();
            decorView.addView(animView, parentWidth, parentHeight);

            // 이벤트 시작점 추출
            int[] point = new int[2];
            mTriggerView.getLocationInWindow(point);
            int centerX = point[0] + mTriggerView.getWidth() / 2;
            int centerY = point[1] + mTriggerView.getHeight() / 2;

            // 최대 확장범위 지정
            int maxWidth = Math.max(centerX, parentWidth - centerX);
            int maxHeight = Math.max(centerY, parentHeight - centerY);
            final int finalRadius = (int) Math.hypot(maxWidth, maxHeight);

            // 애니메이션 생성 및 실행
            Animator revealAnim = ViewAnimationUtils.createCircularReveal(animView, centerX, centerY, 0, finalRadius);
            revealAnim.setDuration(mDuration);
            revealAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    // 확장 애니메이션
                    mAnimationEndListener.onAnimationEnd();
                    mStartActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    // 축소 애니메이션
                    mTriggerView.postDelayed(() -> {

                        Animator hideAnim = ViewAnimationUtils.createCircularReveal(animView, centerX, centerY, finalRadius, 0);
                        hideAnim.setDuration(mDuration);
                        hideAnim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                try {
                                    decorView.removeView(animView);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        hideAnim.start();

                    }, mDuration + 100);
                }
            });
            revealAnim.start();
        }
    }

    public interface OnAnimationEndListener {
        void onAnimationEnd();
    }
}
