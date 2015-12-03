package com.adult.android.model.internet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.adult.android.R;
import com.adult.android.entity.Result;
import com.adult.android.logic.UserLogic;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.bean.StatusInfo;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.presenter.activity.LoginActivity2;
import com.adult.android.utils.JsonUtils;
import com.adult.android.utils.Misc;
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
public class InternetAsyncTask<U extends InputBean, V extends StatusInfo>
		extends AsyncTask<Object, Integer, Object[]> {
	private HttpResponseListener<V> internetResponseListener;
	private Class<V> vClass;

	public InternetAsyncTask(Class<V> vClass,
			HttpResponseListener<V> internetResponseListener) {
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

		com.squareup.okhttp.Headers headers = getHeaders(inputBean);
		Request.Builder requestBuilder = new Request.Builder();
		if (headers != null) {
			requestBuilder.headers(headers);
		}
		if (inputBean.getRequestMethod() == InputBean.RequestMethod.POST) {
			RequestBody formBody = createRequestFormBodyByInputBean(inputBean);
			Log.i("InternetClient", "post request url:\n" + url);
			if (inputBean != null && inputBean.getQueryParams() != null) {
				Log.i("InternetClient", "post request params:\n"
						+ inputBean.getQueryParams().toString());
			}
			if (formBody != null) {
				requestBuilder.post(formBody);
			}
		} else {
			url = creatGetUrlByQueryParams(url, inputBean.getQueryParams());
			Log.i("InternetClient", "get request url:"
					+ Thread.currentThread().getName() + "\n" + url);
		}
		if (inputBean.isCacheable()) {
			requestBuilder.cacheControl(new CacheControl.Builder().maxAge(
					InternetClient.MAX_AGE, TimeUnit.SECONDS).build());
		} else {
			requestBuilder.cacheControl(CacheControl.FORCE_NETWORK);
		}
		Request request = requestBuilder.url(url).build();
		try {
			Response response = client.newCall(request).execute();
			if (response == null || !response.isSuccessful()) {
				Log.e("InternetClient", "exception:\n" + response);
				int responseCode = response == null ? 0 : response.code();
				objects[0] = responseCode;
				objects[1] = AgentApplication.get().getResources()
						.getString(R.string.request_error_text);
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

	private String creatGetUrlByQueryParams(String url,
			Map<String, Object> params) {
		StringBuilder result = new StringBuilder("");
		if (TextUtils.isEmpty(url)) {
			return result.toString();
		} else {
			if (!url.contains("?")) {
				if (url.endsWith("/")) {
					result.append(url.substring(0, url.length() - 1)).append(
							"?");
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
			handleConnectionFailedResponse(0, new IOException(AgentApplication
					.get().getResources()
					.getString(R.string.request_error_text)),
					internetResponseListener);
		} else if ((int) objects[0] < 200 || (int) objects[0] >= 300) {
			handleConnectionFailedResponse((int) objects[0],
					new IOException(AgentApplication.get().getResources()
							.getString(R.string.request_error_text)),
					internetResponseListener);
		} else {
			handleConnectionSuccessResponse((String) objects[1], vClass,
					internetResponseListener);
		}
		internetResponseListener.onFinish();
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
		internetResponseListener.onFinish();
	}

	private static com.squareup.okhttp.Headers getHeaders(InputBean inputBean) {
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

	private static <V extends StatusInfo> void handleConnectionSuccessResponse(
			final String response, final Class<V> vClass,
			final HttpResponseListener<V> internetResponseListener) {

		Log.i("InternetClient", "response:" + response);
		try {
			final StatusInfo statusInfo = JsonUtils.parse(response, vClass);
			Result result = statusInfo.getResult();
			String stringResult = result.getResult();
			if ("1".equals(stringResult)) {
				internetResponseListener.onSuccess((V) statusInfo);
			} else if ("0".equals(stringResult)) {
				BusinessException be = new BusinessException();
				be.setResultCode(result.getResult());
				// 无效的 sessionId 参数
				if ("21".equals(result.getErrorCode())
						&& !Misc.isForeground(LoginActivity2.class)) {
					be.setResultMsg(AgentApplication.get().getResources()
							.getString(R.string.outdate_login_again));
					internetResponseListener.onBusinessException(be);

					UserLogic.getInsatnace().setUserBean(null);
					Intent intent = new Intent(AgentApplication.get(),
							LoginActivity2.class);
					intent.putExtra("isSessionOutDate", true);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					AgentApplication.get().startActivity(intent);
					AgentApplication.get().exitWithoutActivity(
							LoginActivity2.class);
				} else {
					be.setResultMsg(result.getMessage());
					internetResponseListener.onBusinessException(be);
				}
			} else {
				Throwable throwable = new Throwable(
						"unknown business exception");
				internetResponseListener.onOtherException(throwable);
			}
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

	private static <V extends StatusInfo> void handleConnectionFailedResponse(
			int statusCode, Throwable error,
			final HttpResponseListener<V> internetResponseListener) {
		Log.e("InternetClient", "response, statusCode:" + statusCode);
		HttpResponseException e;
		if (error != null) {
			e = new HttpResponseException(error);
		} else {
			e = new HttpResponseException();
		}
		e.setResultMsg(AgentApplication.get().getResources()
				.getString(R.string.request_error_text));
		e.setResultCode(statusCode + "");
		internetResponseListener.onHttpException(e);
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
