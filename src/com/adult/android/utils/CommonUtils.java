/**
 *  Copyright(c) 2013 Hitachi All rights reserved.
 */
package com.adult.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.provider.MediaStore.MediaColumns;
import android.util.Base64;
import android.util.Log;

/**
 * Common Utilities。<br>
 */
public class CommonUtils {

	/** Get system date format */
	public static DateFormat getDateFormat(Context context) {
		return android.text.format.DateFormat.getDateFormat(context);
	}

	/** Get system time format */
	public static DateFormat getTimeFormat(Context context) {
		return android.text.format.DateFormat.getTimeFormat(context);
	}

	/** Check if array contains string */
	public static String match(String[] strings, String string) {
		if (strings != null && strings.length > 0) {
			for (String str : strings) {
				if (str != null && str.length() > 0) {
					if (str.startsWith(string)) {
						return str;
					}
				}
			}
		}
		return null;
	}

	/** Add string to array */
	public static String[] add(String[] strings, String string) {

		if (strings == null) {
			String[] localString = new String[1];
			localString[0] = string;
			return localString;
		}
		List<String> list = new ArrayList<String>(Arrays.asList(strings));
		list.add(string);
		return list.toArray(new String[list.size()]);
	}

	/** Remove string to array */
	public static String[] remove(String[] strings, String string) {
		List<String> list = new ArrayList<String>(Arrays.asList(strings));
		list.remove(string);
		return list.toArray(new String[list.size()]);
	}

	/** Get real path */
	public static String getRealImagePath(Activity activity, Uri uri) {
		if (uri.toString().startsWith("content")) {
			String[] proj = { MediaColumns.DATA };
			Cursor cursor = activity.managedQuery(uri, proj, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		} else if (uri.toString().startsWith("file")) {
			String tempUri = Uri.decode(uri.toString());
			return tempUri.substring(7, tempUri.length());
		} else {
			return "";
		}
	}

	/** Get file name */
	public static String getFileName(String path) {
		if (path != null && path.length() > 0) {
			int index = path.lastIndexOf("/");
			index = Math.max(path.lastIndexOf("\\"), index);
			if (index > 0) {
				return path.substring(index + 1);
			}
		}
		return path;
	}

	/** Kill process */
	public static void killProcess(Context context, String packageName) {
		int version = android.os.Build.VERSION.SDK_INT;
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		if (version <= 7) {
			manager.restartPackage(packageName);
		} else {
			List<RunningAppProcessInfo> process = manager
					.getRunningAppProcesses();
			int len = process.size();
			for (int i = 0; i < len; i++) {
				if (process.get(i).processName.equals(packageName)) {
					android.os.Process.killProcess(process.get(i).pid);
				}
			}
		}
	}

	/** Get file's extension name */
	public static String getExtensionName(String fileName) {
		if (fileName != null) {
			int index = fileName.lastIndexOf(".");
			if (index >= 0) {
				return fileName.substring(index + 1);
			}
			return "";
		}
		return null;
	}

	/** Get file's extension name */
	public static String getRandomFileName(String extension,
			String defaultExtension) {
		String fileName = UUID.randomUUID().toString();
		if (extension != null && extension.length() > 0) {
			fileName = fileName + "." + extension;
		} else if (defaultExtension != null && defaultExtension.length() > 0) {
			fileName = fileName + "." + defaultExtension;
		}
		return fileName;
	}

	/** Compare strings */
	public static boolean equals(String src, String dst) {
		if (src == null || src.length() == 0) {
			if (dst == null || dst.length() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return src.equals(dst);
		}
	}

	/** Get a arrangement of quotation name and quotation content */
	public static String[] splitQuotation(String str, String token) {
		String[] quoteArray = new String[2];
		if (str != null && str.length() > 0) {
			int index = str.indexOf(token);
			if (index >= 0) {
				quoteArray[0] = str.substring(0, index).trim();
				quoteArray[1] = str.substring(index + 1).trim();
			}
		}
		return quoteArray;
	}

	/**
	 * Get Declared Fields
	 */
	public static Field[] getDeclaredFields(Class<?> clazz) {
		Map<String, Object> fieldMap = getDeclaredFieldMap(clazz);
		return fieldMap.values().toArray(new Field[fieldMap.size()]);
	}

	/**
	 * Get Declared Fields
	 */
	protected static Map<String, Object> getDeclaredFieldMap(Class<?> clazz) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		if (clazz.getSuperclass() != null) {
			fieldMap.putAll(getDeclaredFieldMap(clazz.getSuperclass()));
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field);
		}
		return fieldMap;
	}

