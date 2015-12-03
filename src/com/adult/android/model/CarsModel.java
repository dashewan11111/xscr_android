package com.adult.android.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

import com.adult.android.R;
import com.adult.android.entity.AddToCarsResponse;
import com.adult.android.entity.CartDto2;
import com.adult.android.entity.CartSkuDTO;
import com.adult.android.entity.GetCarsCountResponse;
import com.adult.android.entity.GetCarsListResponse;
import com.adult.android.entity.OrderCommitResponse;
import com.adult.android.entity.RuleListEntity;
import com.adult.android.entity.SkuShowAttr;
import com.adult.android.entity.UpdateCarsListResponse;
import com.adult.android.entity.UserInfo;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.CartParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.PostParamsFactory;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.model.listener.SampleModelListener;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.ActionTool;
import com.adult.android.utils.CopUtils;
import com.adult.android.utils.JsonUtils;
import com.adult.android.utils.Misc;

/***
 * 购物车业务
 * 
 * @author LiCheng
 * 
 */
public class CarsModel {
	public static enum CartOperatorSchema {
		EDIT, BROWSE
	}

	private static CarsModel carsModel = null;

	public static CarsModel getInsance() {
		if (null == carsModel) {
			carsModel = new CarsModel();
		}
		return carsModel;
	}

	public static String formatCartCount(String count) {
		if (!TextUtils.isEmpty(count) && !"0".equals(count)) {
			try {
				int number = Integer.parseInt(count);
				if (number > 99) {
					return "99+";
				} else if (number < 0) {
					return "";
				} else {
					return count;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 添加到购物车
	 */
	public void addToCart(final int qty, final SkuShowAttr skuShowAttr,
			final OnAddToCarsCompletedListener listener) {
		if (skuShowAttr == null) {
			ResponseException re = new ResponseException();
			re.setResultMsg("请选择商品");
			listener.onFailed(re);
		} else if (skuShowAttr.getSkuQty() < 1) {
			ResponseException re = new ResponseException();
			re.setResultMsg(AgentApplication.get().getResources()
					.getString(R.string.product_no_stock));
			listener.onFailed(re);
		} else if (skuShowAttr.getSkuQty() < qty) {
			ResponseException re = new ResponseException();
			re.setResultMsg("库存不够了");
			listener.onFailed(re);
		} else if (qty <= 0) {
			ResponseException re = new ResponseException();
			re.setResultMsg("请至少选择一件商品");
			listener.onFailed(re);
		} else {
			Map<String, String> skuMap = new HashMap<>();
			skuMap.put(skuShowAttr.getSkuId(), qty + "");
			addListToCart(skuMap, listener);
		}
	}

	/**
	 * 批量添加到购物车
	 */
	public void addListToCart(final Map<String, String> skuMap,
			final OnAddToCarsCompletedListener listener) {
		if (skuMap == null || skuMap.size() < 1) {
			ResponseException re = new ResponseException();
			re.setResultMsg("请选择商品");
			listener.onFailed(re);
		} else {
			InputBean inputBean = new InputBean();
			String key1, value1;
			// 业务参数
			if (UserLogic.getInsatnace().getIsLogin()) {
				key1 = CartParams.USER_ID;
				value1 = UserLogic.getInsatnace().getUserBean().getUserId();
			} else {
				key1 = CartParams.KEY;
				value1 = Misc.getDeviceId(AgentApplication.get());
			}
			// 签名
			try {
				inputBean = PostParamsFactory
						.createSignInputBean(false, ServiceUrlConstants.MOTHOD,
								CartParams.CART_ADD, key1, value1,
								"skuIdAndQtyMapStr", JsonUtils.generate(skuMap));
			} catch (IOException e) {
				e.printStackTrace();
			}
			inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
					CartParams.CART_ADD);
			inputBean.putQueryParam(key1, value1);
			try {
				inputBean.putQueryParam("skuIdAndQtyMapStr",
						JsonUtils.generate(skuMap));
			} catch (IOException e) {
				e.printStackTrace();
			}
			InternetClient.post(ServiceUrlConstants.getApiHost(), inputBean,
					AddToCarsResponse.class,
					new HttpResponseListener<AddToCarsResponse>() {

						@Override
						public void onStart() {
							listener.onStart();
						}

						@Override
						public void onSuccess(AddToCarsResponse product) {
							listener.onCompleted(product);
						}

						@Override
						public void onHttpException(HttpResponseException e) {
							listener.onFailed(e);
						}

						@Override
						public void onBusinessException(BusinessException e) {
							listener.onFailed(e);
						}

						@Override
						public void onOtherException(Throwable throwable) {
							ResponseException re = new ResponseException(
									throwable);
							re.setResultMsg("添加购物车失败");
							listener.onFailed(re);
						}

						@Override
						public void onFinish() {
							listener.onFinish();
						}
					});
		}
	}

	/**
	 * 删除购物车数据
	 */
	public void deleteProduct(final Context context, final String skuId,
			final OnDeleteCarsProductCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, CartParams.CART_DEL);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		maps.put(CartParams.SKU_ID_LIST_STR, skuId);
		maps.put("accessModel", "2");
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InputBean bean = new InputBean();
		InternetClient.get(url, bean, GetCarsListResponse.class,
				new HttpResponseListener<GetCarsListResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(GetCarsListResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("删除商品失败，请重试");
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});
	}

	/**
	 * 改变商品的选中状态
	 */
	public void changeSelect(final Context context, final List<String> skuIds,
			final OnGetCarsListCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, "cart.changeSelect");
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		StringBuilder skuIdList = new StringBuilder();
		if (skuIds == null || skuIds.size() == 0) {
			skuIdList.append("");
		} else {
			for (int i = 0; i < skuIds.size(); i++) {
				skuIdList.append(skuIds.get(i));
				if (i != skuIds.size() - 1) {
					skuIdList.append(",");
				}
			}
		}
		maps.put("skuIdListStr", skuIdList.toString());
		maps.put("accessModel", "2");
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, GetCarsListResponse.class,
				new HttpResponseListener<GetCarsListResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(GetCarsListResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						e.setResultMsg(AgentApplication.get().getString(
								R.string.cart_operation_failed));
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg(AgentApplication.get().getString(
								R.string.cart_operation_failed));
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});
	}

