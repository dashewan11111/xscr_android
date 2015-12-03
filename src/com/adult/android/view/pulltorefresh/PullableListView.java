package com.adult.android.view.pulltorefresh;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adult.android.R;

public class PullableListView extends ListView implements Pullable {
	public static final int INIT = 0;
	public static final int LOADING = 1;
	private OnLoadListener mOnLoadListener;
	private ImageView mLoadingView;
	private TextView mStateTextView;
	private int state = INIT;
	private boolean canLoad = false;
	private AnimationDrawable mLoadAnim;

	public PullableListView(Context context) {
		super(context);
		init(context);
	}

	public PullableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PullableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.load_more, null);
		mLoadingView = (ImageView) view.findViewById(R.id.loading_icon);
		mLoadingView.setBackgroundResource(R.anim.loading_anim);
		mLoadAnim = (AnimationDrawable) mLoadingView.getBackground();
		mStateTextView = (TextView) view.findViewById(R.id.loadstate_tv);
		// addFooterView(view, null, false);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			canLoad = false;
			break;
		case MotionEvent.ACTION_UP:
			canLoad = true;
			checkLoad();
			break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (canLoad) {
			checkLoad();
		}
	}

	/**
	 * �ж��Ƿ������Զ���������
	 */
	private void checkLoad() {
		if (reachBottom() && mOnLoadListener != null && state != LOADING && canLoad) {
			mOnLoadListener.onLoad(this);
			changeState(LOADING);
		}
	}

	private void changeState(int state) {
		this.state = state;
		switch (state) {
		case INIT:
			mLoadAnim.stop();
			mLoadingView.setVisibility(View.INVISIBLE);
			mStateTextView.setText(R.string.more);
			break;

		case LOADING:
			mLoadingView.setVisibility(View.VISIBLE);
			mLoadAnim.start();
			mStateTextView.setText(R.string.loading);
			break;
		}
	}

	/**
	 * ��ɼ���
	 */
	public void finishLoading() {
		changeState(INIT);
	}

	@Override
	public boolean canPullDown() {
		if (getCount() == 0) {
			return true;
		} else if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() >= 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean canPullUp() {
		// if (getCount() == 0) {
		// return true;
		// } else if (getLastVisiblePosition() == (getCount() - 1)) {
		// if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition())
		// != null
		// && getChildAt(
		// getLastVisiblePosition()
		// - getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
		// return true;
		// }
		return false;

	}

	public void setOnLoadListener(OnLoadListener listener) {
		this.mOnLoadListener = listener;
	}

	/**
	 * @return footerview�ɼ�ʱ����true�����򷵻�false
	 */
	public boolean reachBottom() {
		if (getCount() == 0) {
			// û��item��ʱ��Ҳ������������
			return true;
		} else if (getLastVisiblePosition() == (getCount() - 1)) {
			// �����ײ���
			if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
					&& getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()).getTop() < getMeasuredHeight())
				return true;
		}
		return false;
	}

	public interface OnLoadListener {
		void onLoad(PullableListView pullableListView);
	}
}
