package com.adult.android.model.share;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.adult.android.R;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.ImageUtil;
import com.adult.android.utils.ToastUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by huangchao on 2015/6/8.
 * QQ分享给好友
 *
 */
public class ShareByQQ {

    private static ShareByQQ qqSingleton = null;
    /**分享类型**/
    private int shareType = QQShare.SHARE_TO_QQ_TYPE_DEFAULT;

    public static final String TENCENT_QQ_PACKAGENAME_ANDROID = "com.tencent.mobileqq";

    private Tencent mTencent;

    private ShareByQQ(){}

    public static ShareByQQ getQQInstance(){
        if(qqSingleton == null){
            qqSingleton = new ShareByQQ();
        }

        return qqSingleton;
    }

    /**
     * QQ分享给好友
     * @param context
     * @param productName 商品名称
     * @param productImageUrl 商品主图
     * @param productUrl 商品链接
     * @param productPrice 商品价格
     */
    public void qqShare(Activity context, String productName, String productImageUrl, String productUrl, String productPrice){
        if(checkApkExist(context, TENCENT_QQ_PACKAGENAME_ANDROID)){
            if(mTencent == null){
                mTencent = Tencent.createInstance(ServiceUrlConstants.QQ_APP_ID, context);
            }
            Bundle bundle = new Bundle();
            String shareTitle = context.getResources().getString(R.string.share_title_before) + productPrice +
                    context.getResources().getString(R.string.share_title_after) + productName;

            bundle.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
            bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, productUrl);
            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, context.getResources().getString(R.string.share_content_desc));
            bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, productImageUrl);
            bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, context.getString(R.string.app_name));
            bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);

            doShareToQQ(context, bundle);
        } else {
            ToastUtil.showToastShort(context, R.string.qq_not_installed);
        }
    }



    /**
     * 活动页QQ分享给好友
     * @param context
     * @param actUrl 活动url
     */
    public void qqActShare(Activity context, String actUrl){
        if(checkApkExist(context, TENCENT_QQ_PACKAGENAME_ANDROID)){
            if(mTencent == null){
                mTencent = Tencent.createInstance(ServiceUrlConstants.QQ_APP_ID, context);
            }
            ImageUtil.generatorFileFromBitmap(context, ImageUtil.getCacheImgPath() + "share_ic_launcher.png",
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));

            Bundle bundle = new Bundle();
            String shareTitle = context.getResources().getString(R.string.act_share_title);

            bundle.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
            bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, actUrl);
            bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, context.getResources().getString(R.string.act_share_context));
            bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, ImageUtil.getCacheImgPath() + "share_ic_launcher.png");
            //该字段支持网络和本地图片，QzoneShare.SHARE_TO_QQ_IMAGE_LOCAL_URL这个字段也支持本地图片。
            bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME, context.getString(R.string.app_name));
            bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);

            doShareToQQ(context, bundle);
        } else {
            ToastUtil.showToastShort(context, R.string.qq_not_installed);
        }
    }



    private void doShareToQQ(Activity context, Bundle params) {
        mTencent.shareToQQ(context, params, qqShareListener);
    }

     private IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
            ToastUtil.showToastShort(AgentApplication.get(), R.string.share_cancel);
        }
        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            ToastUtil.showToastShort(AgentApplication.get(), R.string.share_success);
        }
        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
            ToastUtil.showToastShort(AgentApplication.get(), R.string.share_error);
        }
    };


    /**
     * 检查手机是否安装QQ
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

}
