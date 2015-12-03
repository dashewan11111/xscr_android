package com.adult.android.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.adult.android.R;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;

@SuppressWarnings("deprecation")
public class BannerGallery extends Gallery implements android.widget.AdapterView.OnItemClickListener,
		android.widget.AdapterView.OnItemSelectedListener, OnTouchListener {

	private Context mContext;

	private MyOnItemClickListener mMyOnItemClickListener;

	private int mSwitchTime;

	private Timer mTimer;

	private LinearLayout mOvalLayout;

	private int curIndex = 0;

	private int oldIndex = 0;

	private int mFocusedId;

	private int mNormalId;

	private String[] imageUrls;

	List<ImageView> listImgs;

	private BitmapUtils bitmapUtils;

	public BannerGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		bitmapUtils = new BitmapUtils(context);
	}

	public BannerGallery(Context context) {
		super(context);
		bitmapUtils = new BitmapUtils(context);
	}

	public BannerGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmapUtils = new BitmapUtils(context);
	}

	private void init() {
		setSpacing(-1);
		setUnselectedAlpha(1f);
		setSoundEffectsEnabled(false);
		setFadingEdgeLength(0);
		setAnimationDuration(1000);
		setFocusableInTouchMode(true);
	}

	public void start(Context context, String[] imageUrls, int switchTime, LinearLayout ovalLayout, int focusedId,
			int normalId) {
		this.mContext = context;
		this.imageUrls = imageUrls;
		this.mSwitchTime = switchTime;
		this.mOvalLayout = ovalLayout;
		this.mFocusedId = focusedId;
		this.mNormalId = normalId;
		ininImages();
		setAdapter(new AdAdapter());
		this.setOnItemClickListener(this);
		this.setOnTouchListener(this);
		this.setOnItemSelectedListener(this);
		this.setSoundEffectsEnabled(false);
		this.setAnimationDuration(700);
		this.setUnselectedAlpha(1);

		setSpacing(0);

		setSelection((getCount() / 2 / listImgs.size()) * listImgs.size());
		setFocusableInTouchMode(true);
		initOvalLayout();
		startTimer();
	}

	private void ininImages() {
		listImgs = new ArrayList<ImageView>();
		int len = imageUrls.length;
		for (int i = 0; i < len; i++) {
			ImageView imageview = new ImageView(mContext);
			imageview.setScaleType(ImageView.ScaleType.FIT_XY);
			imageview.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT,
					Gallery.LayoutParams.MATCH_PARENT));
			if (null == imageUrls) {	
				imageview.setImageResource(R.drawable.ic_launcher);
			} else {
				bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_720_360);
				bitmapUtils.display(imageview, ServiceUrlConstants.getImageHost() + imageUrls[i]);
			}
			listImgs.add(imageview);
		}
	}

	private void initOvalLayout() {
		if (mOvalLayout != null && listImgs.size() < 2) {
			mOvalLayout.getLayoutParams().height = 0;
		} else if (mOvalLayout != null) {
			int Ovalheight = (int) (mOvalLayout.getLayoutParams().height * 0.7);
			int Ovalmargin = (int) (mOvalLayout.getLayoutParams().height * 0.2);
			android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Ovalheight,
					Ovalheight);
			layoutParams.setMargins(Ovalmargin, 0, Ovalmargin, 0);
			for (int i = 0; i < listImgs.size(); i++) {
				View v = new View(mContext);
				v.setLayoutParams(layoutParams);
				v.setBackgroundResource(mNormalId);
				mOvalLayout.addView(v);
			}
			mOvalLayout.getChildAt(0).setBackgroundResource(mFocusedId);
		}
	}

	class AdAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (listImgs.size() < 2)
				return listImgs.size();
			return Integer.MAX_VALUE;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return listImgs.get(position % listImgs.size());
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		int kEvent;
		if (isScrollingLeft(e1, e2)) {
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(kEvent, null);
		return true;

	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > (e1.getX() + 50);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (MotionEvent.ACTION_UP == event.getAction() || MotionEvent.ACTION_CANCEL == event.getAction()) {
			startTimer();
		} else {
			stopTimer();
		}
		return false;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
		curIndex = position % listImgs.size();
		if (mOvalLayout != null && listImgs.size() > 1) { // �л�Բ��
			mOvalLayout.getChildAt(oldIndex).setBackgroundResource(mNormalId); // Բ��ȡ��
			mOvalLayout.getChildAt(curIndex).setBackgroundResource(mFocusedId);// Բ��ѡ��
			oldIndex = curIndex;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if (mMyOnItemClickListener != null) {
			mMyOnItemClickListener.onItemClick(curIndex);
		}
	}

	public void setMyOnItemClickListener(MyOnItemClickListener listener) {
		mMyOnItemClickListener = listener;
	}

	public interface MyOnItemClickListener {

		void onItemClick(int curIndex);
	}

	public void stopTimer() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
	}

	public void startTimer() {
		if (mTimer == null && listImgs.size() > 1 && mSwitchTime > 0) {
			mTimer = new Timer();
			mTimer.schedule(new TimerTask() {
				public void run() {
					handler.sendMessage(handler.obtainMessage(1));
				}
			}, mSwitchTime, mSwitchTime);
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			onScroll(null, null, 1, 0);
			onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
		}
	};

	public void scrollLeft() {
		onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
	}

	public void scrollRight() {
		onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
	}

	public void scrollLeft(int during) {
		setAnimationDuration(during);
		onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
		setAnimationDuration(1000);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (mAttachedChanged != null) {
			mAttachedChanged.onAttachedToWindow(this);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (mAttachedChanged != null) {
			mAttachedChanged.onDetachedFromWindow(this);
		}
	}

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);
	}

	@Override
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		return false;
	}

	private boolean isLayout = false;

	public void doRequestLayout() {
		isLayout = true;
		requestLayout();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (!changed && !isLayout) {
			return;
		}
		super.onLayout(changed, l, t, r, b);
		isLayout = false;
	}

	private OnWindowAttachedChanged mAttachedChanged;

	public void setOnWindowAttachedChanged(OnWindowAttachedChanged onWindowAttachedChanged) {
		this.mAttachedChanged = onWindowAttachedChanged;
	}

	public static interface OnWindowAttachedChanged {
		public void onAttachedToWindow(BannerGallery view);

		public void onDetachedFromWindow(BannerGallery view);

		public void onTouchEvent(MotionEvent event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mAttachedChanged != null)
			mAttachedChanged.onTouchEvent(event);
		try {
			return super.onTouchEvent(event);
		} catch (Throwable e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.requestFocus();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		}
		super.dispatchTouchEvent(ev);
		return true;
	}
}
