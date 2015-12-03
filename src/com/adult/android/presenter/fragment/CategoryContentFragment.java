package com.adult.android.presenter.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.adult.android.R;
import com.adult.android.entity.CategoryFrist;
import com.adult.android.entity.CategoryResponse;
import com.adult.android.model.CategoryModel;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.presenter.activity.ProductListActivity;
import com.adult.android.presenter.fragment.main.tab.adapter.CategoryContentAdapter;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.LoadingDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

public class CategoryContentFragment extends Fragment {

	private View mainView;

	private CategoryContentAdapter listAdapter;

	private List<CategoryFrist> categorylist = new ArrayList<CategoryFrist>();

	private PullToRefreshGridView gridView;

	private LoadingDialog loadingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.category_content_fragment, null);
		return mainView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
	}

	private void initViews() {
		loadingDialog = new LoadingDialog(getActivity());
		gridView = (PullToRefreshGridView) mainView
				.findViewById(R.id.category_content_gridview);
		gridView.setMode(Mode.DISABLED);

		gridView.getRefreshableView().setOnItemClickListener(
				new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long id) {
						Intent intent = new Intent();
						intent.putExtra("categoryId", categorylist
								.get((int) id).getCategoryId());
						intent.setClass(getActivity(),
								ProductListActivity.class);
						startActivity(intent);
					}
				});
		getDateList();
	}

	/**
	 * 获取数据
	 */
	protected void getDateList() {
		loadingDialog.show();
		CategoryModel.getInstance().getCategoryList(
				getArguments().getString("categoryId"), "1" + "",
				new CategoryModel.OnGetCategoryListCompletedListener() {

					@Override
					public void onSuccess(CategoryResponse info) {
						loadingDialog.dismiss();
						categorylist = info.getData();
						if (null == categorylist) {
							return;
						}
						if (null == listAdapter) {
							listAdapter = new CategoryContentAdapter(
									getActivity(), categorylist);
							gridView.setAdapter(listAdapter);
						} else {
							listAdapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onStart() {

					}

					@Override
					public void onHttpException(HttpResponseException e) {

					}

					@Override
					public void onFinish() {

					}

					@Override
					public void onFailed(ResponseException e) {
						loadingDialog.dismiss();
						ToastUtil.showToastShort(getActivity(),
								e.getResultMsg());
					}
				});
	}
}
