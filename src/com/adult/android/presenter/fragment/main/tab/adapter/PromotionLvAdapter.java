package com.adult.android.presenter.fragment.main.tab.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.FullOrBuyGiftsInfo;
import com.adult.android.entity.GiftsInfo;
import com.adult.android.utils.CcigmallCountDownTimer;
import com.adult.android.utils.Misc;
import com.adult.android.view.LLayoutToListView;

/**
 * Created by huangchao on 8/18/2015.
 */
public class PromotionLvAdapter extends BaseAdapter {
	private Map<Integer, CcigmallCountDownTimer> timerList = new HashMap<>();
	private Context context;

	private List<FullOrBuyGiftsInfo> promotionList;

	private String nowTime;

	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private RelativeLayout promotion_all_layout;

	private LLayoutToListView product_details_promotion_lv;

	private long accessTime = 0l;

	public PromotionLvAdapter(RelativeLayout promotion_all_layout,
			LLayoutToListView product_details_promotion_lv, Context context,
			List<FullOrBuyGiftsInfo> promotionList, String nowTime,
			long accessTime) {
		this.context = context;
		this.promotionList = promotionList;
		this.nowTime = nowTime;
		this.promotion_all_layout = promotion_all_layout;
		this.product_details_promotion_lv = product_details_promotion_lv;
		this.accessTime = accessTime;
	}

	@Override
	public int getCount() {
		if (promotionList != null && promotionList.size() > 0) {
			return promotionList.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.promotion_lv_item, null);
			vh.promotion_lv_item_pro_icon = (ImageView) convertView
					.findViewById(R.id.promotion_lv_item_pro_icon);
			vh.promotion_lv_item_pro_reason = (TextView) convertView
					.findViewById(R.id.promotion_lv_item_pro_reason);
			vh.promotion_lv_item_count_down_layout = (LinearLayout) convertView
					.findViewById(R.id.promotion_lv_item_count_down_layout);
			vh.promotion_lv_item_expand_layout = (LinearLayout) convertView
					.findViewById(R.id.promotion_lv_item_expand_layout);
			vh.promotion_lv_item_count_down = (TextView) convertView
					.findViewById(R.id.promotion_lv_item_count_down);
			vh.promotion_lv_item_gifts = (LinearLayout) convertView
					.findViewById(R.id.promotion_lv_item_gifts);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.promotion_lv_item_pro_icon.setImageResource(Misc
				.switchPromotionIcon(promotionList.get(position)
						.getPromotionType()));
		if (position == 0 && promotionList.size() > 1) {
			vh.promotion_lv_item_expand_layout.setVisibility(View.VISIBLE);
			// 展开或者闭合
			vh.promotion_lv_item_expand_layout
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (promotion_all_layout.getVisibility() == View.GONE) {
								promotion_all_layout
										.setVisibility(View.VISIBLE);
								product_details_promotion_lv
										.setVisibility(View.GONE);
							}
						}
					});

		} else {
			vh.promotion_lv_item_expand_layout.setVisibility(View.GONE);
		}

		vh.promotion_lv_item_pro_reason.setText(promotionList.get(position)
				.getPromotionName());

		if (promotionList.get(position).getPromotionType().equals("buy")
				|| promotionList.get(position).getPromotionType()
						.equals("withIncreasing")) {
			vh.promotion_lv_item_gifts.setVisibility(View.VISIBLE);
			vh.promotion_lv_item_count_down_layout.setVisibility(View.GONE);
			vh.promotion_lv_item_gifts.removeAllViews();
			if (promotionList.get(position).getGifts() != null
					&& promotionList.get(position).getGifts().size() > 0) {
				for (GiftsInfo giftsInfo : promotionList.get(position)
						.getGifts()) {
					TextView tv = new TextView(context);
					tv.setText(giftsInfo.getProductName());
					tv.setTextColor(context.getResources().getColor(
							R.color.main_text_color));
					vh.promotion_lv_item_gifts.addView(tv);
				}

			}

		} else if (promotionList.get(position).getPromotionType()
				.equals("priceDown")) {
			// 只有直降才会显示倒计时
			vh.promotion_lv_item_gifts.setVisibility(View.GONE);
			vh.promotion_lv_item_count_down_layout.setVisibility(View.VISIBLE);

			if (nowTime != null
					&& !TextUtils.isEmpty(promotionList.get(position)
							.getEndTime())) {

				Long lTime = null;
				Long nTime = null;
				try {
					lTime = format.parse(
							promotionList.get(position).getEndTime()).getTime();
					nTime = format.parse(nowTime).getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (lTime - nTime - accessTime > 0) {
					vh.promotion_lv_item_count_down_layout
							.setVisibility(View.VISIBLE);
					CcigmallCountDownTimer ccigmallCountDownTimer = new CcigmallCountDownTimer(
							vh.promotion_lv_item_count_down, lTime - nTime
									- accessTime, 1000, "act");
					ccigmallCountDownTimer.start();
					CcigmallCountDownTimer timerOfMap = timerList.get(position);
					if (timerOfMap != null) {
						timerOfMap.cancel();
						timerList.remove(position);
					}
					timerList.put(position, ccigmallCountDownTimer);
				} else {
					vh.promotion_lv_item_count_down_layout
							.setVisibility(View.GONE);
				}
			} else {
				vh.promotion_lv_item_count_down_layout.setVisibility(View.GONE);
			}
		} else {
			vh.promotion_lv_item_gifts.setVisibility(View.GONE);
			vh.promotion_lv_item_count_down_layout.setVisibility(View.GONE);
		}

		return convertView;
	}

	public void cancelAllTimer() {
		for (Map.Entry<Integer, CcigmallCountDownTimer> entry : timerList
				.entrySet()) {
			CcigmallCountDownTimer timerOfMap = entry.getValue();
			if (timerOfMap != null) {
				timerOfMap.cancel();
				timerOfMap = null;
			}
		}
		timerList.clear();
	}

	class ViewHolder {
		ImageView promotion_lv_item_pro_icon;

		TextView promotion_lv_item_pro_reason;

		LinearLayout promotion_lv_item_count_down_layout,
				promotion_lv_item_expand_layout, promotion_lv_item_gifts;

		TextView promotion_lv_item_count_down;
	}

}
