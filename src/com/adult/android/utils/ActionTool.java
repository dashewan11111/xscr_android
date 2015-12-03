package com.adult.android.utils;

import java.util.HashMap;
import java.util.Map;

import com.adult.android.model.constants.ServiceUrlConstants;

/**
 * 
 * @author zhaoweiChuang
 * 
 * @2015年3月20日
 * 
 * @descripte
 * 
 *            action 数据处理
 */
public class ActionTool {
	/**
	 * 
	 * 2015年3月19日
	 * 
	 * @param singValues
	 *            要以键值对的方式出现 key value 做签名
	 * @return 得到签名的 参数集
	 */
	public static Map getActionSignParams(String... singValues) {
		Map<String, String> maps = new HashMap<String, String>();
		initBaseUrl(maps);
		if (singValues != null) {
			for (int i = 0; i < singValues.length; i += 2) {
				maps.put((String) singValues[i], singValues[i + 1]);
			}                        
		}
		String sing = CopUtils.sign(maps, ServiceUrlConstants.APP_SECRET);
		maps.put(ServiceUrlConstants.SIGN, sing);
		return maps;
	}

	/**
	 * 
	 * 2015年3月19日
	 * 
	 * @param singValues
	 *            要以键值对的方式出现 key value 不做签名参数
	 * @return 得到Action 的URL
	 */
	public static String getActionUrl(String url, Map<String, String> maps,
			String... value) {
		if (value != null) {
			for (int i = 0; i < value.length; i += 2) {
				maps.put((String) value[i], value[i + 1]);
			}
		}
		return CopUtils.buildGetUrl(maps, url);
	}
	/**
	 * 
	 * 2015年3月19日
	 * 
	 * @param maps
	 * 
	 * 
	 */
	private static void initBaseUrl(Map<String, String> maps) {
		maps.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		maps.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
	}

}