	/**
	 * 获取购物车列表
	 */
	public void getCarsList(final Context context, CartSkuDTO skuDTO,
			RuleListEntity entity, final OnGetCarsListCompletedListener listener) {

		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		maps.put(ServiceUrlConstants.MOTHOD, CartParams.CART_GET);
		maps.put("accessModel", "2");
		if (skuDTO != null && entity != null) {
			try {
				maps.put(
						"skuAndactivityInfoStr",
						skuDTO.getSkuId() + "," + entity.getFilteredRuleId()
								+ "," + entity.getRuleTerm() + ","
								+ entity.getRuleName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());

		InternetClient.get(url, null, GetCarsListResponse.class,
				new HttpResponseListener<GetCarsListResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(GetCarsListResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						e.setResultMsg("获取购物车信息失败");
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取购物车信息失败");
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});
	}

	/** 更新购物车数量 */
	public void updateQuntity(Context context, final String skuId,
			final String skuQty, final OnUpdateQtyCompletedListener listener) {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, CartParams.CART_QTY_UPDATE);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		maps.put(CartParams.SKU_ID, skuId);
		maps.put(CartParams.QTY, skuQty);
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InputBean bean = new InputBean();
		InternetClient.get(url, bean, UpdateCarsListResponse.class,
				new HttpResponseListener<UpdateCarsListResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UpdateCarsListResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onFailed(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException();
						re.setResultMsg("操作失败，请重试.");
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});
	}

	/** 获取购物车列表 */
	public void getCarsList(Context context,
			final OnGetCarsListCompletedListener listener) {
		InputBean bean = new InputBean();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		maps.put(ServiceUrlConstants.MOTHOD, CartParams.CART_GET);
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, bean, GetCarsListResponse.class,
				new HttpResponseListener<GetCarsListResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(GetCarsListResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取购物车信息失败");
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});
	}

