package com.adult.android.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;

public class AnimView2 extends RelativeLayout {

	private static final int ANIM_TIME = 300;

	private View rootView;

	private LinearLayout llyt_color;

	private Button btnSearch;

	private int screenWidth = 0;

	private float f_rate_A;

	private LinearLayout llyt_bg;

	private LinearLayout llytRoot;

	private View txtImage;

	private TextView txtHint;

	private EditText edTxt = null;

	private Context context;

	public AnimView2(Context context) {
		super(context);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rootView = inflater.inflate(R.layout.view_anim_2, this);
		intViews();
	}

	public AnimView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rootView = inflater.inflate(R.layout.view_anim_2, this);
		intViews();
	}

	public AnimView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rootView = inflater.inflate(R.layout.view_anim_2, this);
		intViews();
	}

	private void intViews() {
		llyt_color = (LinearLayout) rootView
				.findViewById(R.id.anmi_search_view_llyt_color);
		btnSearch = (Button) rootView
				.findViewById(R.id.anmi_search_view_btn_search);
		llyt_bg = (LinearLayout) rootView
				.findViewById(R.id.anmi_search_view_llyt_bg);
		llytRoot = (LinearLayout) rootView
				.findViewById(R.id.anmi_search_view_root);

		txtImage = (View) rootView.findViewById(R.id.anmi_search_view_img);
		txtHint = (TextView) rootView.findViewById(R.id.anmi_search_view_txt);
		edTxt = (EditText) rootView.findViewById(R.id.anmi_search_view_edtxt);

		screenWidth = getScreenWidth((Activity) context);
		int A_finalWidth = screenWidth - dip2px((Activity) context, 65);
		f_rate_A = (float) A_finalWidth / screenWidth;

	}

	public void startAnim() {
		// A的动画
		AnimationSet set = new AnimationSet(true);
		set.setDuration(ANIM_TIME);
		set.setFillAfter(true);
		// 伸缩
		ScaleAnimation scaleAnimA = new ScaleAnimation(1.0f, f_rate_A, 1.0f,
				1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0);

		set.addAnimation(scaleAnimA);

		// 颜色变化
		TransitionDrawable transition = (TransitionDrawable) llyt_color
				.getBackground();
		transition.startTransition(ANIM_TIME);

		llyt_bg.startAnimation(set);

		// B的动画
		ScaleAnimation scaleAnimB = new ScaleAnimation(0, 1.0f, 0f, 1.0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimB.setDuration(ANIM_TIME);
		scaleAnimB.setFillAfter(true);
		btnSearch.startAnimation(scaleAnimB);

		// 图片动画
		// TranslateAnimation transAnim = new TranslateAnimation(0, -screenWidth
		// / 2 + dip2px(context, 70), 0, 0);
		// transAnim.setDuration(ANIM_TIME);
		// transAnim.setFillAfter(true);
		// transAnim.setAnimationListener(new AnimFinished(1));
		// txtImage.startAnimation(transAnim);

		// 文字的动画
//		AnimationSet txtSet = new AnimationSet(true);
//		TranslateAnimation txtTransAnim = new TranslateAnimation(0,
//				-screenWidth / 2 + dip2px(context, 80), 0, 0);
//		AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
//		txtSet.setDuration(ANIM_TIME);
//		txtSet.setFillAfter(true);
//		txtSet.addAnimation(txtTransAnim);
//		txtSet.addAnimation(alphaAnimation);
//		txtHint.startAnimation(txtSet);

		// 换图片
		TransitionDrawable img_transition = (TransitionDrawable) txtImage
				.getBackground();
		img_transition.startTransition(ANIM_TIME);
	}

	public void backAnim() {
		// A的动画
		AnimationSet set = new AnimationSet(true);
		set.setDuration(ANIM_TIME);
		set.setFillAfter(true);
		// 伸缩
		ScaleAnimation scaleAnimA = new ScaleAnimation(f_rate_A, 1.0f, 1.0f,
				1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0);
		set.addAnimation(scaleAnimA);
		TransitionDrawable transition = (TransitionDrawable) llyt_color
				.getBackground();
		transition.reverseTransition(ANIM_TIME);

		llyt_bg.startAnimation(set);
		// B的动画
		ScaleAnimation scaleAnimB = new ScaleAnimation(1.0f, 0f, 1.0f, 0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimB.setDuration(ANIM_TIME);
		scaleAnimB.setFillAfter(true);
		btnSearch.startAnimation(scaleAnimB);
		// 图片移动
		// TranslateAnimation transAnim = new TranslateAnimation(-screenWidth /
		// 2
		// + dip2px(context, 70), 0, 0, 0);
		// transAnim.setDuration(ANIM_TIME);
		// transAnim.setFillAfter(true);
		// transAnim.setAnimationListener(new AnimFinished(0));
		// txtImage.startAnimation(transAnim);

		// 文字的动画
		// AnimationSet txtSet = new AnimationSet(true);
		// TranslateAnimation txtTransAnim = new TranslateAnimation(-screenWidth
		// / 2 + dip2px(context, 80), 0, 0, 0);
		// AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		// txtSet.setDuration(ANIM_TIME);
		// txtSet.setFillAfter(true);
		// txtSet.addAnimation(txtTransAnim);
		// txtSet.addAnimation(alphaAnimation);
		// txtHint.startAnimation(txtSet);

		// 换图片
		TransitionDrawable img_transition = (TransitionDrawable) txtImage
				.getBackground();
		img_transition.reverseTransition(ANIM_TIME);
	}

	private class AnimFinished implements AnimationListener {

		private int flag = -1;

		public AnimFinished(int flag) {
			this.flag = flag;
		}

		@Override
		public void onAnimationEnd(Animation arg0) {
			if (1 == flag) {
				edTxt.setEnabled(true);
				txtHint.setEnabled(false);
				txtImage.setEnabled(false);
				llytRoot.setClickable(false);
			}
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {

		}

		@Override
		public void onAnimationStart(Animation arg0) {
			if (0 == flag) {
				edTxt.setEnabled(false);
				txtHint.setEnabled(true);
				txtImage.setEnabled(true);
				llytRoot.setClickable(true);
			}
		}

	}

	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public static int dip2px(Context context, float dipValue) {
		float fontScale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * fontScale + 0.5f);
	}

}
