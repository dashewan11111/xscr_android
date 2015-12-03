package com.adult.android.view.product;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.adult.android.R;

/**
 * Created by RoyJing on 15/7/24.
 */
public class GraphicView extends FrameLayout implements
		ProductDetailsSecondView.OnCanPullDownListener {
	private ProductWebView mProductWebView;
	private View mErrorView;
	Handler hanler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HttpStatus.SC_NOT_FOUND:
				mProductWebView.setVisibility(GONE);
				mErrorView.setVisibility(VISIBLE);
				break;
			}
		}
	};
	private String url;

	public GraphicView(Context context) {
		this(context, null);
	}

	public GraphicView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GraphicView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public ProductWebView getProductWebView() {
		return mProductWebView;
	}

	public View getErrorView() {
		return mErrorView;
	}

	public void loadUrl(String url) {
		this.url = url;
		mProductWebView.loadUrl(url);
	}

	public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
		this.url = url;
		mProductWebView.loadUrl(url, additionalHttpHeaders);
	}

	public void loadData(String data, String mimeType, String encoding) {
		mProductWebView.loadData(data, mimeType, encoding);
	}

	public void loadDataWithBaseURL(String baseUrl, String data,
			String mimeType, String encoding, String failUrl) {
		mProductWebView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding,
				failUrl);
	}

	private void init(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		mProductWebView = new ProductWebView(context);
		mErrorView = inflater.inflate(R.layout.internet_error_layout, null);
		addView(mErrorView);
		addView(mProductWebView);
		mErrorView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadUrl(url);
			}
		});
		mErrorView.setVisibility(GONE);
		mProductWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView view, final String url,
					Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				new Thread(new Runnable() {
					@Override
					public void run() {
						int status = getRespStatus(url);
						hanler.sendEmptyMessage(status);
					}
				}).start();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}
		});
	}

	@Override
	public boolean allowPullDown() {
		return mErrorView.getVisibility() == VISIBLE
				|| (mProductWebView.getVisibility() == VISIBLE && mProductWebView
						.allowPullDown());
	}

	@Override
	public boolean isForeground() {
		return getVisibility() == VISIBLE;
	}

	private int getRespStatus(String url) {
		if (TextUtils.isEmpty(url))
			return HttpStatus.SC_NOT_FOUND;
		int status = -1;
		try {
			HttpHead head = new HttpHead(url);
			HttpClient client = new DefaultHttpClient();
			HttpResponse resp = client.execute(head);
			status = resp.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		return status;
	}
}
