package com.adult.android.utils;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

	public static <T> T parse(String json, Class<T> clazz) throws IOException {
		if (null == json || "".equals(json) || null == clazz) {
			return null;
		}
		return JSON.parseObject(json, clazz);
	}

	public static String generate(Object obj) throws IOException {
		if (null == obj) {
			return null;
		}
		return JSON.toJSONString(obj);
	}
}
