package com.adult.android.model.internet;

import java.util.HashMap;
import java.util.Map;

import com.adult.android.logic.UserLogic;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.constants.ServiceUrlConstants.UserParams;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.utils.CopUtils;

/**
 * @ClassName: PostParamsFactory
 * @Description
 * @author JingYuchuan
 * @date 2015年4月3日 下午1:48:34
 * 
 */
public class PostParamsFactory {
	public static InputBean createSignInputBean(boolean session,
			String... singValues) {
		InputBean inputBean = new InputBean();
		Map<String, String> map = new HashMap<String, String>();
		if (session) {
			map.put(UserParams.SESSIONID, UserLogic.getInsatnace().getSession());
			inputBean.putQueryParam(UserParams.SESSIONID, UserLogic
					.getInsatnace().getSession());
		}
		map.put(ServiceUrlConstants.APP_KEY, ServiceUrlConstants.APP_KEY_VALUE);
		map.put(ServiceUrlConstants.VERSION, ServiceUrlConstants.VERSION_VALUE);
		if (singValues != null) {
			for (int i = 0; i < singValues.length; i += 2) {
				map.put(singValues[i], singValues[i + 1]);
			}
		}
		String sign = CopUtils.sign(map, ServiceUrlConstants.APP_SECRET);
		inputBean.putQueryParam(ServiceUrlConstants.SIGN, sign);

		inputBean.putQueryParam(ServiceUrlConstants.APP_KEY,
				ServiceUrlConstants.APP_KEY_VALUE);
		inputBean.putQueryParam(ServiceUrlConstants.VERSION,
				ServiceUrlConstants.VERSION_VALUE);

		return inputBean;
	}
}
