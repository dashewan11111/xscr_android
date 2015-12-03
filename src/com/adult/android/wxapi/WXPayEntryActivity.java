package com.adult.android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.presenter.AgentApplication;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * User: liyuj<liyuj@cigmall.cn>
 * Date: 2015-08-07
 * Time: 10:30
 * FIXME
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.welcome);
        api = WXAPIFactory.createWXAPI(this, ServiceUrlConstants.WECHAT_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {


            AgentApplication app = (AgentApplication)getApplicationContext();
            WXPayCallback wxPayCallback = app.getWXPayCallback();
            if(wxPayCallback!=null){
                wxPayCallback.onWxPayResType(resp.errCode);
            }
            finish();
        }
    }

    public interface WXPayCallback{
        void onWxPayResType(int i);
    }
}