	/**
	 * 选中单个商品数据
	 */
	public void selectProduct(Context context, String selectedItemsIds,
			final OnSelectProductCompletedListener listener) {
		InputBean bean = new InputBean();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		maps.put(ServiceUrlConstants.MOTHOD, CartParams.CART_SELECT);
		if (!TextUtils.isEmpty(selectedItemsIds)) {
			maps.put(CartParams.SKU_ID_LIST_STR, selectedItemsIds);
		}
		maps.put("accessModel", "2");
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, bean, GetCarsListResponse.class,
				new HttpResponseListener<GetCarsListResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(GetCarsListResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("请求失败，请重试");
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});
	}

	/**
	 * 获取购物车商品数量
	 */
	public void getCarsCount(Context context,
			final OnGetCarsCountCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, CartParams.CART_COUNT_GET);
		// 业务参数
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps.put(CartParams.USER_ID, UserLogic.getInsatnace().getUserBean()
					.getUserId());
		}
		if (null != Misc.getDeviceId(context)) {
			maps.put(CartParams.KEY, Misc.getDeviceId(context));
		}
		maps.put("accessModel", "2");
		// 签名
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InputBean bean = new InputBean();
		InternetClient.get(url, bean, GetCarsCountResponse.class,
				new HttpResponseListener<GetCarsCountResponse>() {

					@Override
					public void onStart() {
						listener.onGetCarsCountStart();
					}

					@Override
					public void onSuccess(GetCarsCountResponse product) {
						listener.onGetCarsCountCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onGetCarsCountFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取购物车数量失败，请重试");
						listener.onGetCarsCountFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onGetCarsCountFinish();
					}
				});
	}

	/**
	 * 提交订单
	 */
	public void commitOrder(String orderjson, String skuJson,
			String couponJson, final OnCommitOrderCompletedListener listener) {
		InputBean inputBean = new InputBean();
		// 签名
		inputBean = PostParamsFactory.createSignInputBean(true,
				ServiceUrlConstants.MOTHOD, CartParams.ORDER_SUBMIT, "order",
				orderjson, "cartSkus", skuJson, "promoteRelationDTOs",
				couponJson);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				CartParams.ORDER_SUBMIT);
		inputBean.putQueryParam("order", orderjson);
		inputBean.putQueryParam("cartSkus", skuJson);
		inputBean.putQueryParam("promoteRelationDTOs", couponJson);
		InternetClient.post(ServiceUrlConstants.getApiHost(), inputBean,
				OrderCommitResponse.class,
				new HttpResponseListener<OrderCommitResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(OrderCommitResponse product) {
						listener.onCompleted(product);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFailed(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("提交订单失败，请重试");
						listener.onFailed(re);
					}

					@Override
					public void onFinish() {
						listener.onFinish();
					}
				});

	}

	public void quickBuy(SkuShowAttr skuShowAttr, int qty,
			final SampleModelListener<CartDto2> listener) {
		if (skuShowAttr == null) {
			ResponseException re = new ResponseException();
			re.setResultMsg("请选择商品");
			listener.onRequestFail(re);
		} else if (skuShowAttr.getSkuQty() < 1) {
			ResponseException re = new ResponseException();
			re.setResultMsg("没有库存了");
			listener.onRequestFail(re);
		} else if (skuShowAttr.getSkuQty() < qty) {
			ResponseException re = new ResponseException();
			re.setResultMsg("库存不够了");
			listener.onRequestFail(re);
		} else if (qty <= 0) {
			ResponseException re = new ResponseException();
			re.setResultMsg("请至少选择一件商品");
			listener.onRequestFail(re);
		} else {
			String skuId = skuShowAttr.getSkuId();
			UserInfo userInfo = UserLogic.getInsatnace().getUserBean();
			String userId = "";
			if (userInfo != null) {
				userId = userInfo.getUserId();
			}
			Map<String, String> maps = ActionTool.getActionSignParams("skuId",
					skuId, "qty", qty + "", "userId", userId,
					ServiceUrlConstants.MOTHOD, "cart.direct");
			String url = ActionTool.getActionUrl(
					ServiceUrlConstants.getApiHost(), maps);
			InternetClient.get(url, null, GetCarsListResponse.class,
					new HttpResponseListener<GetCarsListResponse>() {
						@Override
						public void onStart() {
							listener.onRequestStart();
						}

						@Override
						public void onSuccess(GetCarsListResponse t) {
							listener.onRequestSuccess(t.getData());
						}

						@Override
						public void onHttpException(HttpResponseException e) {
							e.setResultMsg("网络请求失败，请重试");
							listener.onRequestFail(e);
						}

						@Override
						public void onBusinessException(BusinessException e) {
							listener.onRequestFail(e);
						}

						@Override
						public void onOtherException(Throwable throwable) {
							ResponseException re = new ResponseException(
									throwable);
							re.setResultMsg("快速购买发生异常，请稍后重试。");
							listener.onRequestFail(re);
						}

						@Override
						public void onFinish() {
							listener.onRequestFinish();
						}
					});
		}
	}

	/** 添加到购物车回调 */
	public static interface OnAddToCarsCompletedListener {
		void onStart();

		void onCompleted(final AddToCarsResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onFinish();
	}

	/** 获取购物车数据回调 */
	public static interface OnGetCarsListCompletedListener {
		void onStart();

		void onCompleted(final GetCarsListResponse info);

		void onHttpException(HttpResponseException e);

		void onFailed(ResponseException e);

		void onFinish();
	}

	/**
	 * 删除购物车数据回调
	 */
	public static interface OnDeleteCarsProductCompletedListener {
		void onCompleted(final GetCarsListResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/** 更新购物车数量回调 */
	public static interface OnUpdateQtyCompletedListener {
		void onCompleted(final UpdateCarsListResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/**
	 * 选中商品的回调
	 */
	public static interface OnSelectProductCompletedListener {
		void onCompleted(final GetCarsListResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/**
	 * 提交订单回调
	 */
	public static interface OnCommitOrderCompletedListener {
		void onCompleted(final OrderCommitResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/**
	 * 获取购物车数据回调
	 */
	public static interface OnGetCarsCountCompletedListener {
		void onGetCarsCountCompleted(final GetCarsCountResponse info);

		void onGetCarsCountFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onGetCarsCountStart();

		void onGetCarsCountFinish();
	}

}
