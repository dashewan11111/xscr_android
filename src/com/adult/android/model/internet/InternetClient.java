package com.adult.android.model.internet;

import java.io.File;
import java.util.concurrent.TimeUnit;

import android.os.Environment;

import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.bean.StatusInfo;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.model.internet.listener.HttpSampleResponseListener;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;


/**
 * 网络请求主业务类。
 */
public class InternetClient {
    private static OkHttpClient client;
    public static final int MAX_AGE = 10 * 60;
    private static final String CacheDirectory = "com.adult.android"+File.separator+"Internet"+File.separator+"Cache";
    private static final int CacheSize = 10 * 1024 * 1024; // 10 MiB
    static {
        client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);


        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        if (sdDir != null && sdDir.exists()) {
            Cache cache = new Cache(new File(sdDir.getAbsolutePath(), CacheDirectory), CacheSize);
            client.setCache(cache);
        }
    }

    public static <V extends StatusInfo> void get(
            final String url, InputBean inputBean, final Class<V> vClass,
            final HttpResponseListener<V> internetResponseListener) {
        if (inputBean == null) {
            inputBean = new InputBean();
        }
        inputBean.setRequestMethod(InputBean.RequestMethod.GET);
        InternetAsyncTask asyncTask = new InternetAsyncTask(vClass,internetResponseListener);
        asyncTask.execute(url, inputBean, client);
    }

    public static <V extends Object> void getSample(
            final String url, InputBean inputBean, final Class<V> vClass,
            final HttpSampleResponseListener<V> internetResponseListener) {
        if (inputBean == null) {
            inputBean = new InputBean();
        }
        inputBean.setRequestMethod(InputBean.RequestMethod.GET);
        InternetSampleAsyncTask asyncTask = new InternetSampleAsyncTask(vClass,internetResponseListener);
        asyncTask.execute(url, inputBean, client);
    }

    public static <V extends StatusInfo> void post(
            String url, InputBean inputBean, final Class<V> vClass,
            final HttpResponseListener<V> internetResponseListener) {
        if (inputBean == null) {
            inputBean = new InputBean();
        }
        inputBean.setRequestMethod(InputBean.RequestMethod.POST);
        InternetAsyncTask asyncTask = new InternetAsyncTask(vClass,internetResponseListener);
        asyncTask.execute(url, inputBean, client);
    }
}
