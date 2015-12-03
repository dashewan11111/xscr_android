package com.adult.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * 
 * @author zhaoweiChuang
 * 
 * @2015年3月30日
 * 
 * @descripte
 * 
 *            验证邮箱 和 身份证
 */
public class CheckCode {

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static Boolean checkEmail(String email) {
		if (TextUtils.isEmpty(email)) {
			return true;
		}
		String check = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+\\.([a-zA-Z0-9_-])+$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	// 判断身份证：要么是15位，要么是18位，最后一位可以为字母，并写程序提出其中的年月日。
	public static boolean isID(String idNum) {
		// 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
		Pattern idNumPattern = Pattern
				.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
		// 通过Pattern获得Matcher
		Matcher idNumMatcher = idNumPattern.matcher(idNum);
		// 判断用户输入是否为身份证号
		if (idNumMatcher.matches()) {
			return true;
		} else {
			// 如果不是，输出信息提示用户
			return false;
		}
	}

	/**
	 * 验证电话号码
	 * 
	 * @param 电话号码
	 * @return
	 */
	public static Boolean checkPhone(String phone) {
		String check = "13[0-35-9]\\d{8}|134[0-8]\\d{7}|14[79]\\d{8}|15[0-35-9]\\d{8}|17[68]\\d{8}|18\\d{9}";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(phone);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 2015年4月3日
	 * 
	 * @param name
	 * @return true 完整的判断中文汉字
	 */

	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	// public static boolean isContentsChinines(String name) {
	// String pattern = "[\u4e00-\u9fa5]";
	// Pattern p = Pattern.compile(pattern);
	// Matcher matcher = p.matcher(name);
	// if (matcher.find()) {
	// return true;
	// }
	// }

	public static boolean checkPsd(String psd) {

		String pattern = "[a-zA-Z0-9]{6,20}";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(psd);

		char[] psds = psd.toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean nosame = false;
		for (int i = 1; i < psds.length; i++) {
			if (!(psds[i] == psds[i - 1])) {
				nosame = true;
			}
		}
		if (matcher.find() && nosame) {
			return true;
		}
		return false;
	}

	public static boolean isChinisePlusEnglish(String receiverName) {
		String pattern = ".*[a-zA-Z]|.*[\u4e00-\u9fa5]|.*[a-zA-Z].*[\u4e00-\u9fa5]|.*[\u4e00-\u9fa5].*[a-zA-Z]|.*[a-zA-Z].*[\u4e00-\u9fa5].*\\.|.*[\u4e00-\u9fa5].*[a-zA-Z].*\\.|.*[\u4e00-\u9fa5].*\\..*[a-zA-Z]|.*[a-zA-Z].*\\..*[\u4e00-\u9fa5]";
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(receiverName);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean checkUserName(String name) {
		String pattern = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5]|[\\-]|[\\_])+";
		// String finder = "([a-z]|[A-Z]|[\\u4e00-\\u9fa5])+";
		// String finder = "[a-zA-Z\u4e00-\u9fa5]";
		// Pattern pf = Pattern.compile(finder);
		// Matcher matcherf = pf.matcher(name);
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(name);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

}
