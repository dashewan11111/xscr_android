package com.adult.android.logic;

import java.io.IOException;

import android.text.TextUtils;

import com.adult.android.entity.UserDto;
import com.adult.android.entity.UserInfo;
import com.adult.android.model.constants.SharedPreferencesConstants.FILES;
import com.adult.android.model.constants.SharedPreferencesConstants.PARAMS;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.JsonUtils;
import com.adult.android.utils.SharedPreferencesUtil;

/**
 * 
 * @author GongXun
 * 
 * @2015年3月20日
 * 
 * 
 *             个人信息处理
 */
public class UserLogic {
	public UserInfo getUserBean() {
		String userString = SharedPreferencesUtil.getSharedPreferences(
				FILES.XSCR_USER, PARAMS.USER_INFO, "");
		UserInfo userInfo = null;
		if (!TextUtils.isEmpty(userString) && !"".equals(userString)) {
			try {
				userInfo = JsonUtils.parse(userString, UserInfo.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userInfo;
	}

	public void setUserBean(UserInfo userBean) {
		boolean isLogin = userBean == null ? false : true;
		setLogin(isLogin);
		// 清空本地缓存
		if (userBean == null) {
			setSession("");
			SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
					PARAMS.USER_INFO, "");
			setLogin(false);
		} else {
			String userString = null;
			try {
				userString = JsonUtils.generate(userBean);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
					PARAMS.USER_INFO, userString);
		}
	}

	private static UserLogic instance = null;

	public static synchronized UserLogic getInsatnace() {
		return instance = instance == null ? new UserLogic() : instance;
	}

	private UserLogic() {
	}

	/**
	 * 
	 * 2015年3月27日
	 * 
	 * @return 用户是否登录 （是否有个人信息）
	 */
	public boolean getIsLogin() {
		return SharedPreferencesUtil.getSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_LOGIN_STATUS, false);
	}

	public String getSession() {
		return SharedPreferencesUtil.getSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_SESSION_ID, "");
	}

	public void setSession(String session) {
		SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_SESSION_ID, session);
	}

	public void setLogin(boolean isLogin) {
		SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_LOGIN_STATUS, isLogin);
	}

	/** 保存用户名 */
	public void saveUserName(String userName) {
		SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_NAME, userName);
	}

	/** 保存密码 */
	public void savePassword(String password) {
		SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
				PARAMS.PASSWORD, password);
	}

	/** 获取用户名 */
	public String getUserName() {
		return SharedPreferencesUtil.getSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_NAME, "");
	}

	/** 获取密码 */
	public String getPassword() {
		return SharedPreferencesUtil.getSharedPreferences(FILES.XSCR_USER,
				PARAMS.PASSWORD, "");
	}

	/** 保存用户信息 */
	public void setUserInfo(UserDto userInfo) {
		String userString = null;
		try {
			userString = JsonUtils.generate(userInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SharedPreferencesUtil.setSharedPreferences(FILES.XSCR_USER,
				PARAMS.USER_INFO, userString);
		saveUserName(userInfo.getLoginName());
		savePassword(userInfo.getPassword());
		AgentApplication.get().setUserInfo(userInfo);
		AgentApplication.get().setUserId(userInfo.getcUserId());
	}

	/** 获取用户信息 */
	public UserDto getUserInfo() {
		String userString = SharedPreferencesUtil.getSharedPreferences(
				FILES.XSCR_USER, PARAMS.USER_INFO, "");
		UserDto userInfo = null;
		if (!TextUtils.isEmpty(userString) && !"".equals(userString)) {
			try {
				userInfo = JsonUtils.parse(userString, UserDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userInfo;
	}
}
