package com.adult.android.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.adult.android.view.product.ProductDetailsSecondView;

/**
 * @ClassName: SampleWebView
 * @Description:
 * @author JingYuchuan
 * @date 2015年5月19日 上午10:48:13
 * 
 */
public class SampleWebView extends WebView implements
		ProductDetailsSecondView.OnCanPullDownListener {

	public SampleWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		WebSettings settings = getSettings();

		int screenDensity = getResources().getDisplayMetrics().densityDpi;

		WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
		switch (screenDensity) {
		case DisplayMetrics.DENSITY_LOW:
			zoomDensity = WebSettings.ZoomDensity.CLOSE;
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			zoomDensity = WebSettings.ZoomDensity.MEDIUM;
			break;
		case DisplayMetrics.DENSITY_HIGH:
			zoomDensity = WebSettings.ZoomDensity.FAR;
			break;
		}
		settings.setDefaultZoom(zoomDensity);

		// 设置JS支持
		settings.setJavaScriptEnabled(true);
		// 将图片调整到适合webview的大小 
		// settings.setUseWideViewPort(true);

		// settings.setLoadWithOverviewMode(true);
		// 支持缩放 
		settings.setSupportZoom(true);
		settings.setTextSize(WebSettings.TextSize.SMALLEST);
		// 支持内容从新布局
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		// 多窗口 
		settings.supportMultipleWindows();
		// 设置可以访问文件 
		settings.setAllowFileAccess(true);
		// 当webview调用requestFocus时为webview设置节点
		// settings.setNeedInitialFocus(true);
		// 设置支持缩放
		settings.setBuiltInZoomControls(true);
		// 支持通过JS打开新窗口
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		// 支持自动加载图片
		settings.setLoadsImagesAutomatically(true);
	}

	public SampleWebView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SampleWebView(Context context) {
		this(context, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getPointerCount() > 1) {
			requestDisallowInterceptTouchEvent(true);
			// Log.e("onTouchEvent", "多个触控点");
		} else {
			requestDisallowInterceptTouchEvent(false);
			// Log.e("onTouchEvent", "一个触控点");
		}

		return super.onTouchEvent(event);
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
			boolean clampedY) {
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
		if (scrollY == 0 && clampedY) {
			allowPullDown = true;
		} else {
			allowPullDown = false;
		}
	}

	boolean allowPullDown = false;

	@Override
	public boolean allowPullDown() {
		return allowPullDown;
	}

	@Override
	public boolean isForeground() {
		return getVisibility() == View.VISIBLE;
	}
}
