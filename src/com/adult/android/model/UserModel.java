package com.adult.android.model;

import java.util.HashMap;
import java.util.Map;

import com.adult.android.entity.CouponResponse;
import com.adult.android.entity.EvaluationResponse;
import com.adult.android.entity.MyTopicResponse;
import com.adult.android.entity.PayInfoResponse;
import com.adult.android.entity.UserResponse;
import com.adult.android.entity.VerifyResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.OrderParams2;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams2;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.CopUtils;

/**
 * 分类业务模块
 * 
 * @author Administrator
 * 
 */
public class UserModel {

	private static UserModel userModel = null;

	public static UserModel getInstance() {
		if (null == userModel) {
			userModel = new UserModel();
		}
		return userModel;
	}

	/** 获取购物车列表 */
	public void login(String userName, String password,
			final OnLoginCompletedListener listener) {
		// 共通参数
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD, UserParams2.LOGIN);
		// 业务参数:
		inputBean.putQueryParam(UserParams2.ACCOUNT, userName);
		inputBean.putQueryParam(UserParams2.PASSWORD, password);

		InternetClient.post(ServiceUrlConstants.getApiHost(), inputBean,
				UserResponse.class, new HttpResponseListener<UserResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UserResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/** 添加购物车 */
	public void regist(String mobile, String password, String verifyCode,
			String gender, String nickname,
			final OnLoginCompletedListener listener) {
		// 共通参数
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD, UserParams2.REGIST);
		// 业务参数:
		inputBean.putQueryParam(UserParams2.MOBILE, mobile);
		inputBean.putQueryParam(UserParams2.PASSWORD, password);
		inputBean.putQueryParam(UserParams2.VERIFY_CODE, verifyCode);
		inputBean.putQueryParam(UserParams2.GEMDER, gender);
		inputBean.putQueryParam(UserParams2.NICK_NAME, nickname);

		InternetClient.post(ServiceUrlConstants.getApiHost(), inputBean,
				UserResponse.class, new HttpResponseListener<UserResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UserResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/** 获取商品列表(通过关键字) */
	public void findBackPassword(String mobile, String password,
			String verifyCode, String gender, String nickname,
			final OnLoginCompletedListener listener) {
		// 共通参数
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				UserParams2.FINDBACK_PASSWORD);
		// 业务参数:
		inputBean.putQueryParam(UserParams2.MOBILE, mobile);
		inputBean.putQueryParam(UserParams2.PASSWORD, password);
		inputBean.putQueryParam(UserParams2.VERIFY_CODE, verifyCode);
		inputBean.putQueryParam(UserParams2.GEMDER, gender);
		inputBean.putQueryParam(UserParams2.NICK_NAME, nickname);

		InternetClient.post(ServiceUrlConstants.getApiHost(), inputBean,
				UserResponse.class, new HttpResponseListener<UserResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UserResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});

	}

