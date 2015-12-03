package com.adult.android.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.DivisionCity;
import com.adult.android.entity.DivisionProvince;
import com.adult.android.entity.DivisionRegion;
import com.adult.android.utils.ImageUtil;
import com.adult.android.utils.Misc;
import com.adult.android.view.widget.OnWheelChangedListener;
import com.adult.android.view.widget.WheelView;
import com.adult.android.view.widget.adapter.ArrayWheelAdapter;

/**
 * Created by huangchao on 2015/6/10.
 */
public class AddressPopupWindow extends PopupWindow implements
		OnWheelChangedListener {

	private LinearLayout product_detail_trans_layout;

	private Activity context;

	private SampleDialog myDialog;
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	DivisionProvince[] provinceList = null;

	private onConfirmCompletedListener onConfirmCompletedListener;

	public AddressPopupWindow(Activity context,
			LinearLayout product_detail_trans_layout,
			onConfirmCompletedListener onConfirmCompletedListener) {
		super();
		this.context = context;
		this.product_detail_trans_layout = product_detail_trans_layout;
		this.onConfirmCompletedListener = onConfirmCompletedListener;
		setFocusable(true);
		setOutsideTouchable(true);
		setBackgroundDrawable(new ColorDrawable(0));
		// 窗口进入和退出的效果
		setAnimationStyle(R.style.win_ani_top_bottom);

		setShareView(R.layout.send_to_pw);

	}

	/**
	 * 为window添加布局文件
	 *
	 * @param layoutResId
	 */
	private void setShareView(int layoutResId) {
		View popView = LayoutInflater.from(context).inflate(layoutResId, null);
		setContentView(popView);
		mViewProvince = (WheelView) popView.findViewById(R.id.id_province);
		mViewCity = (WheelView) popView.findViewById(R.id.id_city);
		mViewDistrict = (WheelView) popView.findViewById(R.id.id_district);
		inflateInfo();
		setWidth(ImageUtil.getScreenWidth(context));
		setHeight(ImageUtil.getScreenHeight(context) * 4 / 9);

		// 位置可以按要求定义
		showAtLocation(popView, Gravity.NO_GRAVITY, 0,
				ImageUtil.getScreenHeight(context) * 5 / 9);

		product_detail_trans_layout.setVisibility(View.VISIBLE);
		TextView txtClose = (TextView) popView.findViewById(R.id.txt_close);
		TextView txtConfirm = (TextView) popView.findViewById(R.id.txt_confirm);
		txtClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		txtConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DivisionProvince currentProvince;
				DivisionCity currentCity;
				DivisionRegion currentDistrict;
				currentProvince = provinceList[mViewProvince.getCurrentItem()];
				currentCity = provinceList[mViewProvince.getCurrentItem()]
						.getCity_list().get(mViewCity.getCurrentItem());
				currentDistrict = provinceList[mViewProvince.getCurrentItem()]
						.getCity_list().get(mViewCity.getCurrentItem())
						.getArea_list().get(mViewDistrict.getCurrentItem());
				onConfirmCompletedListener.onConfirmCompleted(
						currentProvince.getId(), currentCity.getId(),
						currentDistrict.getId(), currentProvince.getName(),
						currentCity.getName(), currentDistrict.getName());
				dismiss();
			}
		});
	}

	private void inflateInfo() {
		mViewProvince.addChangingListener(this);
		mViewCity.addChangingListener(this);
		mViewDistrict.addChangingListener(this);

		provinceList = Misc.getPCAData(context);

		mViewProvince.setViewAdapter(new ArrayWheelAdapter<DivisionProvince>(
				context, provinceList));
		mViewProvince.setVisibleItems(3);
		mViewCity.setVisibleItems(3);
		mViewDistrict.setVisibleItems(3);
		mViewProvince.setCurrentItem(0);
		updateCities();
		updateAreas();
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();

		final int size = provinceList[pCurrent].getCity_list() == null ? 0
				: provinceList[pCurrent].getCity_list().size();
		DivisionCity[] cities = new DivisionCity[size];
		for (int i = 0; i < size; i++) {
			cities[i] = provinceList[pCurrent].getCity_list().get(i);
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<DivisionCity>(context,
				cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		final int pCurrentIndex = mViewProvince.getCurrentItem();
		int cCurrent = mViewCity.getCurrentItem();

		final int size = provinceList[pCurrentIndex].getCity_list()
				.get(cCurrent).getArea_list() == null ? 0
				: provinceList[pCurrentIndex].getCity_list().get(cCurrent)
						.getArea_list().size();
		DivisionRegion[] regions = new DivisionRegion[size];
		for (int i = 0; i < size; i++) {
			regions[i] = provinceList[pCurrentIndex].getCity_list()
					.get(cCurrent).getArea_list().get(i);
		}

		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<DivisionRegion>(
				context, regions));
		mViewDistrict.setCurrentItem(0);
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {

		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		}
	}

	@Override
	public void dismiss() {
		super.dismiss();
		product_detail_trans_layout.setVisibility(View.GONE);
	}

	public interface onConfirmCompletedListener {
		void onConfirmCompleted(String provinceId, String cityId,
				String areaId, String provinceName, String cityName,
				String streetName);
	}
}
