package com.adult.android.view.pulltorefresh;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;

public class PullToRefreshLayout extends RelativeLayout {
	public static final String TAG = "PullToRefreshLayout";

	public static final int INIT = 0;

	public static final int RELEASE_TO_REFRESH = 1;

	public static final int REFRESHING = 2;

	public static final int DONE = 3;

	private int state = INIT;

	private OnRefreshListener mListener;

	public static final int SUCCEED = 0;

	public static final int FAIL = 1;

	private float downY, lastY;

	public float pullDownY = 0;

	private float refreshDist = 200;

	private MyTimer timer;

	public float MOVE_SPEED = 8;

	private boolean isLayout = false;

	private boolean isTouch = false;

	private float radio = 2;

	private RotateAnimation rotateAnimation;

	private RotateAnimation refreshingAnimation;

	private View refreshView;

	private View pullView;

	private View refreshingView;

	private View refreshStateImageView;

	private TextView refreshStateTextView;

	private View pullableView;

	private int mEvents;

	Handler updateHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// �ص��ٶ�����������moveDeltaY���������
			MOVE_SPEED = (float) (8 + 5 * Math.tan(Math.PI / 2
					/ getMeasuredHeight() * (pullDownY)));
			if (!isTouch) {
				// ����ˢ�£���û�������ƵĻ�����ͣ����ʾ"����ˢ��..."
				if (state == REFRESHING && pullDownY <= refreshDist) {
					pullDownY = refreshDist;
					timer.cancel();
				}

			}
			if (pullDownY > 0)
				pullDownY -= MOVE_SPEED;
			if (pullDownY <= 0) {
				// ����ɻص�
				pullDownY = 0;
				pullView.clearAnimation();
				// ��������ͷʱ�п��ܻ���ˢ�£�ֻ�е�ǰ״̬��������ˢ��ʱ�Ÿı�״̬
				if (state != REFRESHING)
					changeState(INIT);
				timer.cancel();
			}
			// ˢ�²���,���Զ�����onLayout
			requestLayout();
		}

	};

	public void setOnRefreshListener(OnRefreshListener listener) {
		mListener = listener;
	}

	public PullToRefreshLayout(Context context) {
		super(context);
		initView(context);
	}

	public PullToRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	private void initView(Context context) {
		timer = new MyTimer(updateHandler);
		rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
				context, R.anim.reverse_anim);
		refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
				context, R.anim.rotating);
		// �������ת������
		LinearInterpolator lir = new LinearInterpolator();
		rotateAnimation.setInterpolator(lir);
		refreshingAnimation.setInterpolator(lir);
	}

	private void hide() {
		timer.schedule(5);
	}

	/**
	 * ���ˢ�²�������ʾˢ�½��ע�⣺ˢ����ɺ�һ��Ҫ�����������
	 */
	/**
	 * @param refreshResult
	 *            PullToRefreshLayout.SUCCEED���ɹ���PullToRefreshLayout.FAIL���ʧ
	 *            ��
	 */
	public void refreshFinish(int refreshResult) {
		refreshingView.clearAnimation();
		refreshingView.setVisibility(View.GONE);
		switch (refreshResult) {
		case SUCCEED:
			// ˢ�³ɹ�
			refreshStateImageView.setVisibility(View.VISIBLE);
			refreshStateTextView.setText(R.string.refresh_succeed);
			refreshStateImageView
					.setBackgroundResource(R.drawable.refresh_succeed);
			break;
		case FAIL:
		default:
			// ˢ��ʧ��
			refreshStateImageView.setVisibility(View.VISIBLE);
			refreshStateTextView.setText(R.string.refresh_fail);
			refreshStateImageView
					.setBackgroundResource(R.drawable.refresh_failed);
			break;
		}
		// ˢ�½��ͣ��1��
		new Handler() {
			@Override
			public void handleMessage(Message msg) {
				changeState(DONE);
				hide();
			}
		}.sendEmptyMessageDelayed(0, 1000);
	}

	private void changeState(int to) {
		state = to;
		switch (state) {
		case INIT:
			// �������ֳ�ʼ״̬
			refreshStateImageView.setVisibility(View.GONE);
			refreshStateTextView.setText(R.string.pull_to_refresh);
			pullView.clearAnimation();
			pullView.setVisibility(View.VISIBLE);
			break;
		case RELEASE_TO_REFRESH:
			// �ͷ�ˢ��״̬
			refreshStateTextView.setText(R.string.release_to_refresh);
			pullView.startAnimation(rotateAnimation);
			break;
		case REFRESHING:
			// ����ˢ��״̬
			pullView.clearAnimation();
			refreshingView.setVisibility(View.VISIBLE);
			pullView.setVisibility(View.INVISIBLE);
			refreshingView.startAnimation(refreshingAnimation);
			refreshStateTextView.setText(R.string.refreshing);
			break;
		case DONE:
			// ˢ����ϣ�ɶ������
			break;
		}
	}

	/*
	 * @see android.view.ViewGroup#dispatchTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			downY = ev.getY();
			lastY = downY;
			timer.cancel();
			mEvents = 0;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
		case MotionEvent.ACTION_POINTER_UP:
			mEvents = -1;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mEvents == 0) {
				if (((Pullable) pullableView).canPullDown()) {
					pullDownY = pullDownY + (ev.getY() - lastY) / radio;
					if (pullDownY < 0) {
						pullDownY = 0;
					}
					if (pullDownY > getMeasuredHeight())
						pullDownY = getMeasuredHeight();
					if (state == REFRESHING) {
						isTouch = true;
					}
				}
			} else
				mEvents = 0;
			lastY = ev.getY();
			radio = (float) (2 + 2 * Math.tan(Math.PI / 2 / getMeasuredHeight()
					* (pullDownY)));
			requestLayout();
			if (pullDownY <= refreshDist && state == RELEASE_TO_REFRESH) {
				changeState(INIT);
			}
			if (pullDownY >= refreshDist && state == INIT) {
				changeState(RELEASE_TO_REFRESH);
			}
			if ((pullDownY) > 8) {
				ev.setAction(MotionEvent.ACTION_CANCEL);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (pullDownY > refreshDist)
				isTouch = false;
			if (state == RELEASE_TO_REFRESH) {
				changeState(REFRESHING);

				if (mListener != null)
					mListener.onRefresh(this);
			}
			hide();
		default:
			break;
		}
		super.dispatchTouchEvent(ev);
		return true;
	}

	private void initView() {

		pullView = refreshView.findViewById(R.id.pull_icon);
		refreshStateTextView = (TextView) refreshView
				.findViewById(R.id.state_tv);
		refreshingView = refreshView.findViewById(R.id.refreshing_icon);
		refreshStateImageView = refreshView.findViewById(R.id.state_iv);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (!isLayout) {
			refreshView = getChildAt(0);
			pullableView = getChildAt(1);
			isLayout = true;
			initView();
			refreshDist = ((ViewGroup) refreshView).getChildAt(0)
					.getMeasuredHeight();
		}

		refreshView.layout(0,
				(int) (pullDownY) - refreshView.getMeasuredHeight(),
				refreshView.getMeasuredWidth(), (int) (pullDownY));
		pullableView.layout(0, (int) (pullDownY),
				pullableView.getMeasuredWidth(), (int) (pullDownY)
						+ pullableView.getMeasuredHeight());
	}

	class MyTimer {
		private Handler handler;
		private Timer timer;
		private MyTask mTask;

		public MyTimer(Handler handler) {
			this.handler = handler;
			timer = new Timer();
		}

		public void schedule(long period) {
			if (mTask != null) {
				mTask.cancel();
				mTask = null;
			}
			mTask = new MyTask(handler);
			timer.schedule(mTask, 0, period);
		}

		public void cancel() {
			if (mTask != null) {
				mTask.cancel();
				mTask = null;
			}
		}

		class MyTask extends TimerTask {
			private Handler handler;

			public MyTask(Handler handler) {
				this.handler = handler;
			}

			@Override
			public void run() {
				handler.obtainMessage().sendToTarget();
			}

		}
	}

	public interface OnRefreshListener {
		void onRefresh(PullToRefreshLayout pullToRefreshLayout);
	}

}
