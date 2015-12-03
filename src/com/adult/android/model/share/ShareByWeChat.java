package com.adult.android.model.share;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.adult.android.R;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.utils.ImageUtil;
import com.adult.android.utils.ToastUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by huangchao on 2015/6/8.
 * 分享给微信好友或者朋友圈
 *
 */
public class ShareByWeChat {
    /**朋友圈分享支持的最低版本**/
    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;

    private static ShareByWeChat weChatInstance = null;

    private IWXAPI iwxapi;

    private ShareByWeChat(){
        iwxapi = WXAPIFactory.createWXAPI(AgentApplication.get(), ServiceUrlConstants.WECHAT_APPID);
        iwxapi.registerApp(ServiceUrlConstants.WECHAT_APPID);
    }

    public static ShareByWeChat getWeChatInstance(){
        if(weChatInstance == null){
            weChatInstance = new ShareByWeChat();
        }
        return weChatInstance;
    }

    /**
     * 微信分享
     * @param context
     * @param productName 商品名称
     * @param productImageUrl 商品主图url
     * @param productUrl 商品链接
     * @param productPrice 商品价格
     * @param isFriendsCircle 分享到朋友圈还是好友。true表示分享到朋友圈, false表示分享给好友
     */
    public void weChatShare(Activity context, String productName, String productImageUrl, String productUrl,
                            String productPrice, boolean isFriendsCircle) {
        if(iwxapi.isWXAppInstalled()){
            if(isFriendsCircle && !isCircleOfFriends(iwxapi)){
                ToastUtil.showToastShort(context,R.string.wechat_version_too_low);
                return;
            }

            sendWebpage(context, productName, productImageUrl, productUrl, productPrice, isFriendsCircle);

        } else {
            ToastUtil.showToastShort(context, R.string.wechat_not_installed);
        }


    }

    /**
     *
     * @param context  上下文
     * @param productName  商品名称
     * @param productImageUrl 商品主图url
     * @param productUrl 商品链接
     * @param productPrice 商品价格
     * @param isFriendsCircle 分享到朋友圈还是好友。true表示分享到朋友圈, false表示分享给好友
     */
    private void sendWebpage(final Activity context, String productName, String productImageUrl, String productUrl,
                             String productPrice, final boolean isFriendsCircle){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = productUrl;
        final WXMediaMessage msg = new WXMediaMessage(webpage);
        String shareTitle = context.getResources().getString(R.string.share_title_before) + productPrice +
                context.getResources().getString(R.string.share_title_after) + productName;
        msg.title = shareTitle;
        msg.description = context.getResources().getString(R.string.share_content_desc);

        HttpUtils httpUtils = new HttpUtils();
        File dirs = new File(ImageUtil.getCacheImgPath());
        //判断文件夹是否存在, 不存在就创建
        if(!dirs.exists() || !dirs.isDirectory()){
            dirs.mkdirs();
        }

        //如果文件夹中已经有, 就先删除
        File shareFile = new File(ImageUtil.getCacheImgPath() + "cigmall_share.jpg");
        if(shareFile.exists()){
            shareFile.delete();
        }

        httpUtils.download(productImageUrl, ImageUtil.getCacheImgPath() + "cigmall_share.jpg",
                true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
                true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
                new RequestCallBack<File>() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        Bitmap bmp = BitmapFactory.decodeFile(ImageUtil.getCacheImgPath() + "cigmall_share.jpg");
                        msg.setThumbImage(bmp);
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("webpage");
                        req.message = msg;
                        req.scene = isFriendsCircle ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                        iwxapi.sendReq(req);

                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });



    }

    /**
     * 活动页分享
     * @param context 上下文
     * @param actUrl 活动url
     * @param isFriendsCircle 分享到朋友圈还是好友。true表示分享到朋友圈, false表示分享给好友
     */
    public void weChatActShare(Activity context, String actUrl, boolean isFriendsCircle) {
        if(iwxapi.isWXAppInstalled()){
            if(isFriendsCircle && !isCircleOfFriends(iwxapi)){
                ToastUtil.showToastShort(context,R.string.wechat_version_too_low);
                return;
            }

            sendActWebpage(context, actUrl, isFriendsCircle);

        } else {
            ToastUtil.showToastShort(context, R.string.wechat_not_installed);
        }


    }

    /**
     * 活动页分享
     * @param context 上下文
     * @param actUrl 活动url
     * @param isFriendsCircle 分享到朋友圈还是好友。true表示分享到朋友圈, false表示分享给好友
     */
    private void sendActWebpage(Activity context, String actUrl, boolean isFriendsCircle){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = actUrl;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        String shareTitle = context.getResources().getString(R.string.act_share_title);
        msg.title = shareTitle;
        msg.description = context.getResources().getString(R.string.act_share_context);
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        msg.setThumbImage(bmp);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = isFriendsCircle ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        iwxapi.sendReq(req);
    }


    /**
     * 判断微信版本是否支持朋友圈分享
     * @param api
     * @return
     */
    private boolean isCircleOfFriends(IWXAPI api) {
        if (api.getWXAppSupportAPI() >= TIMELINE_SUPPORTED_VERSION){
            return true;
        }
        return false;
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
