package com.adult.android.utils;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class ActivityUtils {

	public static void startScaleAnimotion(View view, float toX, float toY) {
		ScaleAnimation animation = new ScaleAnimation(1.0f, toX, 1.0f, toY,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(300);
		animation.setFillAfter(true);
		animation.setInterpolator(new AccelerateInterpolator());
		view.startAnimation(animation);
	}

	public static void exitScaleAnimotion(View view, float fromX, float fromY) {
		ScaleAnimation animation = new ScaleAnimation(fromX, 1.0f, fromY, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(300);
		animation.setFillAfter(true);
		animation.setInterpolator(new AccelerateInterpolator());
		view.startAnimation(animation);
	}
}
