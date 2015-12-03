package com.adult.android.presenter.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.OrderDtoForList;
import com.adult.android.model.OrderModel2;
import com.adult.android.model.OrderModel2.OnGetOrderSkuListCompletedListener;
import com.adult.android.model.OrderSku;
import com.adult.android.model.OrderSkuListResponse;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;
import com.lidroid.xutils.BitmapUtils;

public class EvaluationActivity extends BaseActivity implements OnClickListener {

	private LinearLayout llytContainer;

	private OrderDtoForList order;

	private ImageView imgHideName;

	private Button btnCommit;

	private BitmapUtils bitmapUtils;

	private LoadingDialog loadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViews();
	}

	private void initViews() {
		setShowTitleBar(true);
		setActivityTitle("评价");
		bitmapUtils = new BitmapUtils(this);
		loadingDialog = new LoadingDialog(this);
		order = (OrderDtoForList) getIntent().getSerializableExtra("order");
		llytContainer = (LinearLayout) findViewById(R.id.evaluation_container);
		imgHideName = (ImageView) findViewById(R.id.evaluation_product_image);
		btnCommit = (Button) findViewById(R.id.evaluation_btn_commit);
		imgHideName.setOnClickListener(this);
		btnCommit.setOnClickListener(this);

		getSkuList();

	}

	/**
	 * 订单Sku列表
	 */
	private void getSkuList() {
		loadingDialog.show();
		OrderModel2.getInstance().getOrderSkuList(order.getOrderId(),
				new OnGetOrderSkuListCompletedListener() {

					@Override
					public void onSuccess(OrderSkuListResponse info) {
						loadingDialog.dismiss();
						List<OrderSku> skuList = info.getData().getList();
						if (null == skuList) {
							return;
						}
						for (OrderSku sku : skuList) {
							String productName = sku.getSku_name();
							String orderTime = order.getCreateTime();
							String imageUrl = sku.getImage_url();
							View viewMain = getLayoutInflater().inflate(
									R.layout.layout_evaluation_item, null);
							ImageView imgProduct = (ImageView) viewMain
									.findViewById(R.id.evaluation_product_image);
							TextView txtOrderTime = (TextView) viewMain
									.findViewById(R.id.evaluation_order_time);
							TextView txtProductName = (TextView) viewMain
									.findViewById(R.id.evaluation_product_name);
							RatingBar evaluationPoint = (RatingBar) viewMain
									.findViewById(R.id.evaluation_point);
							EditText extxtContent = (EditText) viewMain
									.findViewById(R.id.evaluation_edit_content);
							txtProductName.setText(productName);
							txtOrderTime.setText(orderTime);
							bitmapUtils.display(imgProduct, imageUrl);
							evaluationPoint
									.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

										@Override
										public void onRatingChanged(
												RatingBar arg0, float value,
												boolean arg2) {

										}
									});
							llytContainer.addView(viewMain);
						}

					}

					@Override
					public void onStart() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onHttpException(HttpResponseException e) {
						loadingDialog.dismiss();
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onFailed(ResponseException exception) {
						ToastUtil.showToastShort(EvaluationActivity.this,
								exception.getResultMsg());
					}
				});
	}

	private boolean isHide;

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.evaluation_product_image:
			if (isHide) {

			}
			isHide = !isHide;
			break;
		case R.id.evaluation_btn_commit:
			commitEvaluation();
			break;
		default:
			break;
		}

	}

	/** 提交评价 */
	private void commitEvaluation() {
		for (int i = 0; i < llytContainer.getChildCount(); i++) {
			View view = llytContainer.getChildAt(i);
			String point = ((RatingBar) view
					.findViewById(R.id.evaluation_point)).getRating() + "";
			String content = ((EditText) view
					.findViewById(R.id.evaluation_edit_content)).getText()
					.toString();
		}

	}
}
