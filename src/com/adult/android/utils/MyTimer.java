package com.adult.android.utils;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Handler;

// by娴峰钩
// 宸ヤ綔浜庡綋鍓嶇嚎绋嬩腑甯﹁鏁扮殑timer锛屽噺灏戠嚎绋嬪垏鎹㈠紑閿?紝涓?釜timer鍙互澶氭start stop锛屽敖閲忓鐢紝涓嶈new涓?釜鎵斾竴涓?
public final class MyTimer {

	private static final String TAG = "KwTimer";
	public static final int ACCURACY = 50; // 绮惧害

	// 浣跨敤鑰呭疄鐜拌繖涓潵鍝嶅簲timer,鍦ㄥ摢涓嚎绋嬮噷new锛孡istener灏卞湪鍝釜绾跨▼鍝嶅簲
	public interface Listener {
		void onTimer(final MyTimer timer); // 鍙互閫氳繃杩欎釜timer鏌ヨtick娆℃暟绛夊悇绉峵imer鐘舵?浜?
	}

	// Listener涓?鏄厑璁镐慨鏀圭殑锛屼笅鏂规湁鎺ュ彛
	public MyTimer(final Listener l) {
		listener = l;
		runThreadID = Thread.currentThread().getId();
	}

	// 鏃堕棿闂撮殧鍗曚綅姣锛宼imer绮惧害涓?ACCURACY ms銆?
	public void start(final int intervalMilisecond) {
		start(intervalMilisecond, -1);
	}

	// 鎵цtimes娆℃暟涔嬪悗鑷姩鍋滄
	public void start(final int intervalMilisecond, final int times) {
		if (running) {
			return;
		}
		interval = intervalMilisecond;
		startTime = System.currentTimeMillis();
		remainderTimes = times;
		tickTimes = 0;
		TimerHelper.setTimer(this);
	}

	// 鎵嬪姩鍋滄
	public void stop() {
		if (!running) {
			return;
		}
		TimerHelper.stopTimer(this);
	}

	public boolean isRunnig() {
		return running;
	}

	// 淇敼鍝嶅簲
	public void setListener(final Listener l) {
		listener = l;
	}

	// timer鐩墠鍝嶅簲浜嗗灏戞
	public int getTickTimes() {
		return tickTimes;
	}

	// 鐩墠杩樻湁澶氬皯娆℃病鏈夊搷搴旓紝闈炶娆imer杩斿洖-1
	public int getRemainderTimes() {
		return remainderTimes;
	}

	// 浠巗tart鍒扮幇鍦ㄨ繃浜嗗涔?
	public long getRunningTimeMiliseconds() {
		return System.currentTimeMillis() - startTime;
	}

	// //pirvate
	private long runThreadID = -1;
	private boolean running;
	private Listener listener;
	private int interval;
	private long startTime;
	private int remainderTimes = -1;
	private int tickTimes;

	private void onTimer() {
		if (remainderTimes > 0) {
			--remainderTimes;
			if (remainderTimes == 0) {
				TimerHelper.stopTimer(this);
			}
		}
		++tickTimes;
		if (listener != null) {
			listener.onTimer(this);
		}
	}

	// 瀛樺湪绾跨▼鏈湴瀛樺偍閲岀殑杈呭姪绫伙紝姣忎釜绾跨▼涓?釜锛岄噷闈㈡湁涓?釜handler鍙戞秷鎭疄鐜扮殑璁℃椂鍣ㄥ拰鏈嚎绋嬫墍鏈塼imer
	private static class TimerHelper extends Handler {

		public static void setTimer(final MyTimer timer) {
			TimerHelper helper = getThreadTimerHelper();
			helper.add(timer);
		}

		public static void stopTimer(final MyTimer timer) {
			TimerHelper helper = getThreadTimerHelper();
			helper.remove(timer);
		}

		// 鏍规嵁interval鎶婂搷搴攖imer鏀惧湪涓嶅悓鐨刧roup(TimingGroup)閲?
		private void add(final MyTimer timer) {
			timer.running = true;
			TimingItem item = new TimingItem();
			item.timer = timer;
			item.interval = timer.interval;
			item.elapsed = timer.interval;
			allTimers.add(item);
			++timingNum;
			timingIdle = 0;
			if (!timerMessageRunning) {
				timerMessageRunning = true;
				sendEmptyMessageDelayed(TIMER_ID, ACCURACY);
			}
		}

		private void remove(final MyTimer timer) {
			timer.running = false;
			for (TimingItem t : allTimers) {
				if (t.timer == timer) {
					t.timer = null;
					break;
				}
			}
		}

		@Override
		public void handleMessage(final android.os.Message msg) {
			if (msg.what == TIMER_ID) {
				dispatch();
				if (timingNum > 0) {
					sendEmptyMessageDelayed(TIMER_ID, ACCURACY);
				} else if (timingIdle < 10 * 1000 / ACCURACY) {
					sendEmptyMessageDelayed(TIMER_ID, ACCURACY);
					++timingIdle;
				} else {
					// 涓?鏃堕棿娌℃湁timer闇?鍝嶅簲灏辫嚜鍔ㄥ仠浜嗗惂
					timerMessageRunning = false;
					allTimers.clear();
					threadLocal.remove();
				}
			}
		}

		// 鍒嗗彂timer娑堟伅
		private void dispatch() {
			for (Iterator<TimingItem> itemIter = allTimers.iterator(); itemIter.hasNext();) {
				TimingItem item = (TimingItem) itemIter.next();
				item.elapsed -= ACCURACY;
				if (item.elapsed > ACCURACY / 2) {
					continue;
				}
				item.elapsed = item.interval;
				if (item.timer != null) {
					item.timer.onTimer();
				} else {
					itemIter.remove();
					--timingNum;
				}
			}
		}

		// 绾跨▼鏈湴瀛樺偍
		private static TimerHelper getThreadTimerHelper() {
			if (threadLocal == null) {
				threadLocal = new ThreadLocal<TimerHelper>();
			}
			TimerHelper helper = threadLocal.get();
			if (helper == null) {
				helper = new TimerHelper();
				threadLocal.set(helper);
			}
			return helper;
		}

		private static class TimingItem {
			public int interval;
			public int elapsed;
			public MyTimer timer;
		}

		private int timingNum;
		private int timingIdle;
		private boolean timerMessageRunning;
		private ArrayList<TimingItem> allTimers = new ArrayList<TimingItem>();
		private static final int TIMER_ID = 1001;
		private static ThreadLocal<TimerHelper> threadLocal;
	}

}
