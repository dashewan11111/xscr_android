package com.adult.android.presenter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.adult.android.R;
import com.adult.android.presenter.fragment.main.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class SearchResultListFragment extends BaseFragment implements
		OnClickListener {

	private Context context;
	private ListView productRefreshView;
	private PullToRefreshListView productList;
	private ImageView toStart;
	private View view;

	public SearchResultListFragment(Context c, View view1) {
		super();
		this.context = c;
		view = view1;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		productList = (PullToRefreshListView) view
				.findViewById(R.id.lv_goods_list);
		toStart = (ImageView) view.findViewById(R.id.backToStart);
		toStart.setOnClickListener(this);
		productRefreshView = productList.getRefreshableView();
		productRefreshView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem > 0) {
					if (toStart.getVisibility() == View.GONE) {
						toStart.setVisibility(View.VISIBLE);
					}
				} else {
					if (toStart.getVisibility() == View.VISIBLE) {
						toStart.setVisibility(View.GONE);
					}
				}

			}
		});
		return view;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.backToStart:
			productRefreshView.setSelection(0);
			toStart.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
