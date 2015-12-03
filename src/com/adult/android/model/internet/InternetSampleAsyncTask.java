package com.adult.android.model.internet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.adult.android.R;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpSampleResponseListener;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.JsonUtils;
import com.alibaba.fastjson.JSONException;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by JingYuchuan on 2015/6/8.
 */
public class InternetSampleAsyncTask<U extends InputBean, V extends Object>  extends AsyncTask<Object, Integer, Object[]> {
    private HttpSampleResponseListener<V> internetResponseListener;
    private Class<V> vClass;

    public InternetSampleAsyncTask(Class<V> vClass, HttpSampleResponseListener<V> internetResponseListener) {
        super();
        this.internetResponseListener = internetResponseListener;
        this.vClass = vClass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        internetResponseListener.onStart();
    }

    @Override
    protected Object[] doInBackground(Object... params) {

        Object[] objects = new Object[2];
        String url = (String) params[0];
        U inputBean = (U) params[1];
        OkHttpClient client = (OkHttpClient) params[2];

        Headers headers = getHeaders(inputBean);
        Request.Builder requestBuilder = new Request.Builder();
        if (headers != null) {
            requestBuilder.headers(headers);
        }
        if (inputBean.getRequestMethod() == InputBean.RequestMethod.POST) {
            RequestBody formBody = createRequestFormBodyByInputBean(inputBean);
            Log.i("InternetClient", "post request url:\n" + url);
            if (inputBean != null && inputBean.getQueryParams() != null) {
                Log.i("InternetClient", "post request params:\n" + inputBean.getQueryParams().toString());
            }
            if (formBody != null) {
                requestBuilder.post(formBody);
            }
        } else {
            url = creatGetUrlByQueryParams(url, inputBean.getQueryParams());
            Log.i("InternetClient", "get request url:"+Thread.currentThread().getName()+"\n" + url);
        }
        if (inputBean.isCacheable()) {
            requestBuilder.cacheControl(new CacheControl.Builder().maxAge(InternetClient.MAX_AGE, TimeUnit.SECONDS).build());
        } else {
            requestBuilder.cacheControl(CacheControl.FORCE_NETWORK);
        }
        Request request = requestBuilder
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response == null || !response.isSuccessful()) {
                Log.e("InternetClient", "exception:\n" + response);
                int responseCode = response == null ? 0 : response.code();
                objects[0] = responseCode;
                objects[1] = "网络请求失败";
                return objects;
            } else {
                String responseString = response.body().string();
                objects[0] = response.code();
                objects[1] = responseString;
                return objects;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String creatGetUrlByQueryParams(String url, Map<String, Object> params) {
        StringBuilder result = new StringBuilder("");
        if (TextUtils.isEmpty(url)) {
            return result.toString();
        } else {
            if (!url.contains("?")) {
                if (url.endsWith("/")) {
                    result.append(url.substring(0, url.length() - 1)).append("?");
                } else {
                    result.append(url).append("?");
                }
            } else {
                if (url.endsWith("?") || url.endsWith("&")) {
                    result.append(url);
                } else {
                    result.append(url).append("&");
                }
            }
            return result.append(getParamsUrl(params)).toString();
        }
    }

    private String getParamsUrl(Map<String, Object> params) {
        StringBuilder requestUrl = new StringBuilder("");
        String joinChar = "";
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            requestUrl.append(joinChar);
            requestUrl.append(entry.getKey());
            requestUrl.append("=");
            requestUrl.append(entry.getValue());
            joinChar = "&";
        }
        return requestUrl.toString();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object[] objects) {
        if (objects == null) {
            Log.e("InternetClient", "response, statusCode:" + 0);
            HttpResponseException e = new HttpResponseException(new IOException(AgentApplication.get().getResources().getString(R.string.request_error_text)));
            e.setResultMsg(AgentApplication.get().getResources().getString(R.string.request_error_text));
            e.setResultCode(0 + "");
            internetResponseListener.onHttpException(e);
        } else if ((int)objects[0] < 200 || (int)objects[0] >= 300) {
            Log.e("InternetClient", "response, statusCode:" + objects[0]);
            HttpResponseException e = new HttpResponseException(new IOException(AgentApplication.get().getResources().getString(R.string.request_error_text)));
            e.setResultMsg(AgentApplication.get().getResources().getString(R.string.request_error_text));
            e.setResultCode(objects[0] + "");
            internetResponseListener.onHttpException(e);
        }else {
            Log.i("InternetClient", "response:" + objects[1]);
            try {
                final Object respose = JsonUtils.parse((String) objects[1], vClass);
                internetResponseListener.onSuccess((V) respose);
            } catch (IOException e) {
                e.printStackTrace();
                internetResponseListener.onOtherException(e);
            } catch (JSONException e) {
                e.printStackTrace();
                internetResponseListener.onOtherException(e);
            } catch (Exception e) {
                e.printStackTrace();
                internetResponseListener.onOtherException(e);
            }
        }
        internetResponseListener.onFinish();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        internetResponseListener.onFinish();
    }

    private static Headers getHeaders(InputBean inputBean) {
        Headers.Builder builder = new Headers.Builder();
        if (inputBean != null && inputBean.getHeaders() != null) {
            Map<String, String> headerMap = inputBean.getHeaders();
            if (headerMap.size() == 0) {
                return null;
            }
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            return builder.build();
        } else {
            return null;
        }
    }

    private static RequestBody createRequestFormBodyByInputBean(
            InputBean inputBean) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        if (inputBean != null && inputBean.getQueryParams().size() > 0) {
            for (Map.Entry<String, Object> entry : inputBean.getQueryParams()
                    .entrySet()) {
                formEncodingBuilder.add(entry.getKey(), entry.getValue() + "");
            }
            return formEncodingBuilder.build();
        } else {
            return null;
        }
    }
}
