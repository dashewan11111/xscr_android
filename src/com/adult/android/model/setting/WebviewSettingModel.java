package com.adult.android.model.setting;

import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by RoyJing on 15/8/6.
 */
public class WebviewSettingModel {
    public static void addDefaultSettings(WebView webView) {
        WebSettings settings = webView.getSettings();

        int screenDensity = webView.getResources().getDisplayMetrics().densityDpi ;

        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM ;
        switch (screenDensity){
            case DisplayMetrics.DENSITY_LOW :
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break ;
        }
        settings.setDefaultZoom(zoomDensity);

        // 设置JS支持
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);

        settings.setLoadWithOverviewMode(true);
//        settings.setSupportZoom(true);
//        settings.setTextSize(WebSettings.TextSize.SMALLEST);
        // 支持内容从新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // 多窗口
        settings.supportMultipleWindows();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置可以访问文件 
        settings.setAllowFileAccess(true);
        // 当webview调用requestFocus时为webview设置节点
         settings.setNeedInitialFocus(true);
        // 设置支持缩放
//        settings.setBuiltInZoomControls(true);
        // 支持通过JS打开新窗口
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
    }
}
