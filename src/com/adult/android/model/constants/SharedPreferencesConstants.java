package com.adult.android.model.constants;

public class SharedPreferencesConstants {
	/**
	 * 此处定义所有的SharedPreferences文件名
	 */
	public static interface FILES {
		public static final String FILE_SETTING = "settings";
		/** 购物车 */
		public static final String FILE_SHOPPING_CART = "shopping_cart";
		public static final String FILE_USER = "xscr_user";
		public static final String XSCR_USER = "my_user";
		/** 新浪微博AccessToken文件名 **/
		public static final String SINA_TOKEN_SHARE_PREFS = "com_weibo_token_share_prefs";

		/** 搜索条件文件名 **/
		public static final String CCIGMALL_SEARCH_SHARE_PREFS = "xscr_search_share_prefs";

		/** 一直不能清除的常量 **/
		public static final String CCIGMALL_NEVER_CLEAR_PREFS = "xscr_global_constant_prefs";
	}

	/**
	 * 此处定义所有的SharedPreferences参数名
	 */
	public static interface PARAMS {
		public static final String USER_SESSION_ID = "xscr_sessionId";
		public static final String USER_INFO = "xscr_user_info";
		public static final String USER_LOGIN_REGISTER_SUCCESS_INFO = "xscr_user_login_register_success_info";
		public static final String USER_LOGIN_STATUS = "xscr_user_login_status";

		/** 新浪微博AccessToken参数 **/
		public static final String SINA_TOKEN_UID = "uid";
		public static final String SINA_TOKEN_ACCESS_TOKEN = "access_token";
		public static final String SINA_TOKEN_EXPIRES_TIME = "expires_time";
		public static final String SINA_TOKEN_REFRESH_TOKEN = "refresh_token";

		/** 搜索条件参数 **/
		public static final String CCIGMALL_SEARCH_BRAND = "ccigmall_brand";
		public static final String CCIGMALL_SEARCH_COUNTRY = "ccigmall_country";
		public static final String CCIGMALL_SEARCH_PRICE = "ccigmall_price";
		public static final String CCIGMALL_SEARCH_REPERTORY = "ccigmall_repertory";
		public static final String CCIGMALL_SEARCH_SUPPLY = "ccigmall_supply";

		/** 购物车参数 */
		public static final String CART_COUNT = "cart_count";
		/** 推送注册的JPushId **/
		public static final String CCIGMALL_JPUSH_REGISTER_ID = "jpush_register_id";
		/** 百度定位的省份 **/
		public static final String CCIGMALL_BAIDU_LOCATION_PROVINCE = "baidu_location_province";

		public static final String SDK_VERSION = "version";
		public static final String USER_NAME = "user_name";
		public static final String PASSWORD = "user_password";
	}

}
