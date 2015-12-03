package com.adult.android.model.internet.listener;

import org.json.JSONException;

import com.adult.android.entity.UserBaseInfo;
import com.adult.android.model.UserActionModel.UserAction;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;

/**
 * 
 * @author zhaoweiChuang
 * 
 * @2015年3月26日
 * 
 * @descripte
 * 
 *            个人业务模块消息回传
 */
public interface UserBussListener {

	public void onStartTask();

	/**
	 * @param action
	 *            事件指令 数据请求成功
	 * @throws JSONException
	 */
	public void onSuccess(UserAction action, UserBaseInfo baseInfo);

	/**
	 * @param action
	 *            事件指令 数据请求失败
	 */
	public void onFaile(UserAction action, BusinessException e);

	/**
	 * @param action
	 *            事件指令 网络请求异常
	 */
	public void onHttpException(UserAction action, HttpResponseException e);

	/**
	 * @param action
	 *            事件指令 网络请求异常
	 */
	public void onOtherException(UserAction action, Throwable e);

	public void onFinishTask();
}
