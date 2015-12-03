package com.adult.android.view.webview;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adult.android.R;
/**
 * 重写进度条
 * 
 * @author LiCheng
 */
public class MyChromeWebClient extends WebChromeClient {

	private Activity activity;

	private ImageView pb;

	private TextView txt;

	private Dialog dialog;

	private OnReceiveTitleListener listener;

	public MyChromeWebClient(Activity activity, ProgressBar pb,
			OnReceiveTitleListener listener) {
		this.activity = activity;
		this.listener = listener;
	}

	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		MyWebViewClient.process = newProgress;
		super.onProgressChanged(view, newProgress);
	}

	private void initDialog() {
		View dialogView = LayoutInflater.from(activity).inflate(
				R.layout.view_dialog_progress, null);
		pb = (ImageView) dialogView.findViewById(R.dialog_progress.pb);
		txt = (TextView) dialogView.findViewById(R.dialog_progress.txt);
		// 默认不显示进度
		txt.setVisibility(View.VISIBLE);
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(1500);
		rotateAnimation.setRepeatMode(RotateAnimation.RESTART);
		// Animation circleAnimation = AnimationUtils.loadAnimation(activity,
		// R.anim.loading_dialog);
		pb.startAnimation(rotateAnimation);
		dialog.setContentView(dialogView);
		dialog.setCanceledOnTouchOutside(false);
	}

	@Override
	public void onReceivedTitle(WebView view, String title) {
		super.onReceivedTitle(view, title);
		//listener.onReceiveTitle(title);
	}

	public interface OnReceiveTitleListener {
		void onReceiveTitle(String title);
	}
}
