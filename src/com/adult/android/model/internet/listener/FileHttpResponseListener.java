package com.adult.android.model.internet.listener;

import com.adult.android.model.internet.bean.StatusInfo;

public interface FileHttpResponseListener extends
		HttpResponseListener<StatusInfo> {
	public void onProgress(int bytesWritten, int totalSize);
}
