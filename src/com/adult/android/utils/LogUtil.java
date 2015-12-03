package com.adult.android.utils;

import android.util.Log;

/**
 * 
 * @author zhaoweiChuang  
 * 
 * @descripte
 *         
 *         运行日志
 * @2015年1月7日
 */
public class LogUtil {
   static boolean IS_DEBUG=true;
   static String TAG="XinWang";
   static int i=0;
   public static void Show(String local,String value){
	   if(IS_DEBUG){
		   Log.d(TAG,local+"_"+i+++"\n"+value);
	   }
   }
   public static void Show(String local,int value){
	   if(IS_DEBUG){
		   Log.d(TAG,local+"_"+i+++"\n"+String.valueOf(value));
	   }
   }
}
