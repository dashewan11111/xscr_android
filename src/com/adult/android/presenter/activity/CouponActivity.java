package com.adult.android.presenter.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CartSkuDTO;
import com.adult.android.entity.CouponModel;
import com.adult.android.entity.QueryCouponRes;
import com.adult.android.entity.SkuStr;
import com.adult.android.entity.UserBaseInfo;
import com.adult.android.entity.UserStrs;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.UserActionModel;
import com.adult.android.model.UserActionModel.UserAction;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.CouponParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.PostParamsFactory;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.model.internet.listener.UserBussListener;
import com.adult.android.presenter.fragment.main.tab.adapter.CouponAdapter;
import com.adult.android.utils.CopUtils;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.JsonUtils;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;
import com.alibaba.fastjson.JSON;

public class CouponActivity extends BaseActivity implements OnClickListener,
		UserBussListener {

	public static final String SKU_LIST = "skuList";

	public static final String COUPON_MODEL_LIST = "CouponModelList";

	protected static final String ORDER_ID = "OrderId";

	private ListView listView;

	private View tagView;

	private TextView btnNoUse;

	private TextView btnUsed;

	private TextView btnOverTime;

	private TextView countTet, txtCountDesc;

	private LoadingDialog lodingDialog;

	private Button btn_coupon_description;

	private LinearLayout zero_layout;

	private int size;

	private View rootView;

	private PopupWindow popWindow;

	private List<CouponModel> datas;
	// 从订单获取
	private List<CartSkuDTO> skuList;

	// 当前已使用的优惠券
	private HashMap<Integer, CouponModel> usingMap;
	// 当前要使用优惠券的Group位置
	private int orderId;

	private List<CouponModel> listNormal;

	// private List<CouponModel> myList = new ArrayL;

	private List<CouponModel> myList = new ArrayList<CouponModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowTitleBar(true);
		setActivityTitle(R.string.string_my_coupon);
		initViews();
	}

	/**
	 * 获取我的优惠券
	 */
	public void getDatas(String stype, UserAction action) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, CouponParams.MY_COUPON_GET);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put("userid", UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		maps.put("identification", stype);

		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		UserActionModel.sendGetAction(url, this, action);
	}

	/**
	 * 符合订单的数据
	 */
	public void getDatas(List<CartSkuDTO> list, UserAction action) {
		if (null == list) {
			return;
		}
		try {
			UserStrs user = new UserStrs();
			user.setUserid(UserLogic.getInsatnace().getUserBean().getUserId());
			List<SkuStr> skuList = new ArrayList<SkuStr>();
			for (CartSkuDTO sku : list) {
				SkuStr akustr = new SkuStr();
				akustr.setSkuId(sku.getSkuId().toString());
				if (null != sku.getSubTotalPrice()
						&& 0 < sku.getSubTotalPrice().doubleValue()) {
					akustr.setPrice(sku.getSubTotalPrice().doubleValue() + "");
				}
				skuList.add(akustr);
			}
			InputBean inputBean = new InputBean();
			inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
					CouponParams.MY_ORDER_COUPON_GET);
			inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
					ServiceUrlConstants.APP_KEY_VALUE);
			inputBean.putQueryParam(ServiceUrlConstants.VERSION,
					ServiceUrlConstants.VERSION_VALUE);
			inputBean.putQueryParam("UserStrs", JsonUtils.generate(user));
			inputBean.putQueryParam("SkusStrs", JsonUtils.generate(skuList));
			UserActionModel.sendPostAction(this, action, inputBean);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSuccess(UserAction action, UserBaseInfo baseInfo) {
		lodingDialog.dismiss();
		if (null == baseInfo.getData()) {
			return;
		}
		datas = JSON.parseArray(baseInfo.getData().toString(),
				CouponModel.class);
		List<CouponModel> list = checkUsed(datas);
		listNormal = list;
		size = list.size();
		if (null == action) {
			listView.setAdapter(new CouponAdapter(listNormal, usingMap,
					orderId, getLayoutInflater(), 0, action,
					new OnMyUseChangeListener(), CouponActivity.this));
			countTet.setText(listNormal.size() + "");
			txtCountDesc.setText(R.string.can_use_coupon);
			if (0 == listNormal.size()) {
				zero_layout.setVisibility(View.VISIBLE);
			} else {
				zero_layout.setVisibility(View.GONE);
			}
		} else {
			switch (action) {
			// 没有使用
			case ACTION_COUPON_NO_USE:
				// CouponLogic.getInstance().setNoUser(datas);
				if (size != 0) {
					countTet.setText(size + "");
					listView.setVisibility(View.VISIBLE);
					zero_layout.setVisibility(View.GONE);
					listView.setAdapter(new CouponAdapter(list, usingMap,
							orderId, getLayoutInflater(), 0, action,
							new OnMyUseChangeListener(), CouponActivity.this));
				} else {
					listView.setVisibility(View.GONE);
					zero_layout.setVisibility(View.VISIBLE);
					countTet.setText("0");
				}
				txtCountDesc.setText(R.string.unuse_coupon);
				break;
			// 已经过时
			case ACTION_COUPON_OUT_TIME:
				// CouponLogic.getInstance().setOutTime(datas);
				if (size != 0) {
					countTet.setText(size + "");
					listView.setVisibility(View.VISIBLE);
					zero_layout.setVisibility(View.GONE);
					listView.setAdapter(new CouponAdapter(list, usingMap,
							orderId, getLayoutInflater(), 2, action,
							new OnMyUseChangeListener(), CouponActivity.this));
				} else {
					countTet.setText("0");
					listView.setVisibility(View.GONE);
					zero_layout.setVisibility(View.VISIBLE);
				}
				txtCountDesc.setText(R.string.outdate_coupon);
				break;
			// 已经用过
			case ACTION_COUPON_USED:
				// CouponLogic.getInstance().setUsed(datas);
				if (size != 0) {
					countTet.setText(size + "");
					listView.setVisibility(View.VISIBLE);
					zero_layout.setVisibility(View.GONE);
					listView.setAdapter(new CouponAdapter(list, usingMap,
							orderId, getLayoutInflater(), 1, action,
							new OnMyUseChangeListener(), CouponActivity.this));
				} else {
					countTet.setText("0");
					listView.setVisibility(View.GONE);
					zero_layout.setVisibility(View.VISIBLE);
				}
				txtCountDesc.setText(R.string.used_coupon);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 过期劵
		case R.id.coupon_overTimeBtn:
			// 检测缓存
			getDatas("2", UserAction.ACTION_COUPON_OUT_TIME);
			updateLineView(R.id.coupon_overTimeLine);
			updateTextView((TextView) v);
			break;
		// 已使用
		case R.id.coupon_usedBtn:
			getDatas("1", UserAction.ACTION_COUPON_USED);
			updateLineView(R.id.coupon_usedLine);
			updateTextView((TextView) v);
			break;
		// 未使用
		case R.id.coupon_noUseBtn:
			getDatas("0", UserAction.ACTION_COUPON_NO_USE);
			updateLineView(R.id.coupon_noUseLine);
			updateTextView((TextView) v);
			break;
		case R.id.btn_coupon_description:
			showPopupWindow();
			break;
		default:
			break;
		}
	}

	void updateLineView(int id) {
		if (tagView != null) {
			tagView.setBackgroundResource(R.color.lightgray);
			tagView = null;
		}
		tagView = findViewById(id);
		tagView.setBackgroundResource(R.color.red);
	}

	void updateTextView(TextView btn) {
		if (btnNoUse != null) {
			btnNoUse.setTextColor(getResources().getColor(R.color.black));
			btnNoUse = null;
		}
		btnNoUse = btn;
		btnNoUse.setTextColor(getResources().getColor(R.color.red));
	}

	/**
	 * 显示使用说明
	 */
	@SuppressWarnings("deprecation")
	public void showPopupWindow() {

		// rootView.setVisibility(View.GONE);
		View view = LayoutInflater.from(this).inflate(
				R.layout.layout_popupwindow, null);
		popWindow = new PopupWindow(view, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		popWindow.setTouchable(true);
		popWindow.setFocusable(true);
		// 外部点击消失
		popWindow.setOutsideTouchable(true);
		popWindow.setBackgroundDrawable(new BitmapDrawable());
		popWindow.setAnimationStyle(R.style.popup_anim);
		// 显示位置
		popWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);

		ImageView btnBack = (ImageView) view.findViewById(R.id.btn_back);
		btnBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// rootView.setVisibility(View.VISIBLE);
				popWindow.dismiss();
			}
		});
	}

	private void initViews() {
		rootView = (LinearLayout) findViewById(R.id.rootview);
		listView = (ListView) findViewById(R.id.coupon_listView);
		zero_layout = (LinearLayout) findViewById(R.id.zero_layout);
		btnOverTime = (TextView) findViewById(R.id.coupon_overTimeBtn);
		btnOverTime.setOnClickListener(this);
		btnNoUse = (TextView) findViewById(R.id.coupon_noUseBtn);
		btnNoUse.setOnClickListener(this);
		btnUsed = (TextView) findViewById(R.id.coupon_usedBtn);
		btnUsed.setOnClickListener(this);
		/*
		 * btnUsed = (Button) findViewById(R.id.coupon_usedBtn);
		 * btnUsed.setOnClickListener(this);
		 */
		tagView = findViewById(R.id.coupon_noUseLine);
		countTet = (TextView) findViewById(R.id.coupon_countTet);
		txtCountDesc = (TextView) findViewById(R.id.coupon_countTet_des);
		lodingDialog = new LoadingDialog(this);

		btn_coupon_description = (Button) findViewById(R.id.btn_coupon_description);
		btn_coupon_description.setOnClickListener(this);

		Intent intent = getIntent();
		if (null != intent && null != intent.getSerializableExtra(SKU_LIST)) {
			btnUsed.setVisibility(View.GONE);
			btnOverTime.setVisibility(View.GONE);
			btnNoUse.setVisibility(View.GONE);
			findViewById(R.id.coupon_usedLine).setVisibility(View.GONE);
			findViewById(R.id.coupon_overTimeLine).setVisibility(View.GONE);
			findViewById(R.id.coupon_noUseLine).setVisibility(View.GONE);
			skuList = (List<CartSkuDTO>) intent.getSerializableExtra(SKU_LIST);
			orderId = intent.getIntExtra(ORDER_ID, -1);
			usingMap = (HashMap<Integer, CouponModel>) intent
					.getSerializableExtra(COUPON_MODEL_LIST);
			// updateLineView(R.id.coupon_moneyLine);
			getDatas(skuList, null);
		} else {
			btnUsed.setVisibility(View.VISIBLE);
			btnOverTime.setVisibility(View.VISIBLE);
			btnNoUse.setVisibility(View.VISIBLE);
			findViewById(R.id.coupon_usedLine).setVisibility(View.VISIBLE);
			findViewById(R.id.coupon_overTimeLine).setVisibility(View.VISIBLE);
			findViewById(R.id.coupon_noUseLine).setVisibility(View.VISIBLE);
			getDatas("0", UserAction.ACTION_COUPON_NO_USE);
		}
	}

	/**
	 * 判断该优惠券是否在使用
	 *
	 * @param couponModel
	 * @return
	 */
	protected boolean isUsing(CouponModel couponModel) {
		if (null == usingMap) {
			return false;
		}
		Iterator ite = usingMap.keySet().iterator();
		while (ite.hasNext()) {
			Integer key = (Integer) ite.next();
			CouponModel value = usingMap.get(key);
			if (value.getCouponstockid() == couponModel.getCouponstockid()
					&& key != orderId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		if (null != popWindow && popWindow.isShowing()) {
			popWindow.dismiss();
		}
		super.onBackPressed();
	}

	@Override
	public void onFaile(UserAction action, BusinessException e) {
		lodingDialog.dismiss();
		ToastUtil.showToastShort(
				CouponActivity.this,
				GeneralTool.isEmpty(e.getResultMsg()) ? getResources()
						.getString(R.string.request_error_text) : e
						.getResultMsg());
	}

	@Override
	public void onHttpException(UserAction action, HttpResponseException e) {
		lodingDialog.dismiss();
		ToastUtil.showToastShort(CouponActivity.this,
				getResources().getString(R.string.request_error_text));

	}

	@Override
	public void onOtherException(UserAction action, Throwable e) {
		lodingDialog.dismiss();
		ToastUtil.showToastShort(CouponActivity.this,
				getResources().getString(R.string.request_error_text));
	}

	@Override
	public void onStartTask() {
		lodingDialog.show();
	}

	@Override
	public void onFinishTask() {
		lodingDialog.dismiss();
	}

	public class OnMyUseChangeListener implements
			CouponAdapter.OnUseChangeListener {

		@Override
		public void onUse(final CouponModel model) {
			if (isUsing(model)) {
				ToastUtil.showToastShort(CouponActivity.this,
						R.string.use_by_other_order);
				return;
			}

			// 查询优惠劵金额
			for (CartSkuDTO m : skuList) {
				CouponModel cou = new CouponModel();
				cou.setSkuId(String.valueOf(m.getSkuId()));
				cou.setPrice(Double.valueOf(String.valueOf(m.getSubTotalPrice())));
				myList.add(cou);
			}
			String json = "";
			try {
				json = JsonUtils.generate(myList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			InputBean inputBean = PostParamsFactory.createSignInputBean(true,
					ServiceUrlConstants.MOTHOD, "myorder.coupon.getDiscount",
					"couponStockId", String.valueOf(model.getCouponstockid()),
					"couponConditions", json);
			inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
					"myorder.coupon.getDiscount");
			inputBean.putQueryParam("couponStockId",
					String.valueOf(model.getCouponstockid()));
			inputBean.putQueryParam("couponConditions", json);

			InternetClient.post(ServiceUrlConstants.getApiHost(), inputBean,
					QueryCouponRes.class,
					new HttpResponseListener<QueryCouponRes>() {

						@Override
						public void onStart() {

						}

						@Override
						public void onSuccess(QueryCouponRes statusInfo) {
							// 优惠劵金额重新赋值
							model.setPrice(statusInfo.getData());
							Intent intent = new Intent();
							intent.putExtra("CouponModel", model);
							intent.putExtra(ORDER_ID, orderId);
							setResult(Activity.RESULT_OK, intent);
							finish();
						}

						@Override
						public void onHttpException(HttpResponseException e) {

						}

						@Override
						public void onBusinessException(BusinessException e) {

						}

						@Override
						public void onOtherException(Throwable throwable) {

						}

						@Override
						public void onFinish() {

						}
					});

		}

		@Override
		public void onCancel(int orderId) {
			Intent intent = new Intent();
			intent.putExtra(ORDER_ID, orderId);
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	}

	/**
	 * 过滤调被使用的
	 *
	 * @param couponList
	 * @return
	 */
	private List<CouponModel> checkUsed(List<CouponModel> couponList) {
		List<CouponModel> newList = new ArrayList<CouponModel>();
		for (CouponModel model : couponList) {
			if (!isUsing(model)) {
				newList.add(model);
			}
		}
		return newList;
	}

}
