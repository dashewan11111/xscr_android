package com.adult.android.presenter.fragment.main.tab;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.adult.android.R;
import com.adult.android.entity.CategoryFrist;
import com.adult.android.entity.CategoryResponse;
import com.adult.android.model.CategoryModel;
import com.adult.android.model.internet.exception.HttpResponseException;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.presenter.activity.MainActivity;
import com.adult.android.presenter.activity.SearchActivity;
import com.adult.android.presenter.fragment.CategoryContentFragment;
import com.adult.android.presenter.fragment.main.BaseTabFragment;
import com.adult.android.presenter.fragment.main.tab.adapter.CategoryFirstAdapter;
import com.adult.android.view.LoadingDialog;

/**
 * 分类页面
 * 
 * @author LiCheng
 * 
 */
public class CategoryFragment extends BaseTabFragment implements
		OnClickListener {

	public static final String Tag = "CategoryFragment";

	private LinearLayout llytMainView = null;

	private ListView listView;

	private CategoryFirstAdapter listAdapter;

	private LoadingDialog loading;

	private FragmentManager mFragmentManager;

	private List<CategoryFrist> categorylist = new ArrayList<CategoryFrist>();

	public CategoryFragment() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		llytMainView = (LinearLayout) inflater.inflate(
				R.layout.fragment_category, null);
		return llytMainView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	/**
	 * 初始化view
	 */
	private void initView() {
		((MainActivity) getActivity()).setShowSearchViewCategory();
		mFragmentManager = getActivity().getSupportFragmentManager();
		loading = new LoadingDialog(getActivity());
		listView = (ListView) llytMainView.findViewById(R.id.category_list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				for (int i = 0; i < categorylist.size(); i++) {
					listView.getChildAt(i).setBackgroundResource(
							R.drawable.category_list_bg_gray);
				}
				switchTab((int) id);
				listView.setSelection(position);
			}
		});
		getDateList();
	}

	/**
	 * 获取数据
	 */
	protected void getDateList() {
		// categoryListModel.getCategoryResult(null, null, currentPage, this);
		CategoryModel.getInstance().getCategoryList("0", "1" + "",
				new CategoryModel.OnGetCategoryListCompletedListener() {

					@Override
					public void onSuccess(CategoryResponse info) {
						loading.dismiss();
						categorylist = info.getData();
						if (null == listAdapter) {
							listAdapter = new CategoryFirstAdapter(
									getActivity(), categorylist);
							listView.setAdapter(listAdapter);
						} else {
							listAdapter.notifyDataSetChanged();
						}
						if (0 < categorylist.size()) {
							switchTab(0);
						}
					}

					@Override
					public void onStart() {
						loading.show();
					}

					@Override
					public void onHttpException(HttpResponseException e) {
						loading.dismiss();
					}

					@Override
					public void onFinish() {
						loading.dismiss();
					}

					@Override
					public void onFailed(ResponseException e) {
						loading.dismiss();
					}
				});
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.search_view_root:
			goToSearchActivity();
			break;
		default:
			break;
		}
	}

	private void goToSearchActivity() {
		Intent intent = new Intent();
		intent.setClass(getActivity(), SearchActivity.class);
		startActivity(intent);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (!hidden) {
			((MainActivity) getActivity()).setShowSearchViewCategory();
		}
	}

	private List<String> tagList;

	/** 切换页面 */
	private void switchTab(int id) {
		if (null != listView.getChildAt(id)) {
			listView.getChildAt(id).setBackgroundResource(
					R.drawable.category_list_bg_white);
		}
		hideAllFragments(Tag + id);
		Fragment fragment = mFragmentManager.findFragmentByTag(Tag + id);
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		if (null == fragment) {
			fragment = new CategoryContentFragment();
			Bundle data = new Bundle();
			data.putString("categoryId", categorylist.get(id).getCategoryId());
			fragment.setArguments(data);
			mFragmentTransaction.add(R.id.category_fragment_content, fragment,
					Tag + id);
			if (null == tagList) {
				tagList = new ArrayList<String>();
			}
			tagList.add(Tag + id);
		} else {
			mFragmentTransaction.show(fragment);
		}
		mFragmentTransaction.commit();
	}

	private void hideAllFragments(String id) {
		if (null == tagList) {
			return;
		}
		for (String tag : tagList) {
			if (id.equals(tag)) {
				continue;
			}
			hideFragmet(tag);
		}
	}

	/** 隐藏Fragment */
	private void hideFragmet(String tag) {
		Fragment fragment = mFragmentManager.findFragmentByTag(tag);
		FragmentTransaction mFragmentTransaction = mFragmentManager
				.beginTransaction();
		if (null != fragment && !fragment.isHidden()) {
			mFragmentTransaction.hide(fragment);
			mFragmentTransaction.commit();
		}
	}

}
