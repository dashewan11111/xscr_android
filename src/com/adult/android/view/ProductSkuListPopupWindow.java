package com.adult.android.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.AddToCartResponse;
import com.adult.android.entity.CalculateCartResponse;
import com.adult.android.entity.Sku2;
import com.adult.android.entity.SkuForCart;
import com.adult.android.model.CartModel;
import com.adult.android.model.CartModel.OnCalculateCartCompletedListener;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.presenter.activity.OrderCommitActivity;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.MyEditText2.OnEditNumberListener;
import com.lidroid.xutils.BitmapUtils;

public class ProductSkuListPopupWindow extends BasePopUpWindow implements
		OnEditNumberListener, OnClickListener {

	private Context context;

	private List<SkuForCart> dataList;

	private ImageView imgSku, imgClose;

	private TextView txtSkuPrice, txtSkuName;

	private LinearLayout llytContainer;

	private MyEditText2 myEditText;

	private Button btnOk;

	private BitmapUtils mBitmapUtils;

	private SkuForCart defaultSku;

	private int action;

	private LoadingDialog loadingDialog;

	public ProductSkuListPopupWindow(Context context, int action,
			LinearLayout llytShadow, List<SkuForCart> dataList) {
		super(context, llytShadow);
		this.context = context;
		this.dataList = dataList;
		this.action = action;
		loadingDialog = new LoadingDialog(context);
		mBitmapUtils = new BitmapUtils(context);
		mBitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		mBitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
		initPopupWindow();
	}

	private void initPopupWindow() {
		View view = LayoutInflater.from(context).inflate(
				R.layout.pop_product_sku_list, null);
		setContentView(view);
		imgSku = (ImageView) view.findViewById(R.id.pop_product_sku_image);
		imgClose = (ImageView) view.findViewById(R.id.pop_product_sku_close);
		txtSkuPrice = (TextView) view.findViewById(R.id.pop_product_sku_price);
		txtSkuName = (TextView) view.findViewById(R.id.pop_product_sku_name);
		myEditText = (MyEditText2) view
				.findViewById(R.id.pop_product_sku_count);
		llytContainer = (LinearLayout) view
				.findViewById(R.id.pop_product_sku_container);
		btnOk = (Button) view.findViewById(R.id.pop_product_sku_btn_ok);
		btnOk.setOnClickListener(this);
		imgClose.setOnClickListener(this);
		myEditText.setNums(1);
		myEditText.setOnAddReduceClickListener(this, 0, 0);
		addItems();
	}

	private void addItems() {
		for (int i = 0; i < dataList.size(); i++) {
			SkuForCart sku = dataList.get(i);
			if (null == sku) {
				return;
			}
			View view = LayoutInflater.from(context).inflate(
					R.layout.item_round_conner_text, null);
			if (0 == i) {
				defaultSku = sku;
				updateDefaultValue();
				setSelected(view);
			}

			TextView txtSku = (TextView) view
					.findViewById(R.id.item_round_conner_text);
			txtSku.setText(sku.getName());
			view.setTag(sku);
			view.setOnClickListener(new OnSkuItemSelected());
			llytContainer.addView(view);
		}
	}

	private void updateDefaultValue() {
		if (null == defaultSku) {
			return;
		}
		txtSkuPrice.setText(defaultSku.getPrice());
		txtSkuName.setText(defaultSku.getSkuName());
		mBitmapUtils.display(imgSku, ServiceUrlConstants.getImageHost()
				+ defaultSku.getImgUrl());

		myEditText.setTag(defaultSku);
		myEditText.setMaxNumber(defaultSku.getStockQty());
	}

	class OnSkuItemSelected implements OnClickListener {

		@Override
		public void onClick(View view) {
			defaultSku = (SkuForCart) view.getTag();
			setSelected(view);
			updateDefaultValue();
		}
	}

	@Override
	public void dismiss() {
		super.dismiss();
		((SkuListPopupWindowListener) this.context).onPopupWindowDismiss();
	}

	public void setSelected(View view) {
		for (int i = 0; i < llytContainer.getChildCount(); i++) {
			View item = llytContainer.getChildAt(i);
			(item.findViewById(R.id.item_round_conner_text))
					.setBackgroundResource(R.drawable.common_round_conner_bg_gray);
		}
		(view.findViewById(R.id.item_round_conner_text))
				.setBackgroundResource(R.drawable.common_round_conner_bg_yellow);
	}

	public interface SkuListPopupWindowListener {
		void onPopupWindowDismiss();

		void onBtnOk(Sku2 defaultSku, int count);
	}

	@Override
	public void onAddClick(int position1, int position2, View v,
			int previousNum, int currentNum, Object tag) {
		myEditText.getEditText().setText(currentNum + "");
	}

	@Override
	public void onReduceClick(int position1, int position2, View v,
			int previousNum, int currentNum, Object tag) {
		myEditText.getEditText().setText(currentNum + "");
	}

	@Override
	public void onMaxQty(int position1, int position2, View v, int max) {

	}

	@Override
	public void onInputNumberDone(int position1, int position2, int number,
			int max, Object tag) {

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.pop_product_sku_close:
			dismiss();
			break;
		case R.id.pop_product_sku_btn_ok:
			if (GeneralTool.isEmpty(AgentApplication.get().getUserId())) {
				ToastUtil.showToastShort(context, "请先登陆");
				return;
			}
			if (0 == action) {
				addToCart();
			} else {
				quickBuy();
			}
			break;
		default:
			break;
		}
	}

	/** 立即购买 */
	private void quickBuy() {
		loadingDialog.show();
		CartModel.getInstance().calculateCart(
				AgentApplication.get().getUserId(), defaultSku.getSkuId(), "1",
				defaultSku.getSkuId(),
				myEditText.getEditText().getText().toString(),
				new OnCalculateCartCompletedListener() {

					@Override
					public void onSuccess(CalculateCartResponse info) {
						loadingDialog.dismiss();
						if (null != info.getData()) {
							Intent intent = new Intent();
							intent.setClass(context, OrderCommitActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
									| Intent.FLAG_ACTIVITY_SINGLE_TOP);
							List<SkuForCart> selectedList = new ArrayList<SkuForCart>();
							selectedList.add(defaultSku);
							intent.putExtra("skuList",
									(Serializable) selectedList);
							intent.putExtra("data", info.getData());
							intent.putExtra("isQuickBuy", true);
							context.startActivity(intent);
						}
					}

					@Override
					public void onStart() {
						loadingDialog.dismiss();
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						loadingDialog.dismiss();
					}

					@Override
					public void onFinish() {
						loadingDialog.dismiss();
					}

					@Override
					public void onFailed(ResponseException e) {
						loadingDialog.dismiss();
					}
				});
	}

	/** 添加到购物车 */
	private void addToCart() {
		loadingDialog.show();
		CartModel.getInstance().addToCart(AgentApplication.get().getUserId(),
				defaultSku.getSkuId(),
				myEditText.getEditText().getText().toString(),
				new CartModel.OnAddToCartCompletedListener() {

					@Override
					public void onSuccess(AddToCartResponse info) {
						loadingDialog.dismiss();
						ToastUtil.showToastShort(context, "添加购物车成功");
						// 保存购物车数量
						AgentApplication.get().setCartCount(
								Integer.parseInt(info.getData()
										.getCartProductCount()));
						dismiss();
					}

					@Override
					public void onStart() {

					}

					@Override
					public void onHttpException(HttpResponseException e) {
						loadingDialog.dismiss();

					}

					@Override
					public void onFinish() {
						loadingDialog.dismiss();

					}

					@Override
					public void onFailed(ResponseException e) {
						loadingDialog.dismiss();
					}
				});

	}
}
