package com.adult.android.presenter.activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.adult.android.R;
/**
 * 将原doc文件转成HTML文件，通过WebView加载
 * agreement.html保存在assets目录下
 * */

public class CheckAgreementActivity extends BaseActivity {
	private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setShowTitleBar(true);
		setActivityTitle(R.string.agreement);
	    webview = (WebView) findViewById(R.id.agreement_html);  
        //设置WebView属性，能够执行Javascript脚本  
        webview.getSettings().setJavaScriptEnabled(true);  
        //加载需要显示的网页  
        webview.loadUrl("file:///android_asset/agreement.html");  
        //设置Web视图  
        webview.setWebViewClient(new AgreementWebViewClient());  
	}

	  //Web视图  
    private class AgreementWebViewClient extends WebViewClient {  
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        }  
    }  

	
}
