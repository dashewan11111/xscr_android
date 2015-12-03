package com.adult.android.model.share;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.adult.android.R;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.ImageUtil;
import com.adult.android.utils.ToastUtil;
import com.ta.utdid2.android.utils.StringUtils;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by huangchao on 2015/6/8.
 * QQ分享到QQ空间
 */
public class ShareByQZone {

    private static ShareByQZone qZoneSingleton = null;

    private Tencent mTencent;

    private int shareType = QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT;

    private ShareByQZone(){}

    public static ShareByQZone getQZoneInstance(){
        if (qZoneSingleton == null){
            qZoneSingleton = new ShareByQZone();
        }

        return qZoneSingleton;
    }

    /**
     * QQ空间分享
     * @param context
     * @param productName 商品名称
     * @param productImageUrl 商品主图
     * @param productUrl 商品链接
     * @param productPrice 商品价格
     */
    public void qZoneShare(Activity context, String productName, String productImageUrl, String productUrl, String productPrice) {
        if (ShareByQQ.checkApkExist(context, ShareByQQ.TENCENT_QQ_PACKAGENAME_ANDROID)) {
            if (mTencent == null) {
                mTencent = Tencent.createInstance(ServiceUrlConstants.QQ_APP_ID, context);
            }
            Bundle params = new Bundle();

            String shareTitle = context.getResources().getString(R.string.share_title_before) + productPrice +
                    context.getResources().getString(R.string.share_title_after) + productName;

            ArrayList<String> productImageUrls = new ArrayList<String>();
            if(!StringUtils.isEmpty(productImageUrl)) {
                productImageUrls.add(productImageUrl);
            }

            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareTitle);
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, productUrl);
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, context.getResources().getString(R.string.share_content_desc));
            //该字段支持网络和本地图片。这点跟QQ好友分享不同，QQ空间QzoneShare.SHARE_TO_QQ_IMAGE_LOCAL_URL这个字段不支持。
            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, productImageUrls);
            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, shareType);

            doShareToQzone(context, params);


        } else {
            ToastUtil.showToastShort(context, R.string.qq_not_installed);
        }
    }


    /**
     * QQ空间分享
     * @param context
     * @param actUrl 活动url
     */
    public void qZoneActShare(Activity context, String actUrl) {
        if (ShareByQQ.checkApkExist(context, ShareByQQ.TENCENT_QQ_PACKAGENAME_ANDROID)) {
            if (mTencent == null) {
                mTencent = Tencent.createInstance(ServiceUrlConstants.QQ_APP_ID, context);
            }
            Bundle params = new Bundle();

            String shareTitle = context.getResources().getString(R.string.act_share_title);

            ImageUtil.generatorFileFromBitmap(context, ImageUtil.getCacheImgPath() + "share_ic_launcher.png",
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));

            ArrayList<String> actImageUrls = new ArrayList<String>();
            if(!StringUtils.isEmpty(ImageUtil.getCacheImgPath() + "share_ic_launcher.png")) {
                actImageUrls.add(ImageUtil.getCacheImgPath() + "share_ic_launcher.png");
            }

            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, shareTitle);
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, actUrl);
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, context.getResources().getString(R.string.act_share_context));
            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, actImageUrls);
            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, shareType);
            doShareToQzone(context, params);
        } else {
            ToastUtil.showToastShort(context, R.string.qq_not_installed);
        }

    }


    private void doShareToQzone(final Activity context, final Bundle params){
        mTencent.shareToQzone(context, params, qZoneShareListener);
    }


    IUiListener qZoneShareListener = new IUiListener() {

        @Override
        public void onCancel() {
            ToastUtil.showToastShort(AgentApplication.get(), R.string.share_cancel);
        }

        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub

            ToastUtil.showToastShort(AgentApplication.get(), R.string.share_error);
        }

        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub

            ToastUtil.showToastShort(AgentApplication.get(), R.string.share_success);
        }

    };
}