	/** 获取验证码 */
	public void getVerifyCode(String mobile,
			final OnGetVerifyCodeCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams2.GET_VERIFY_CODE);
		// 业务参数:
		maps.put(UserParams2.MOBILE, mobile);

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, VerifyResponse.class,
				new HttpResponseListener<VerifyResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(VerifyResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/***/
	public void getPayInfo(String orderId, String payType,
			final OnGetPayInfoCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams2.GET_PAY_INFO);
		// 业务参数:
		maps.put(UserParams2.ORDER_ID, orderId);
		maps.put(UserParams2.PAY_TYPE, payType);

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, PayInfoResponse.class,
				new HttpResponseListener<PayInfoResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(PayInfoResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/**
	 * 我的帖子列表
	 * 
	 * @param userId
	 * @param pageCount
	 * @param listener
	 */
	public void getMyTopicList(String userId, String pageCount,
			final OnGetMyTopicListCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams2.GET_MY_TOPIC_LIST);
		// 业务参数:
		maps.put(UserParams2.USER_ID, userId);
		maps.put(UserParams2.PAGE_COUNT, pageCount);

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, MyTopicResponse.class,
				new HttpResponseListener<MyTopicResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(MyTopicResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/**
	 * 
	 * @param userId
	 * @param pageCount
	 * @param status
	 * @param listener
	 */
	public void getMyCouponList(String userId, String pageCount, String status,
			final OnGetMyCouponListCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams2.GET_MY_COUPON_LIST);
		// 业务参数:
		maps.put(UserParams2.USER_ID, userId);
		maps.put(UserParams2.PAGE_COUNT, pageCount);
		maps.put(UserParams2.STATUS, status);

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, CouponResponse.class,
				new HttpResponseListener<CouponResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(CouponResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/**
	 * 
	 * @param userId
	 * @param pageCount
	 * @param status
	 * @param listener
	 */
	public void getCommentListByUserId(String userId, String skuId,
			String pageCount, final OnGetCommentListCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD,
				OrderParams2.GET_MY_EVALUATION_LIST);
		// 业务参数:
		maps.put(OrderParams2.USER_ID, userId);
		maps.put(OrderParams2.SKU_ID, skuId);
		maps.put(OrderParams2.PAGE_COUNT, pageCount);

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, EvaluationResponse.class,
				new HttpResponseListener<EvaluationResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(EvaluationResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/** 获取用户信息 */
	public void getUserInfo(final OnLoginCompletedListener listener) {
		// 共通参数
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams2.GET_USER_INFO);
		// 业务参数:
		maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());

		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, UserResponse.class,
				new HttpResponseListener<UserResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UserResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/** 更新用户信息 */
	public void updateUserInfo(String nick, String age, String sex,
			String love, String marrage, String location,
			final OnLoginCompletedListener listener) {
		// 共通参数
		InputBean input = new InputBean();
		input.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		input.putQueryParam(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		input.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		input.putQueryParam(ServiceUrlConstants.MOTHOD,
				UserParams2.SAVE_USER_INFO);
		// 业务参数:
		input.putQueryParam(UserParams2.USER_ID, AgentApplication.get()
				.getUserId());
		input.putQueryParam(UserParams2.NICK_NAME, nick);
		input.putQueryParam(UserParams2.GEMDER, sex);
		input.putQueryParam(UserParams2.SEX_ORIENTATION, love);
		input.putQueryParam(UserParams2.MARRAGE_STATUS, marrage);
		// maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());
		// maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());
		// maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());

		InternetClient.post(ServiceUrlConstants.getApiHost(), input,
				UserResponse.class, new HttpResponseListener<UserResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UserResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/** 绑定手机 */
	public void bindMobile(String mobile, String code,
			final OnLoginCompletedListener listener) {
		// 共通参数
		InputBean input = new InputBean();
		input.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		input.putQueryParam(ServiceUrlConstants.APP_SECRET_NAME,
				ServiceUrlConstants.APP_SECRET_VALUE);
		input.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);
		input.putQueryParam(ServiceUrlConstants.MOTHOD, UserParams2.BIND_MOBILE);
		// 业务参数:
		input.putQueryParam(UserParams2.USER_ID, AgentApplication.get()
				.getUserId());
		input.putQueryParam(UserParams2.MOBILE, mobile);
		input.putQueryParam(UserParams2.VERIFY_CODE, code);

		// maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());
		// maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());
		// maps.put(UserParams2.USER_ID, AgentApplication.get().getUserId());

		InternetClient.post(ServiceUrlConstants.getApiHost(), input,
				UserResponse.class, new HttpResponseListener<UserResponse>() {

					@Override
					public void onStart() {
						listener.onStart();
					}

					@Override
					public void onSuccess(UserResponse response) {
						listener.onSuccess(response);
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
						ResponseException exception = new ResponseException(
								throwable);
						exception.setResultMsg("请求失败");
						listener.onFailed(exception);
					}

					@Override
					public void onFinish() {

					}
				});
	}

	/** 获取购物车数据回调 */
	public static interface OnLoginCompletedListener {

		void onSuccess(UserResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/** 获取商品列表数据回调 */
	public static interface OnGetPayInfoCompletedListener {

		void onSuccess(PayInfoResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/** 结算数据回调 */
	public static interface OnGetMyTopicListCompletedListener {

		void onSuccess(MyTopicResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/** 获取优惠券列表回调 */
	public static interface OnGetMyCouponListCompletedListener {

		void onSuccess(CouponResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/** 获取优惠券列表回调 */
	public static interface OnGetVerifyCodeCompletedListener {

		void onSuccess(VerifyResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

	/** 结算数据回调 */
	public static interface OnGetCommentListCompletedListener {

		void onSuccess(EvaluationResponse info);

		void onFailed(ResponseException e);

		void onHttpException(HttpResponseException e);

		void onStart();

		void onFinish();
	}

}
