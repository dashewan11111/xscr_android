package com.adult.android.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;
import android.util.Log;

import com.adult.android.R;
import com.adult.android.entity.LoginResponse;
import com.adult.android.entity.ReceiveAreaResponse;
import com.adult.android.entity.UserBaseInfo;
import com.adult.android.entity.UserInfoResponse;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.PostParamsFactory;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.bean.StatusInfo;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.model.internet.listener.UserBussListener;
import com.adult.android.model.listener.SampleModelListener;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.ActionTool;
import com.adult.android.utils.CopUtils;
import com.adult.android.utils.LogUtil;
import com.adult.android.utils.crypto.AESUtil;

/**
 * @author GongXun
 * @2015年3月26日
 * @descripte 消息回传 处理
 */
public class UserActionModel {
	/**
	 * 2015年3月26日
	 *
	 * @param url
	 *            请求 路径（处理好的参数）
	 * @param listener
	 *            消息回传
	 * @param action
	 *            事件指令
	 *            <p/>
	 *            发送 get请求
	 */
	public static void sendGetAction(final String url,
			final UserBussListener listener, final UserAction action) {
		InputBean inputBean = new InputBean();
		Log.i("UserActionModel", "sendGetAction Url=" + url);
		InternetClient.get(url, inputBean, UserBaseInfo.class,
				new HttpResponseListener<UserBaseInfo>() {
					@Override
					public void onStart() {
						listener.onStartTask();
					}

					@Override
					public void onSuccess(UserBaseInfo t) {
						listener.onSuccess(action, t);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(action, e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFaile(action, e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						listener.onOtherException(action, throwable);

					}

					@Override
					public void onFinish() {
						listener.onFinishTask();
					}
				});
	}

	/**
	 *
	 * 2015年3月26日
	 *
	 * @param url
	 *            请求 路径（处理好的参数）
	 * @param listener
	 *
	 * @param action
	 *            事件指令
	 *
	 *            发送 Post请求
	 */
	/**
	 * 2015年4月3日
	 *
	 * @param listener
	 *            消息回传
	 * @param action
	 *            指令
	 * @param inputBean
	 *            参数处理 处理中文乱码
	 */
	public static void sendPostAction(final UserBussListener listener,
			final UserAction action, InputBean inputBean) {
		Log.i("UserActionModel", "sendPostAction");
		InternetClient.post(UserParams.getApiHost(), inputBean,
				UserBaseInfo.class, new HttpResponseListener<UserBaseInfo>() {
					@Override
					public void onStart() {
						listener.onStartTask();
					}

					@Override
					public void onSuccess(UserBaseInfo t) {
						LogUtil.Show("UserActionModel.onSuccess", t.toString());
						listener.onSuccess(action, t);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						LogUtil.Show("UserActionModel.onHttpException",
								e.toString());
						listener.onHttpException(action, e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						LogUtil.Show("UserActionModel.onBusinessException",
								e.toString());
						listener.onFaile(action, e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						LogUtil.Show("UserActionModel.onOtherException", "系统过热");
						listener.onOtherException(action, throwable);
					}

					@Override
					public void onFinish() {
						listener.onFinishTask();
					}
				});
	}

	/**
	 * 2015年3月26日
	 *
	 * @param url
	 *            请求 路径（处理好的参数）
	 * @param listener
	 *            消息回传
	 * @param action
	 *            事件指令
	 *            <p/>
	 *            发送 Post请求
	 */
	public static void sendPostAction(final String url,
			final UserBussListener listener, final UserAction action) {
		InputBean inputBean = new InputBean();
		InternetClient.post(url, inputBean, UserBaseInfo.class,
				new HttpResponseListener<UserBaseInfo>() {
					@Override
					public void onStart() {
						listener.onStartTask();
					}

					@Override
					public void onSuccess(UserBaseInfo t) {
						listener.onSuccess(action, t);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onHttpException(action, e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onFaile(action, e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						listener.onOtherException(action, throwable);
					}

					@Override
					public void onFinish() {
						listener.onFinishTask();
					}
				});
	}

	public static void login(String name, String psd,
			final SampleModelListener<Object> listener) {
		String password = "";
		try {
			password = URLEncoder.encode(AESUtil.encrypt(psd), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> maps = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.login, UserParams.AC,
				name);
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				maps);
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(UserParams.PWD, password);
		InternetClient.post(url, inputBean, LoginResponse.class,
				new HttpResponseListener<LoginResponse>() {
					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(LoginResponse t) {
						listener.onRequestSuccess(t.getData());

					}

					@Override
					public void onHttpException(HttpResponseException e) {
						e.setResultMsg(AgentApplication.get().getResources()
								.getString(R.string.request_error_text));
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("登录失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	/**
	 * 验证旧手机验证码请求
	 *
	 * @param mb
	 * @param vd
	 * @param listener
	 */
	public static void oldmobileValidate(String mb, String vd,
			final SampleModelListener<Object> listener) {
		InputBean inputBean = PostParamsFactory.createSignInputBean(true, "mb",
				mb, "uid", UserLogic.getInsatnace().getUserBean().getUserId(),
				ServiceUrlConstants.MOTHOD, "user.oldmobile.validate");

		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				"user.oldmobile.validate");
		inputBean.putQueryParam("uid", UserLogic.getInsatnace().getUserBean()
				.getUserId());
		inputBean.putQueryParam("mb", mb);
		inputBean.putQueryParam("vd", vd);
		inputBean.putQueryParam(ServiceUrlConstants.MOTHOD,
				"user.oldmobile.validate");
		InternetClient.post(UserParams.getApiHost(), inputBean,
				StatusInfo.class, new HttpResponseListener<StatusInfo>() {
					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						e.setResultMsg(AgentApplication.get().getResources()
								.getString(R.string.request_error_text));
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("请求失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	/**
	 * 验证新手机验证码请求
	 *
	 * @param mb
	 * @param vd
	 * @param listener
	 */
	public static void newmobileValidate(String mb, String vd,
			final SampleModelListener<Object> listener) {

		InputBean inputBean = PostParamsFactory.createSignInputBean(true, "mb",
				mb, ServiceUrlConstants.MOTHOD, "user.mobile.edit");

		inputBean.putQueryParam("method", "user.mobile.edit");
		inputBean.putQueryParam("uid", UserLogic.getInsatnace().getUserBean()
				.getUserId());
		inputBean.putQueryParam("mb", mb);
		inputBean.putQueryParam("vd", vd);

		InternetClient.post(UserParams.getApiHost(), inputBean,
				StatusInfo.class, new HttpResponseListener<StatusInfo>() {
					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						e.setResultMsg(AgentApplication.get().getResources()
								.getString(R.string.request_error_text));
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg(AgentApplication.get().getResources()
								.getString(R.string.request_error_text));
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	public static void registerFast(String phone, String code, String psd,
			final SampleModelListener<Object> listener) {
		String password = "";
		try {
			password = URLEncoder.encode(AESUtil.encrypt(psd), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams.quickRegister);
		maps.put(UserParams.MB, phone);
		maps.put(UserParams.RT, "1");
		String sign = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sign);
		maps.put(UserParams.PWD, code);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(UserParams.PASSWORD, password);
		InternetClient.post(url, inputBean, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("注册失败，请重试");
						listener.onRequestFail(re);

					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void registerCommon(String name, String mobile, String psd,
			String email, String code,
			final SampleModelListener<Object> listener) {
		String password = "";
		try {
			password = URLEncoder.encode(AESUtil.encrypt(psd), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 添加 签名
		InputBean inputBean = PostParamsFactory.createSignInputBean(false,
				UserParams.UM, name, UserParams.MB, mobile,
				ServiceUrlConstants.MOTHOD, UserParams.register, UserParams.RT,
				"1");
		if (!TextUtils.isEmpty(email)) {
			inputBean = PostParamsFactory.createSignInputBean(false,
					UserParams.UM, name, UserParams.MB, mobile, UserParams.EL,
					email, ServiceUrlConstants.MOTHOD, UserParams.register,
					UserParams.RT, "1");
			inputBean.putQueryParam(UserParams.EL, email);
		}
		inputBean
				.putQueryParam(ServiceUrlConstants.MOTHOD, UserParams.register);
		inputBean.putQueryParam(UserParams.UM, name);
		inputBean.putQueryParam(UserParams.MB, mobile);
		inputBean.putQueryParam(UserParams.PWD, password);
		inputBean.putQueryParam(UserParams.VD, code);
		inputBean.putQueryParam(UserParams.RT, "1");

		InternetClient.post(UserParams.getApiHost(), inputBean,
				StatusInfo.class, new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("注册失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void getFastRegisterCode(String phone,
			final SampleModelListener<Object> listener) {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		maps.put(ServiceUrlConstants.MOTHOD, UserParams.defaultpwd);
		maps.put(UserParams.MB, phone);
		String sign = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sign);
		String url = CopUtils.buildGetUrl(maps,
				ServiceUrlConstants.getApiHost());
		InternetClient.get(url, null, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("注册失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void getCommonRegisterCode(String mobile,
			final SampleModelListener<Object> listener) {

		Map<String, String> maps = ActionTool.getActionSignParams(
				UserParams.MB, mobile, ServiceUrlConstants.MOTHOD,
				UserParams.captcha);
		String url;
		url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(), maps);

		InternetClient.get(url, null, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("登录失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void getFindPwdCode(String phone,
			final SampleModelListener<Object> listener) {
		Map<String, String> maps = ActionTool
				.getActionSignParams(UserParams.MB, phone, UserParams.MOTHOD,
						UserParams.FIND_PWD_VD);
		String url = ActionTool.getActionUrl(UserParams.getApiHost(), maps);

		InternetClient.post(url, null, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取验证码失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void getModifyPwdCode(String userId, String phone,
			final SampleModelListener<Object> listener) {
		Map<String, String> datas = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.pwdeditCaptchaGet,
				UserParams.UID, userId, UserParams.MB, phone,
				UserParams.SESSIONID, UserLogic.getInsatnace().getSession());
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				datas);

		InternetClient.post(url, null, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取验证码失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void resetPwd(String phone, String pwd, String vd,
			final SampleModelListener<Object> listener) {
		Map<String, String> datas = ActionTool.getActionSignParams(
				UserParams.MB, phone, UserParams.MOTHOD, UserParams.FIND_PWD);
		String password = "";
		try {
			password = URLEncoder.encode(AESUtil.encrypt(pwd), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = ActionTool.getActionUrl(UserParams.getApiHost(), datas,
				UserParams.VD, vd);
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(UserParams.PWD, password);
		InternetClient.post(url, inputBean, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("重置密码失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void modifyLoginPwd(String userId, String phone,
			String oldPsd, String newPsd, String vd,
			final SampleModelListener<Object> listener) {
		Map<String, String> maps = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.pwdedit, UserParams.UID,
				userId, UserParams.MB, phone, UserParams.SESSIONID, UserLogic
						.getInsatnace().getSession());
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				maps, UserParams.VD, vd);

		String oldPassword = "";
		String newPassword = "";
		try {
			oldPassword = URLEncoder.encode(AESUtil.encrypt(oldPsd), "UTF-8");
			newPassword = URLEncoder.encode(AESUtil.encrypt(newPsd), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(UserParams.OPWD, oldPassword);
		inputBean.putQueryParam(UserParams.NPWD, newPassword);
		InternetClient.post(url, inputBean, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						e.setResultMsg(AgentApplication.get().getString(
								R.string.request_error_text));
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("修改密码失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void getUserInfo(String sessionId,
			final SampleModelListener<Object> listener) {
		// 执行获取 个人信息
		Map<String, String> maps = ActionTool.getActionSignParams(
				UserParams.SESSIONID, sessionId, ServiceUrlConstants.MOTHOD,
				UserParams.userGet);
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				maps);
		InternetClient.get(url, null, UserInfoResponse.class,
				new HttpResponseListener<UserInfoResponse>() {
					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(UserInfoResponse t) {
						listener.onRequestSuccess(t.getData());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("获取用户信息失败，请重试");
						listener.onRequestFail(re);

					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	public static void getMotifyUserInfoCode(String name, String mobile,
			final SampleModelListener<Object> listener) {

		Map<String, String> maps = ActionTool.getActionSignParams(
				UserParams.UM, name, UserParams.MB, mobile,
				ServiceUrlConstants.MOTHOD, UserParams.captcha);
		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				maps);

		InternetClient.get(url, null, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("登录失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void realNameApprove(String id, String name,
			final SampleModelListener<Object> listener) {
		Map<String, String> maps = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.userValidate,
				UserParams.UID, UserLogic.getInsatnace().getUserBean()
						.getUserId(), "cd", id, UserParams.SESSIONID, UserLogic
						.getInsatnace().getSession());
		String url = ActionTool.getActionUrl(UserParams.getApiHost(), maps);
		String userName = "";
		try {
			userName = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam("rm", userName);
		InternetClient.post(url, inputBean, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("实名认证失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	public static void userLogOut(final SampleModelListener<Object> listener) {
		Map<String, String> maps = ActionTool.getActionSignParams(
				UserParams.MOTHOD, UserParams.loginOut, UserParams.SESSIONID,
				UserLogic.getInsatnace().getSession());
		String url = ActionTool.getActionUrl(UserParams.getApiHost(), maps);
		InternetClient.post(url, null, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						e.setResultMsg(AgentApplication.get().getResources()
								.getString(R.string.request_error_text));
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("退出登录失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	public static void validateCode(String phone, String code,
			final SampleModelListener<Object> listener) {
		Map<String, String> datas = ActionTool.getActionSignParams(
				ServiceUrlConstants.MOTHOD, UserParams.pwdeditCaptchaValidate,
				UserParams.MB, phone, UserParams.SESSIONID, UserLogic
						.getInsatnace().getSession());

		String url = ActionTool.getActionUrl(ServiceUrlConstants.getApiHost(),
				datas);
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam(UserParams.VD, code);
		InternetClient.post(url, inputBean, StatusInfo.class,
				new HttpResponseListener<StatusInfo>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(StatusInfo t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("验证码检验失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});

	}

	/**
	 * 更新用户“配送地址”
	 *
	 * @param key
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @param areaName
	 * @param listener
	 */
	public static void setReceiveArea(String key, String provinceId,
			String cityId, String areaId, String address,
			final SampleModelListener<Object> listener) {
		Map<String, String> maps;
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps = ActionTool.getActionSignParams(ServiceUrlConstants.MOTHOD,
					UserParams.setReceiveArea, "userId", UserLogic
							.getInsatnace().getUserBean().getUserId(),
					"provinceId", provinceId, "cityId", cityId, "areaId",
					areaId, "areaName", address);
		} else {
			maps = ActionTool.getActionSignParams(ServiceUrlConstants.MOTHOD,
					UserParams.setReceiveArea, "key", key, "provinceId",
					provinceId, "cityId", cityId, "areaId", areaId, "areaName",
					address);
		}
		String url = ActionTool.getActionUrl(UserParams.getApiHost(), maps);

		InternetClient.get(url, null, ReceiveAreaResponse.class,
				new HttpResponseListener<ReceiveAreaResponse>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(ReceiveAreaResponse t) {
						listener.onRequestSuccess(t.getResult());
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("登录失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	/**
	 * 获取用户“配送地址”
	 *
	 * @param key
	 * @param listener
	 */
	public static void getReceiveArea(String key,
			final SampleModelListener<Object> listener) {
		Map<String, String> maps;
		if (UserLogic.getInsatnace().getIsLogin()) {
			maps = ActionTool.getActionSignParams(ServiceUrlConstants.MOTHOD,
					UserParams.getReceiveArea, "userId", UserLogic
							.getInsatnace().getUserBean().getUserId());
		} else {
			maps = ActionTool.getActionSignParams(ServiceUrlConstants.MOTHOD,
					UserParams.getReceiveArea, "key", key);
		}
		String url = ActionTool.getActionUrl(UserParams.getApiHost(), maps);

		InternetClient.get(url, null, ReceiveAreaResponse.class,
				new HttpResponseListener<ReceiveAreaResponse>() {

					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(ReceiveAreaResponse t) {
						listener.onRequestSuccess(t);
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onRequestFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onRequestFail(e);

					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("登录失败，请重试");
						listener.onRequestFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	/**
	 * @author zhaoweiChuang
	 * @2015年3月19日
	 * @descripte 个人业务模块指令
	 */
	public enum UserAction {
		/* ----------优惠圈指令--------- */
		ACTION_COUPON_NO_USE(0), ACTION_COUPON_OUT_TIME(0), ACTION_COUPON_USED(
				0),
		/* ---------------------------------- */
		ACTION_COMMON(0),
		/**
		 * 得到修改用户密码校验码
		 */
		ACTION_MODIFY_USER_PSD_CODE(0),
		/**
		 * 得到修改用户 密码
		 */
		ACTION_MODIFY_USER_PSD(0),
		/**
		 * 得到修改用户 信息 校验码
		 */
		ACTION_MODIFY_USER_INFO_CODE(0),
		/**
		 * 修改用户信息
		 */
		ACTION_MODIFY_USER_INFO(0),
		/**
		 * 得到个人信息
		 */
		ACTION_GET_USERINFO(0),
		/**
		 * 得到找回密码的验证码
		 */
		ACTION_FIND_PSD_VD(0),
		/**
		 * 重新设置密码
		 */
		ACTION_FIND_PSD(0),
		/**
		 * 登录
		 */
		// ACTION_LOGIN(0),

		ACTION_VALIDATE_NUMBER(0);

		public int value;

		private UserAction(int value) {
			this.value = value;
		}
	}

}
