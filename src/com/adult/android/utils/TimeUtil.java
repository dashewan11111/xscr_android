package com.adult.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {
	/**
	 * 
	 * @param oldTime
	 * 
	 * @return
	 * 
	 *         根据接受的时间与当前时间匹配，返回需要的字符信息
	 */
	public static String getTimeStr(Date oldTime) {

		long time1 = new Date().getTime();

		long time2 = oldTime.getTime();

		long time = (time1 - time2) / 1000;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(oldTime);
	}

	/**
	 * 
	 * @param str
	 * @return 显示客户端时间 String
	 */
	public static String strToString(String str) {
		// sample：Tue May 31 17:46:55 +0800 2011
		// E：周 MMM：字符串形式的月，如果只有两个M，表示数值形式的月 Z表示时区（＋0800）
		SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy",
				Locale.US);
		Date result = null;
		try {
			result = sdf.parse(str);
		} catch (Exception e) {
		}
		return getTimeStr(result);
	}

    /**
     * 
     * 2015年4月3日
     * @param time
     *        毫秒
     * @return
     *       yyyy-MM-dd
     */
	public static String getTimeString(long time) {
		return getTimeStr(new Date(time));
	}
	public static long getTime(String time) {
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long mTime = 0;
		try {
			mTime = sdff.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mTime/1000;
	}
	/**
	 * 当日的日期字符串形式例如：20140327
	 * @return
	 */
	public static String getCurrentDate(){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		return sFormat.format(new Date());
	}
}
