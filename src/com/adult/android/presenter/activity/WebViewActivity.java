package com.adult.android.presenter.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.view.webview.MyChromeWebClient;
import com.adult.android.view.webview.MyJavaScriptInterface;
import com.adult.android.view.webview.MyWebViewClient;

public class WebViewActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/** 设置 */
	protected void initWebViewSetting(WebView mWebView) {

		int screenDensity = getResources().getDisplayMetrics().densityDpi;

		WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.FAR;
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
		// mWebView.getSettings().setDefaultZoom(zoomDensity);
		// 提高解析性能
		// mWebView.getSettings().setRenderPriority(RenderPriority.HIGH);
		// 设置JS支持
		mWebView.getSettings().setJavaScriptEnabled(true);
		// 触摸焦点有效
		// mWebView.requestFocus();
		mWebView.setFocusable(false);
		mWebView.setFocusableInTouchMode(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		// 支持插件
		// mWebView.getSettings().setPluginsEnabled(true);
		// 将图片调整到适合webview的大小 
		mWebView.getSettings().setUseWideViewPort(true);
		mWebView.getSettings().setLoadWithOverviewMode(true);

		// 支持缩放 
		mWebView.getSettings().setSupportZoom(true);
		// 支持内容从新布局
		mWebView.getSettings().setLayoutAlgorithm(
				WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		// 多窗口 
		// mWebView.getSettings().supportMultipleWindows();
		// 关闭webview中缓存
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		// 设置可以访问文件 
		mWebView.getSettings().setAllowFileAccess(true);
		// 当webview调用requestFocus时为webview设置节点
		// mWebView.getSettings().setNeedInitialFocus(true);
		// 设置支持缩放
		// mWebView.getSettings().setBuiltInZoomControls(true);
		// 支持通过JS打开新窗口
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		// 支持自动加载图片
		mWebView.getSettings().setLoadsImagesAutomatically(true);
		// 不在浏览器打开，在自己的页面打开
		mWebView.setWebViewClient(new MyWebViewClient(this));
		mWebView.setWebChromeClient(new MyChromeWebClient(this, null, null));
		mWebView.addJavascriptInterface(new MyJavaScriptInterface(this),
				ServiceUrlConstants.WebView.WEBVIEW_INTERFACE_NAME);
		mWebView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				return false;
			}
		});
	}

}
