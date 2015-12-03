package com.adult.android.entity;

/**
 * Created by huangchao on 2015/6/10.
 */
public class ShareInfo {

    /**分享平台名称**/
    private int shareName;
    /**分享平台图片**/
    private int shareRes;

    public void setShareName(int shareName) {
        this.shareName = shareName;
    }

    public void setShareRes(int shareRes) {
        this.shareRes = shareRes;
    }


    public int getShareRes() {
        return shareRes;
    }

    public int getShareName() {

        return shareName;
    }

    @Override
    public String toString() {
        return "ShareInfo{" +
                "shareName='" + shareName + '\'' +
                ", shareRes=" + shareRes +
                '}';
    }
}
