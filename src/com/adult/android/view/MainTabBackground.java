package com.adult.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.adult.android.R;
/**
 * Created by RoyJing on 15/7/21.
 */
public class MainTabBackground extends View {
	private float radius;
	private float transparentHeight;

	public MainTabBackground(Context context) {
		this(context, null);
	}

	public MainTabBackground(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MainTabBackground(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		Resources resources = context.getResources();
		radius = resources.getDimension(R.dimen.big_radius);
		transparentHeight = resources.getDimension(R.dimen.transparent_height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();

		canvas.save();
		paint.reset();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColor(0x00ffffff);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(0, 0, getWidth(), transparentHeight, paint);

		canvas.save();
		paint.reset();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColor(getResources().getColor(R.color.main_activity_tab));
		canvas.drawCircle(getWidth() / 2, radius, radius, paint);

		canvas.save();
		paint.reset();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColor(getResources().getColor(R.color.main_activity_tab));
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(0, transparentHeight, getWidth(), getHeight(), paint);
	}
}
