package com.adult.android.presenter.fragment.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * @ClassName: BaseFragment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年4月21日 下午4:45:28
 * 
 */
public class BaseFragment extends Fragment {
	private View exceptionView;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// exceptionView = LayoutInflater.from(getActivity()).inflate(
		// R.layout.error_loading_layout, null);
		// exceptionView.findViewById(R.id.retry).setOnClickListener(
		// new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// retry();
		// }
		// });
		// addView(exceptionView, new RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.MATCH_PARENT,
		// RelativeLayout.LayoutParams.MATCH_PARENT));
		// hideExceptionView();
	}

	protected void showExceptionView() {
		// exceptionView.setVisibility(View.VISIBLE);
	}

	protected void hideExceptionView() {
		// exceptionView.setVisibility(View.GONE);
	}

	protected void retry() {

	}
}
