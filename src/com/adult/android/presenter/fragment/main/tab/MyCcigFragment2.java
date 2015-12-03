package com.adult.android.presenter.fragment.main.tab;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.presenter.AgentApplication;
import com.adult.android.presenter.activity.CommentListActivity;
import com.adult.android.presenter.activity.CouponListActivity;
import com.adult.android.presenter.activity.FeedbackActivity;
import com.adult.android.presenter.activity.LoginActivity2;
import com.adult.android.presenter.activity.MainActivity;
import com.adult.android.presenter.activity.MyTopicListActivity;
import com.adult.android.presenter.activity.OrderListActivity2;
import com.adult.android.presenter.activity.RegistActivity;
import com.adult.android.presenter.activity.UserProfile;
import com.adult.android.presenter.fragment.main.BaseTabFragment;
import com.adult.android.utils.GeneralTool;
import com.adult.android.utils.ToastUtil;

/**
 * 个人中心主页面
 */
@SuppressLint("NewApi")
public class MyCcigFragment2 extends BaseTabFragment implements OnClickListener {

	public static final String Tag = "MyCMFragment2";

	private View llytMainView = null;

	private TextView txtUserName;

	private LinearLayout llytLoaded, llytUnLoaded;

	public MyCcigFragment2() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		llytMainView = inflater.inflate(R.layout.fragment_my, container, false);
		return llytMainView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initFragment();
		getActivity().registerReceiver(receiver,
				new IntentFilter(LoginActivity2.ACTION_REFRESH_USER));
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	/** 初始化 */
	private void initFragment() {
		initActivityTitle();
		llytLoaded = (LinearLayout) llytMainView
				.findViewById(R.id.fragment_my_layout_loaded);
		txtUserName = (TextView) llytMainView
				.findViewById(R.id.fragment_my_user_name);
		llytUnLoaded = (LinearLayout) llytMainView
				.findViewById(R.id.fragment_my_layout_unLoaded);
		llytLoaded.setOnClickListener(this);
		llytMainView.findViewById(R.id.fragment_my_login).setOnClickListener(
				this);
		llytMainView.findViewById(R.id.fragment_my_regist).setOnClickListener(
				this);
		llytMainView.findViewById(R.id.fragment_my_order).setOnClickListener(
				this);
		llytMainView.findViewById(R.id.fragment_my_coupon).setOnClickListener(
				this);
		llytMainView.findViewById(R.id.fragment_my_comment).setOnClickListener(
				this);
		llytMainView.findViewById(R.id.fragment_my_favourite)
				.setOnClickListener(this);
		llytMainView.findViewById(R.id.fragment_service_center)
				.setOnClickListener(this);
		llytMainView.findViewById(R.id.fragment_my_about).setOnClickListener(
				this);
		llytMainView.findViewById(R.id.fragment_my_topic_list)
				.setOnClickListener(this);
		updateUserInfo();
	}

	private void initActivityTitle() {
		((MainActivity) getActivity()).setActivityTitle("个人中心");
		((MainActivity) getActivity()).setShowMiddleView();
		//((MainActivity) getActivity()).setShowRightButton(false);
	}

	private void updateUserInfo() {
		if (null != AgentApplication.get().getUserId()) {
			llytLoaded.setVisibility(View.VISIBLE);
			llytUnLoaded.setVisibility(View.GONE);
			txtUserName.setText(AgentApplication.get().getUserInfo()
					.getLoginName());
		} else {
			llytLoaded.setVisibility(View.GONE);
			llytUnLoaded.setVisibility(View.VISIBLE);
		}
	}

	// 主界面的点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_my_login:
			Intent intent_login = new Intent(getActivity()
					.getApplicationContext(), LoginActivity2.class);
			intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent_login);
			break;
		case R.id.fragment_my_regist:
			Intent intent_regist = new Intent(getActivity()
					.getApplicationContext(), RegistActivity.class);
			intent_regist.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent_regist);
			break;
		case R.id.fragment_my_layout_loaded:
			Intent intent_profile = new Intent(getActivity()
					.getApplicationContext(), UserProfile.class);
			intent_profile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent_profile);
			break;
		// 全部订单
		case R.id.fragment_my_order:
			if (GeneralTool.isEmpty(AgentApplication.get().getUserId())) {
				ToastUtil.showToastShort(getActivity(), "请先登陆");
				return;
			}
			Intent intent = new Intent(getActivity().getApplicationContext(),
					OrderListActivity2.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent);
			break;
		// 我的优惠券
		case R.id.fragment_my_coupon:
			if (GeneralTool.isEmpty(AgentApplication.get().getUserId())) {
				ToastUtil.showToastShort(getActivity(), "请先登陆");
				return;
			}
			Intent intent_address = new Intent(getActivity()
					.getApplicationContext(), CouponListActivity.class);
			startActivity(intent_address);
			break;
		// 我的评价
		case R.id.fragment_my_comment:
			if (GeneralTool.isEmpty(AgentApplication.get().getUserId())) {
				ToastUtil.showToastShort(getActivity(), "请先登陆");
				return;
			}
			Intent intent_comment = new Intent(getActivity()
					.getApplicationContext(), CommentListActivity.class);
			intent_comment.putExtra("userId", AgentApplication.get()
					.getUserId());
			startActivity(intent_comment);
			break;

		// 我的收藏
		case R.id.fragment_my_favourite:
			// Intent intentAllOrder = new Intent(getActivity()
			// .getApplicationContext(), OrderListActivity.class);
			// intentAllOrder.putExtra(OrderListActivity.EXTRA_ORDER_TYPE,
			// OrderStatus.ALL);
			// startActivity(intentAllOrder);
			break;
		// 客服中心
		case R.id.fragment_service_center:
			Intent intent_feedback = new Intent(getActivity()
					.getApplicationContext(), FeedbackActivity.class);
			startActivity(intent_feedback);
			break;
		// 关于成人秀
		case R.id.fragment_my_about:
			// Intent send = new Intent(getActivity().getApplicationContext(),
			// OrderListActivity.class);
			// send.putExtra(OrderListActivity.EXTRA_ORDER_TYPE,
			// OrderStatus.UN_RECEIVED);
			// startActivity(send);
			break;
		case R.id.fragment_my_topic_list:
			if (GeneralTool.isEmpty(AgentApplication.get().getUserId())) {
				ToastUtil.showToastShort(getActivity(), "请先登陆");
				return;
			}
			Intent intent_topic = new Intent(getActivity()
					.getApplicationContext(), MyTopicListActivity.class);
			startActivity(intent_topic);
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (!hidden) {
			initActivityTitle();
			updateUserInfo();
		}
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			updateUserInfo();
			ToastUtil.showToastShort(getActivity(), "更新用户信息");
		}
	};
}