	/**
	 * stringtoBitmap
	 * 
	 * @param string
	 * @return bitmap
	 */
	public static Bitmap transportStringtoBitmap(String string) {
		// change String to Bitmap
		Bitmap bitmap = null;
		try {
			byte[] bitmapArray;
			bitmapArray = Base64.decode(string, Base64.DEFAULT);
			bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
					bitmapArray.length);
		} catch (Exception e) {
			Log.e("CommonUtils", "transportStringtoBitmap", e);
		}

		return bitmap;
	}

	/**
	 * bitmaptoString
	 * 
	 * @param bitmap
	 * @return string
	 */
	public static String transportBitmaptoString(Bitmap bitmap) {

		// change Bitmap to String
		String string = null;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 100, bStream);
		byte[] bytes = bStream.toByteArray();
		try {
			bStream.close();
		} catch (IOException e) {
			Log.e("CommonUtils", "transportStringtoBitmap", e);
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (IOException e) {
					Log.e("CommonUtils", "transportStringtoBitmap", e);
				}
			}
		}
		string = Base64.encodeToString(bytes, Base64.DEFAULT);
		return string;
	}

	/**
	 * set image's bottom corners to round<BR>
	 * 
	 * @author cuijk
	 * @param bitmap
	 * @param roundPx
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(0, -20, bitmap.getWidth(),
				bitmap.getHeight());

		final Paint paint = new Paint();
		int color = 0xff424242;
		paint.setColor(color);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * set image's bottom corners to round<BR>
	 * 
	 * @author zhuf
	 * @param bitmap
	 * @param roundPx
	 */
	public static Bitmap getRoundedAllCornerBitmap(Bitmap bitmap, float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(0, 0, bitmap.getWidth(),
				bitmap.getHeight());

		final Paint paint = new Paint();
		int color = 0xff424242;
		paint.setColor(color);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * is an application is running in front
	 * 
	 * @param context
	 * @return boolean true: is running in front ;false: is not running in
	 *         front;
	 */
	public static boolean isAppOnFront(Context context) {
		// return a list of application process that are running on the devices
		ActivityManager activityManager = (ActivityManager) context
				.getApplicationContext().getSystemService(
						Context.ACTIVITY_SERVICE);
		String packageName = context.getApplicationContext().getPackageName();
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (null == appProcesses) {
			return false;
		}
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	/**
	 * trim half-space & full-space
	 * 
	 * @param str
	 * @return
	 */
	public static String myTrim(String str) {
		String halfSpace = " ";
		String fullSpace = "　";

		if (null != str && 0 != str.length()) {
			String firstStr = String.valueOf(str.charAt(0));
			String lastStr = String.valueOf(str.charAt(str.length() - 1));

			if (!halfSpace.equals(firstStr) && !fullSpace.equals(firstStr)
					&& !halfSpace.equals(lastStr) && !fullSpace.equals(lastStr)) {
				return str;
			}
			if (halfSpace.equals(firstStr) || fullSpace.equals(firstStr)) {
				str = str.substring(1);
			}

			if (null != str && 0 != str.length()) {
				if (halfSpace.equals(lastStr) || fullSpace.equals(lastStr)) {
					str = str.substring(0, str.length() - 1);
				}
			}
		} else {
			return str;
		}
		return myTrim(str);
	}
}
