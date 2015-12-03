package com.adult.android.model.setting;

import android.content.Context;

import com.adult.android.entity.VersionInfo;
import com.adult.android.entity.VersionInfoResponse;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.model.internet.InternetClient;
import com.adult.android.model.internet.bean.InputBean;
import com.adult.android.model.internet.exception.BusinessException;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.model.internet.listener.HttpResponseListener;
import com.adult.android.utils.Misc;

public class AppUpgradeModel {

	private String packageName;
	private String channelName;
	private int localVersion;

	/**
	 * 
	 * @param versionCode
	 *            本地版本号
	 * @param packageName
	 *            包名
	 * @param channelName
	 *            渠道名
	 */
	public AppUpgradeModel(Context context) {
		super();
		this.packageName = context.getPackageName();
		this.channelName = Misc.getChannelName(context);
		this.localVersion = Misc.getVersionCode(context);
	}

	public void checkUpgrade(final OnCheckVersionListener listener) {
		InputBean inputBean = new InputBean();
		inputBean.putQueryParam("packageName", packageName);// 初始化请求参数
		inputBean.putQueryParam("channelName", channelName);// 初始化请求参数

		InternetClient.get(ServiceUrlConstants.getUpdateHost(),
				inputBean, VersionInfoResponse.class,
				new HttpResponseListener<VersionInfoResponse>() {
					@Override
					public void onStart() {
						listener.onRequestStart();
					}

					@Override
					public void onSuccess(VersionInfoResponse t) {
						int netVersion = Integer.parseInt(t.getData()
								.getVersionCode());
						if (localVersion < netVersion) {
							listener.onNeedUpdateVerion(t.getData());
						} else {
							if (localVersion == netVersion) {
								listener.onNotNeedUpdateVersion(true);
							} else {
								listener.onNotNeedUpdateVersion(false);
							}
						}
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						listener.onCheckVersionFail(e);
					}

					@Override
					public void onBusinessException(BusinessException e) {
						listener.onCheckVersionFail(e);
					}

					@Override
					public void onOtherException(Throwable throwable) {
						ResponseException re = new ResponseException(throwable);
						re.setResultMsg("检查更新失败");
						listener.onCheckVersionFail(re);
					}

					@Override
					public void onFinish() {
						listener.onRequestFinish();
					}
				});
	}

	public static interface OnCheckVersionListener {
		void onRequestStart();

		void onRequestFinish();

		void onNeedUpdateVerion(final VersionInfo info);

		void onNotNeedUpdateVersion(boolean same);

		void onCheckVersionFail(final ResponseException e);
	}

	public static interface OnDownLoadApkListener {
		void onDownloadStart();

		void onDownloadSuccess();

		void onDownloadProgress(int bytesWritten, int totalSize);

		void onDownloadFinish();

		void onDownloadInstall();

		void onDownloadFailed(Exception e);
	}

}
