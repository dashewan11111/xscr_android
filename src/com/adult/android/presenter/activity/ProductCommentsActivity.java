package com.adult.android.presenter.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.ProductComment;
import com.adult.android.entity.ProductCommentsInfo;
import com.adult.android.model.CommentModel;
import com.adult.android.model.CommentModel.CommentType;
import com.adult.android.model.CommentModel.OnGetProductCommentsListener;
import com.adult.android.model.internet.exception.ResponseException;
import com.adult.android.utils.Misc;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.RoundProgressBar;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
/**
 * @ClassName: ProductCommentsActivity
 * @Description:商品评论列表页
 * @author JingYuchuan
 * @date 2015年3月31日 下午8:32:22
 * 
 */
public class ProductCommentsActivity extends BaseActivity implements
		OnGetProductCommentsListener, OnClickListener {
	public static final String EXTRA_PRODUCT_ID = "extra_product_id";
	private PullToRefreshListView mPullRefreshListView;
	private CommentsAdapter adapter;
	private CommentType currentCommentType = CommentType.ALL;
	private int currentPage = 0;
	private int perPageSize = 15;
	private List<ProductComment> dataList = new ArrayList<ProductComment>();
	private String pid;
	private CommentModel commentModel = new CommentModel();
	private View headerView;
	private RoundProgressBar roundProgressBar;
	private TextView haopingText, zhongpingText, chapingText;
	private ProgressBar haopingProgressBar, zhongpingProgressBar,
			chapingProgressBar;
	private RelativeLayout sort1, sort2, sort3, sort4;
	private TextView sortText1, sortText2, sortText3, sortText4;
	private ProductCommentsInfo productCommentsinfo;
	private String percentage;
	private DecimalFormat decimalFormat = new DecimalFormat("#");

	private LinearLayout footView;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pid = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
		setShowTitleBar(true);
		setActivityTitle(R.string.activity_title_product_comments);
		percentage = getResources().getString(R.string.percentage);
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.lv_comment_list);
		/**
		 * 实现 接口 OnRefreshListener2<ListView> 以便与监听 滚动条到顶部和到底部
		 */
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						currentPage = 0;
						commentModel.getCommentListOfProduct(pid, currentPage,
								perPageSize, currentCommentType,
								ProductCommentsActivity.this);
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						commentModel.getCommentListOfProduct(pid, currentPage,
								perPageSize, currentCommentType,
								ProductCommentsActivity.this);
					}
				});
		listView = mPullRefreshListView.getRefreshableView();
		headerView = LayoutInflater.from(this).inflate(
				R.layout.comment_list_header_layout, null);

		footView = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.comment_list_foot, null);
		roundProgressBar = (RoundProgressBar) headerView
				.findViewById(R.id.roundProgressBar);
		roundProgressBar.setProgress(0);
		roundProgressBar.setSampleText(getResources().getString(
				R.string.good_rate));
		haopingText = (TextView) headerView
				.findViewById(R.id.comment_haoping_text);
		zhongpingText = (TextView) headerView
				.findViewById(R.id.comment_zhongping_text);
		chapingText = (TextView) headerView
				.findViewById(R.id.comment_chaping_text);
		haopingProgressBar = (ProgressBar) headerView
				.findViewById(R.id.progressbar1);
		zhongpingProgressBar = (ProgressBar) headerView
				.findViewById(R.id.progressbar2);
		chapingProgressBar = (ProgressBar) headerView
				.findViewById(R.id.progressbar3);
		sort1 = (RelativeLayout) headerView.findViewById(R.id.comments_sort_1);
		sort2 = (RelativeLayout) headerView.findViewById(R.id.comments_sort_2);
		sort3 = (RelativeLayout) headerView.findViewById(R.id.comments_sort_3);
		sort4 = (RelativeLayout) headerView.findViewById(R.id.comments_sort_4);
		sort1.setOnClickListener(this);
		sort2.setOnClickListener(this);
		sort3.setOnClickListener(this);
		sort4.setOnClickListener(this);
		sortText1 = (TextView) headerView
				.findViewById(R.id.comments_sort_text_1);
		sortText2 = (TextView) headerView
				.findViewById(R.id.comments_sort_text_2);
		sortText3 = (TextView) headerView
				.findViewById(R.id.comments_sort_text_3);
		sortText4 = (TextView) headerView
				.findViewById(R.id.comments_sort_text_4);
		listView.addHeaderView(headerView);
		listView.addFooterView(footView);

		adapter = new CommentsAdapter();
		mPullRefreshListView.setAdapter(adapter);

		refreshUI(currentCommentType);
		commentModel.getCommentListOfProduct(pid, currentPage, perPageSize,
				currentCommentType, ProductCommentsActivity.this);
	}

	private void changeCommentType(CommentType type) {
		if (currentCommentType == type)
			return;
		refreshUI(type);
		currentCommentType = type;
		currentPage = 0;
		commentModel.getCommentListOfProduct(pid, currentPage, perPageSize,
				currentCommentType, ProductCommentsActivity.this);
	}

	private String formatCountNumber(int count) {
		return count > 999 ? "999+" : (count + "");
	}

	private String formatPercentageNumber(int count) {
		return count > 9 ? (count + "") : (" " + count);
	}

	private void refreshUI(CommentType type) {
		int percentageGoodNumber = 0;
		int percentageOkNumber = 0;
		int percentageBadNumber = 0;
		String sortTextShow1 = "0";
		String sortTextShow2 = "0";
		String sortTextShow3 = "0";
		String sortTextShow4 = "0";
		String percentageGood = "0";
		String percentageOk = "0";
		String percentageBad = "0";
		if (productCommentsinfo != null) {
			percentageGoodNumber = productCommentsinfo.getGoodRatio();
			percentageOkNumber = productCommentsinfo.getOkRatio();
			percentageBadNumber = productCommentsinfo.getBadRatio();
			sortTextShow1 = formatCountNumber(productCommentsinfo
					.getTotalCount());
			sortTextShow2 = formatCountNumber(productCommentsinfo
					.getGoodCount());
			sortTextShow3 = formatCountNumber(productCommentsinfo.getOkCount());
			sortTextShow4 = formatCountNumber(productCommentsinfo.getBadCount());
			percentageGood = formatPercentageNumber(productCommentsinfo
					.getGoodRatio());
			percentageOk = formatPercentageNumber(productCommentsinfo
					.getOkRatio());
			percentageBad = formatPercentageNumber(productCommentsinfo
					.getBadRatio());
		}
		roundProgressBar.setProgress(percentageGoodNumber);
		haopingText.setText(String.format(getResources().getString(R.string.brackets_number),decimalFormat.format(percentageGoodNumber) + percentage));
		
		zhongpingText.setText(String.format(getResources().getString(R.string.brackets_number),decimalFormat.format(percentageOkNumber) + percentage));
		
		chapingText.setText(String.format(getResources().getString(R.string.brackets_number),decimalFormat.format(percentageBadNumber) + percentage));
		
		
		System.out.println("percentageBadNumber"+percentageGoodNumber);
		System.out.println("percentageBadNumber"+percentageOkNumber);
		System.out.println("percentageBadNumber"+percentageBadNumber);
		
		haopingProgressBar.setProgress(percentageGoodNumber);
		zhongpingProgressBar.setProgress(percentageOkNumber);
		chapingProgressBar.setProgress(percentageBadNumber);
		sortText1.setText(String.format(
				getResources().getString(R.string.comment_all_number),
				sortTextShow1));
		sortText2.setText(String.format(
				getResources().getString(R.string.comment_good_number),
				sortTextShow2));
		sortText3.setText(String.format(
				getResources().getString(R.string.comment_ok_number),
				sortTextShow3));
		sortText4.setText(String.format(
				getResources().getString(R.string.comment_bad_number),
				sortTextShow4));
		switch (type) {
		case ALL:
			sort1.setSelected(true);
			sort2.setSelected(false);
			sort3.setSelected(false);
			sort4.setSelected(false);
			break;
		case GOOD:
			sort1.setSelected(false);
			sort2.setSelected(true);
			sort3.setSelected(false);
			sort4.setSelected(false);
			break;
		case OK:
			sort1.setSelected(false);
			sort2.setSelected(false);
			sort3.setSelected(true);
			sort4.setSelected(false);
			break;
		case BAD:
			sort1.setSelected(false);
			sort2.setSelected(false);
			sort3.setSelected(false);
			sort4.setSelected(true);
			break;
		}
	}

	@Override
	public void onClick(View arg0) {
		final int id = arg0.getId();
		switch (id) {
		case R.id.comments_sort_1:
			changeCommentType(CommentType.ALL);
			break;

		case R.id.comments_sort_2:

			changeCommentType(CommentType.GOOD);
			break;
		case R.id.comments_sort_3:

			changeCommentType(CommentType.OK);
			break;
		case R.id.comments_sort_4:

			changeCommentType(CommentType.BAD);
			break;
		default:
			break;
		}
	}

	@Override
	public void onGetProductCommentsFail(ResponseException e) {
		ToastUtil.showToastShort(this, e.getResultMsg());
		mPullRefreshListView.onRefreshComplete();
	}

	@Override
	public void onGetProductComments(ProductCommentsInfo info) {
		productCommentsinfo = info;

		if (null != productCommentsinfo.getComments()) {

			listView.removeFooterView(footView);
		}
		if (currentPage == 0) {
			dataList.clear();
			refreshUI(currentCommentType);
		}
		if (info.getComments() != null && info.getComments().size() > 0) {
			dataList.addAll(info.getComments());
			if (info.getComments().size() == perPageSize) {
				currentPage++;
			}
		}
		adapter.notifyDataSetChanged();
		mPullRefreshListView.onRefreshComplete();
	}

	public class CommentsAdapter extends BaseAdapter {
		private LayoutInflater layoutInflater;

		public CommentsAdapter() {
			super();
			this.layoutInflater = LayoutInflater
					.from(ProductCommentsActivity.this);
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = layoutInflater.inflate(
						R.layout.comment_list_item_layout, null);
			}
			final ProductComment comment = dataList.get(position);
			/** 文字 */
			TextView name = (TextView) convertView.findViewById(R.id.name);
			name.setText(TextUtils.isEmpty(comment.getUserInfo().getUserName()) ? ""
					: comment.getUserInfo().getUserName());
			((TextView) convertView.findViewById(R.id.content)).setText(comment
					.getContext());
			((TextView) convertView.findViewById(R.id.time)).setText(Misc
					.dateFormat(comment.getDate(), null));
			RatingBar ratingBar = (RatingBar) convertView
					.findViewById(R.id.ratingBar);
			ratingBar.setRating(comment.getLevel());
			return convertView;
		}

	}

}
