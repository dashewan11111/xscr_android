package com.adult.android.model.internet.exception;
/**
 * http请求的异常。
 */
public class HttpResponseException extends ResponseException {
    public HttpResponseException() {
        super();
    }

    public HttpResponseException(Throwable throwable) {
        super(throwable);
    }
}
