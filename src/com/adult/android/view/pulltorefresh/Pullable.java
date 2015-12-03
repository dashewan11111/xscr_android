package com.adult.android.view.pulltorefresh;

public interface Pullable {
	/**
	 * �ж��Ƿ���������������Ҫ�������ܿ���ֱ��return false
	 * 
	 * @return true�������������򷵻�false
	 */
	boolean canPullDown();

	boolean canPullUp();
}
