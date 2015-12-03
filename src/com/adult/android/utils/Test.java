package com.adult.android.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {

	static{
		try {
			str= URLDecoder.decode("%E5%B0%8F%E6%B2%B9%E6%B2%B9", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		public Test() {
		super();
	}
		public static String str;

}
