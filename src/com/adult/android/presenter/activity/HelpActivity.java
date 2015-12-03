package com.adult.android.presenter.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.adult.android.R;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.view.webview.MyWebViewClient;
public class HelpActivity extends WebViewActivity {

	private WebView helpWebView;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setShowTitleBar(true);
		setActivityTitle(R.string.string_help);

		helpWebView = (WebView) findViewById(R.id.help_webview);
		initWebViewSetting(helpWebView);
		helpWebView.setWebViewClient(new MyWebViewClient(this));
		helpWebView.loadUrl(ServiceUrlConstants.HelpUrl);
	}

}
