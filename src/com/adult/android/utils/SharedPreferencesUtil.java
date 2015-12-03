package com.adult.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.adult.android.presenter.AgentApplication;

public class SharedPreferencesUtil {

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param value
	 * @return 是否保存成功
	 */
	public static boolean setSharedPreferences(String sharedPreferencesFile,
			String key, boolean value) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param defaultValue
	 * @return value
	 */
	public static boolean getSharedPreferences(String sharedPreferencesFile,
			String key, boolean defaultValue) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param value
	 * @return 是否保存成功
	 */
	public static boolean setSharedPreferences(String sharedPreferencesFile,
			String key, float value) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.edit().putFloat(key, value).commit();
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param defaultValue
	 * @return value
	 */
	public static float getSharedPreferences(String sharedPreferencesFile,
			String key, float defaultValue) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.getFloat(key, defaultValue);
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param value
	 * @return 是否保存成功
	 */
	public static boolean setSharedPreferences(String sharedPreferencesFile,
			String key, int value) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.edit().putInt(key, value).commit();
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param defaultValue
	 * @return value
	 */
	public static int getSharedPreferences(String sharedPreferencesFile,
			String key, int defaultValue) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.getInt(key, defaultValue);
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param value
	 * @return 是否保存成功
	 */
	public static boolean setSharedPreferences(String sharedPreferencesFile,
			String key, long value) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.edit().putLong(key, value).commit();
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param defaultValue
	 * @return value
	 */
	public static long getSharedPreferences(String sharedPreferencesFile,
			String key, long defaultValue) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.getLong(key, defaultValue);
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param value
	 * @return 是否保存成功
	 */
	public static boolean setSharedPreferences(String sharedPreferencesFile,
			String key, String value) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.edit().putString(key, value).commit();
	}

	/**
	 * @param sharedPreferencesFile
	 * @param key
	 * @param defaultValue
	 * @return value
	 */
	public static String getSharedPreferences(String sharedPreferencesFile,
			String key, String defaultValue) {
		SharedPreferences sp = AgentApplication.get().getSharedPreferences(
				sharedPreferencesFile, Context.MODE_PRIVATE);
		return sp.getString(key, defaultValue);
	}

}
