package com.yellowbytestudios.jarvisui.jarvisui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Robert on 10-Nov-17.
 */

public class JarvisButton extends RelativeLayout {

    private View rootView;
    private int baseColor;
    private int secondaryColor;
    private int jarvisStyle;
    private int rotationTime;

    private ImageView base;
    private ImageView outer;
    private ImageView inner;

    public JarvisButton(@NonNull Context context) {
        super(context);
        init(context);
    }

    public JarvisButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.ui_button, this);
        rootView.findViewById(R.id.outer_circle).startAnimation(getRotateAnimation(0f, 360f));
        rootView.findViewById(R.id.inner_circle).startAnimation(getRotateAnimation(360f, 0f));
    }

    private void init(Context context, AttributeSet attrs) {
        rootView = inflate(context, R.layout.ui_button, this);
        outer = (ImageView) rootView.findViewById(R.id.outer_circle);
        inner = (ImageView) rootView.findViewById(R.id.inner_circle);
        base = (ImageView) rootView.findViewById(R.id.base_circle);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.JarvisButton, 0, 0);
        baseColor = ta.getColor(R.styleable.JarvisButton_baseColor, -1);
        secondaryColor = ta.getColor(R.styleable.JarvisButton_secondaryColor, -1);
        jarvisStyle = ta.getInteger(R.styleable.JarvisButton_jarvisStyle, 0);
        rotationTime = ta.getInteger(R.styleable.JarvisButton_rotationTime, 2000);

        base.startAnimation(getRotateAnimation(360f, 0f));
        outer.startAnimation(getRotateAnimation(0f, 360f));
        inner.startAnimation(getRotateAnimation(360f, 0f));

        setBaseColor(baseColor);
        setSecondaryColor(secondaryColor);
        setJarvisStyle(jarvisStyle);
        setRotationTime(rotationTime);
    }

    private RotateAnimation getRotateAnimation(float start, float end) {
        RotateAnimation rotateAnimation = new RotateAnimation(start, end,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        return rotateAnimation;
    }

    public int getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(int baseColor) {
        if (baseColor != -1) {
            this.baseColor = baseColor;
            outer.setColorFilter(baseColor);
            inner.setColorFilter(baseColor);
            base.setColorFilter(baseColor);
        }
    }

    public int getJarvisStyle() {
        return jarvisStyle;
    }

    public void setJarvisStyle(int jarvisStyle) {
        this.jarvisStyle = jarvisStyle;
        switch (jarvisStyle) {

            case 1:
                base.setImageResource(R.drawable.ic_ui_arc);
                outer.setImageResource(R.drawable.ui_circle);
                inner.setImageResource(R.drawable.ic_ui_arc);
                break;
            case 2:
                base.setImageResource(R.drawable.ui_circle_thick);
                outer.setImageResource(R.drawable.ic_ui_arc);
                inner.setImageResource(R.drawable.ic_ui_arc);
                break;
            case 3:
                base.setImageResource(R.drawable.ic_ui_arc);
                outer.setImageResource(R.drawable.ic_ui_arc);
                inner.setImageResource(R.drawable.ui_circle_thick);
                break;
            case 4:
                base.setImageResource(R.drawable.ic_ui_arc);
                outer.setImageResource(R.drawable.ic_ui_arc);
                inner.setImageResource(R.drawable.ui_circle);
                break;
            default:
                this.jarvisStyle = 0;
                break;
        }
    }

    public int getRotationTime() {
        return rotationTime;
    }

    public void setRotationTime(int rotationTime) {
        this.rotationTime = rotationTime;
        base.getAnimation().setDuration(rotationTime);
        outer.getAnimation().setDuration(rotationTime);
        inner.getAnimation().setDuration(rotationTime);
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
        if (secondaryColor != -1) {
            outer.setColorFilter(secondaryColor);
        }
    }
}
