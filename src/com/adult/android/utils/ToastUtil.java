package com.adult.android.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adult.android.R;

/**
 * @ClassName: ToastUtil
 * @Description:
 * @author JingYuchuan
 * @date 2015年4月7日 下午1:50:51
 * 
 */
public class ToastUtil {
	public static void showToastLong(Context context, String text) {
		if (context != null) {
			showSample(context, text, Toast.LENGTH_LONG);
		}
	}

	public static void showToastShort(Context context, String text) {
		if (context != null) {
			showSample(context, text, Toast.LENGTH_SHORT);
		}
	}

	public static void showToastLong(Context context, int textId) {
		if (context != null) {
			showSample(context, textId, Toast.LENGTH_LONG);
		}
	}

	public static void showToastShort(Context context, int textId) {
		if (context != null) {
			showSample(context, textId, Toast.LENGTH_SHORT);
		}
	}

	private static void showSample(Context context, String text, int length) {
		final Toast toast = Toast.makeText(context, text, length);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.toast_layout, null);
		((TextView) contentView.findViewById(R.id.toast_text)).setText(text);
		toast.setView(contentView);
		// toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		// toast.setMargin(0f, 0f);
		toast.show();
	}

	private static void showSample(Context context, int textId, int length) {
		final Toast toast = Toast.makeText(context, textId, length);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.toast_layout, null);
		((TextView) contentView.findViewById(R.id.toast_text)).setText(textId);
		toast.setView(contentView);
		// toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		// toast.setMargin(0f, 0f);
		toast.show();
	}
}
