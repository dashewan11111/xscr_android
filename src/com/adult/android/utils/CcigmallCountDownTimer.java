package com.adult.android.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by huangchao on 8/18/2015.
 */
public class CcigmallCountDownTimer extends CountDownTimer {

        private long secFormat = 1000;
        private long minFormat = secFormat * 60;
        private long hourFormat = minFormat * 60;
        private long dayFormat = hourFormat * 24;
        /**由于闪购和活动的倒计时展现方式有区别。所以区分是闪购倒计时还是活动倒计时, "act"表示活动倒计时, "flash"表示闪购倒计时**/
        private String actOrFlash = "";
        private TextView tv;

        public CcigmallCountDownTimer(TextView tv, long millisInFuture, long countDownInterval, String actOrFlash){
            super(millisInFuture, countDownInterval);
            this.tv = tv;
            this.actOrFlash = actOrFlash;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int days = (int) (millisUntilFinished / dayFormat);
            int hours = (int) ((millisUntilFinished - (days * dayFormat)) / hourFormat);
            int mins = (int) ((millisUntilFinished - (days * dayFormat) - (hours * hourFormat)) / minFormat);
            int secs = (int) ((millisUntilFinished - (days * dayFormat)
                    - (hours * hourFormat) - (mins * minFormat)) / secFormat);
            if(actOrFlash.equals("flash")){
                tv.setText(Misc.formatInt(days) + "天" + Misc.formatInt(hours) + "小时" + Misc.formatInt(mins) + "分" + Misc.formatInt(secs) + "秒");
            } else {
                if(days > 0){
                    tv.setText(Misc.formatInt(days) + "天" + Misc.formatInt(hours) + "小时");
                } else {
                    tv.setText(Misc.formatInt(hours) + ":" + Misc.formatInt(mins) + ":" + Misc.formatInt(secs));
                }
            }


        }

        @Override
        public void onFinish() {
            if(actOrFlash.equals("flash")) {
                tv.setText("00天00小时00分00秒");
            } else {
                tv.setText("00:00:00");
            }
        }

}
