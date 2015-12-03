package com.adult.android.utils;

import java.lang.Thread.UncaughtExceptionHandler;

import android.util.Log;

import com.adult.android.presenter.AgentApplication;

/***
 * 程序异常捕获
 * 
 * @author LiCheng
 * 
 */
public class CatchHandler implements UncaughtExceptionHandler {

	public synchronized static CatchHandler getInstance() {
		if (null == mCatchHandler) {
			mCatchHandler = new CatchHandler();
		}
		return mCatchHandler;
	}

	private static CatchHandler mCatchHandler = new CatchHandler();

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("uncaughtException", "exception: " + ex);
		AgentApplication.get().exit();
	}

	public void init() {
		Thread.setDefaultUncaughtExceptionHandler(this);
	}
}
