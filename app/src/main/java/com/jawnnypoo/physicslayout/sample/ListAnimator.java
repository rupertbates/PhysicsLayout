package com.jawnnypoo.physicslayout.sample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class ListAnimator {
    private final static long DURATION = 200;

    public interface AnimationEndListener {
        void OnAnimationEnd();
    }

    private final View button;
    private final ViewGroup listContainer;
    private final PhysicsListView listView;
    private final View title;
    private AnimationEndListener animationEndListener;

    public ListAnimator(View button, ViewGroup listContainer, PhysicsListView listView, View title, AnimationEndListener animationEndListener) {
        this.button = button;
        this.listContainer = listContainer;
        this.listView = listView;
        this.title = title;
        this.animationEndListener = animationEndListener;
    }

    public void runCloseListAnimation() {
            runListAnimation(listContainer.getWidth(), button.getWidth(), listContainer.getHeight(), button.getHeight(), new StartAndEndListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    button.setVisibility(View.VISIBLE);
                    title.setVisibility(View.GONE);
                    listContainer.setVisibility(View.INVISIBLE);
                    setWidthAndHeight(listContainer, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }
            });
        }

    public void runOpenListAnimation() {
        runListAnimation(button.getWidth(), listContainer.getWidth(), button.getHeight(), listContainer.getHeight(), new StartAndEndListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                listView.removeAllViews();
                setWidthAndHeight(listContainer, button.getWidth(), button.getHeight());
                listContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setVisibility(View.GONE);
                title.setVisibility(View.VISIBLE);
                setHeight(listContainer, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (animationEndListener != null)
                    animationEndListener.OnAnimationEnd();
            }
        });
    }

    public void runListAnimation(int fromWidth, int toWidth, int fromHeight, int toHeight, Animator.AnimatorListener listener) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(fromWidth, toWidth);
        widthAnimation.setDuration(DURATION);
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setWidth(listContainer, (int) animation.getAnimatedValue());
            }
        });

        ValueAnimator heightAnimation = ValueAnimator.ofInt(fromHeight, toHeight);
        heightAnimation.setDuration(DURATION);
        heightAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setHeight(listContainer, (int) animation.getAnimatedValue());
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(widthAnimation, heightAnimation);
        set.addListener(listener);
        set.start();
    }

    protected static void setWidth(View view, int width) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    protected static void setHeight(View view, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }

    protected static void setWidthAndHeight(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    protected static class StartAndEndListener implements Animator.AnimatorListener{

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
