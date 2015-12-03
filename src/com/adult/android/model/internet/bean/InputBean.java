package com.adult.android.model.internet.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.protocol.HTTP;

/**
 * @ClassName: InputBean
 * @Description: 统一的http请求封装
 * @author JingYuchuan
 * @date 2015年3月23日 下午4:07:06
 * 
 */
public class InputBean {
	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(RequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}
	private boolean cacheable = false;

	public boolean isCacheable() {
		return cacheable;
	}

	public void setCacheable(boolean cacheable) {
		this.cacheable = cacheable;
	}

	public static enum RequestMethod {
		GET, POST, PUT, DELETE;
	}
	public final static String APPLICATION_JSON = "application/json";
	public final static String APPLICATION_OCTET_STREAM = "application/octet-stream";
	/**
	 * the content type of the payload you are sending, for example
	 * application/json if sending a json payload.
	 */
	public final static String STRING_ENTITY_CONTENT_TYPE = HTTP.UTF_8;
	private HttpEntity httpEntity;
	private RequestMethod requestMethod = RequestMethod.GET;
	private String contentType = APPLICATION_OCTET_STREAM;

	private String contentEncoding = HTTP.UTF_8;

	private Map<String, String> headers = new HashMap<String, String>();

	private Map<String, Object> queryParams = new HashMap<String, Object>();

	private Map<String, Object> formParams = new HashMap<String, Object>();

	public void putFormParams(String key, Object value) {
		formParams.put(key, value);
	}

	public Map<String, Object> getFormParams() {
		return formParams;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void putQueryParam(String key, Object value) {
		queryParams.put(key, value);
	}

	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public HttpEntity getHttpEntity() {
		return httpEntity;
	}

	public void setHttpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
	}

	public boolean isRefreshTokenTag() {
		return false;
	}

}
