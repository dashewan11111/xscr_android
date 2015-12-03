package com.adult.android.view.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.adult.android.entity.ProductDTO;
import com.adult.android.presenter.activity.ProductDetailsActivity;
import com.alibaba.fastjson.JSON;

public class MyJavaScriptInterface {

	private Context context;

	public MyJavaScriptInterface(Context context) {
		this.context = context;
	}

	@JavascriptInterface
	public void jumpToProductDetails(String json) {

		Log.e(">etails>", json);
		ProductDTO product = JSON.parseObject(json, ProductDTO.class);
		Intent intent = new Intent();
		intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT_ID, product);
		intent.setClass(context, ProductDetailsActivity.class);
		((Activity) context).startActivity(intent);
	}

}
