package com.adult.android.view.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

	public static int process = 0;

	private Context context;

	// private LoadingDialog dialog;

	public MyWebViewClient(Context context) {
		this.context = context;
	}

	@Override
	public boolean shouldOverrideUrlLoading(final WebView view, String url) {
		view.loadUrl(url);
		return true;
	}

	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
		// TODO Auto-generated method stub
		return super.shouldInterceptRequest(view, url);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		// if (null != dialog) {
		// dialog.dismiss();
		// }
		super.onPageFinished(view, url);
	}

	@Override
	public void onPageStarted(WebView view, final String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		// dialog = new LoadingDialog(context);
		// // 展示对话框
		// dialog.show();
	}

	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		Log.e("onReceivedError", "错误描述：" + description + ",地址：" + failingUrl
				+ ",ErrorCode:" + errorCode);
		// if (null != dialog) {
		// dialog.dismiss();
		// }
		String errorCode_zn = getZNErrorCode(errorCode);
		showErrorDialog(view, errorCode, description, errorCode_zn, failingUrl);
	}

	private String getZNErrorCode(int errorCode) {
		String errorCode_zn = null;
		switch (errorCode) {
			case WebViewClient.ERROR_AUTHENTICATION :
				errorCode_zn = "认证失败";
				break;
			case WebViewClient.ERROR_BAD_URL :
				errorCode_zn = "Url 错误";
				break;
			case WebViewClient.ERROR_CONNECT :
				errorCode_zn = "链接错误";
				break;
			case WebViewClient.ERROR_FAILED_SSL_HANDSHAKE :
				errorCode_zn = "SSL握手失败";
				break;
			case WebViewClient.ERROR_FILE :
				errorCode_zn = "文件错误";
				break;
			case WebViewClient.ERROR_FILE_NOT_FOUND :
				errorCode_zn = "文件不存在";
				break;
			case WebViewClient.ERROR_HOST_LOOKUP :
				errorCode_zn = "找不到网页";
				break;
			case WebViewClient.ERROR_IO :
				errorCode_zn = "IO 异常";
				break;
			case WebViewClient.ERROR_PROXY_AUTHENTICATION :
				errorCode_zn = "代理认证失败";
				break;
			case WebViewClient.ERROR_REDIRECT_LOOP :
				errorCode_zn = "重定向循环";
				break;
			case WebViewClient.ERROR_TIMEOUT :
				errorCode_zn = "链接服务超时";
				break;
			case WebViewClient.ERROR_TOO_MANY_REQUESTS :
				errorCode_zn = "请求过多";
				break;
			case WebViewClient.ERROR_UNKNOWN :
				errorCode_zn = "未知错误";
				break;
			case WebViewClient.ERROR_UNSUPPORTED_AUTH_SCHEME :
				errorCode_zn = "不支持scheme认证";
				break;
			case WebViewClient.ERROR_UNSUPPORTED_SCHEME :
				errorCode_zn = "不支持scheme";
				break;
			default :
				errorCode_zn = "未知错误";
				break;
		}
		return errorCode_zn;
	}

	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler,
			SslError error) {
		super.onReceivedSslError(view, handler, error);
	}

	private void showErrorDialog(WebView view, int errorCode,
			String description, String description_zn, String failingUrl) {
		view.loadUrl("file:///android_asset/errorMessage.html?");
		// view.loadUrl("file:///android_asset/errorMessage.html?url="
		// + failingUrl + "&code=" + errorCode + "&des=" + description
		// + "&des_zn=" + description_zn);
		// creatErroDialog(view, errorCode, description, failingUrl);
	}
}
