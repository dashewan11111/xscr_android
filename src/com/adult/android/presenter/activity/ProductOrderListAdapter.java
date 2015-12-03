package com.adult.android.presenter.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.CartSkuDTO;
import com.adult.android.entity.CartSkuGroupDTO;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.lidroid.xutils.BitmapUtils;

public class ProductOrderListAdapter extends BaseAdapter {

	private Context context;

	private List<CartSkuGroupDTO> groupList;

	private BitmapUtils bitmapUtils;

	public ProductOrderListAdapter(Context context,
			List<CartSkuGroupDTO> groupList, BitmapUtils bitmapUtils) {
		this.context = context;
		this.groupList = groupList;
		this.bitmapUtils = bitmapUtils;
	}

	@Override
	public int getCount() {
		return groupList.size();
	}

	@Override
	public Object getItem(int position) {
		return groupList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProductOrderHolder holder = null;
		if (null == convertView) {
			holder = new ProductOrderHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_product_order_single, null);
			holder.imgIcon = (ImageView) convertView
					.findViewById(R.id.item_product_order_single_icon);
			holder.txtName = (TextView) convertView
					.findViewById(R.id.item_product_order_single_name);
			holder.txtCount = (TextView) convertView
					.findViewById(R.id.item_product_order_single_count);
			holder.txtAmount = (TextView) convertView
					.findViewById(R.id.item_product_order_single_amount);
			holder.txtPromotion = (TextView) convertView
					.findViewById(R.id.item_product_order_single_txt_promotion);
			holder.edTxtInfo = (EditText) convertView
					.findViewById(R.id.item_product_order_single_edtxt);
			holder.txtTax = (TextView) convertView
					.findViewById(R.id.item_product_order_single_tax);
			holder.txtTrans = (TextView) convertView
					.findViewById(R.id.item_product_order_single_trans);
			holder.relytPromotion = (RelativeLayout) convertView
					.findViewById(R.id.item_product_order_single_relyt_promotion);
			holder.txtAllAmount = (TextView) convertView
					.findViewById(R.id.item_product_order_single_all_amount);
			// holder.llytProductItem = (LinearLayout) convertView
			// .findViewById(R.id.item_product_order_single_item_layout);
			holder.taxLine = (View) convertView
					.findViewById(R.id.item_product_order_single_tax_line);
		} else {
			holder = (ProductOrderHolder) convertView.getTag();
		}

		CartSkuGroupDTO group = groupList.get(position);
		CartSkuDTO dto = group.getSkuList().get(0);
		if (null != dto) {
			bitmapUtils
					.configDefaultLoadFailedImage(R.drawable.img_default_720_360);
			bitmapUtils.display(holder.imgIcon,
					ServiceUrlConstants.getImageHost() + dto.getImgUrl());
			holder.txtName.setText(dto.getpName());
			holder.txtCount.setText(dto.getQty() + "");
			holder.txtAmount.setText(group.getSumPrice().toString());
			holder.txtTax.setText(group.getSumTax().toString());
			if (50 <= group.getSumTax().intValue()) {
				holder.taxLine.setVisibility(View.GONE);
			} else {
				holder.taxLine.setVisibility(View.VISIBLE);
			}
			holder.txtAllAmount.setText(String.format(context
					.getString(R.string.renminbi), group.getSumPrice()
					.toString()));
			// holder.txtTrans.setText("");
		}
		setListeners(holder, position);
		return convertView;
	}

	private void setListeners(ProductOrderHolder holder, int position) {
		holder.relytPromotion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(context, CouponActivity.class);
				context.startActivity(intent);
			}
		});
		// holder.llytProductItem.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// Intent intent = new Intent();
		// intent.setClass(context, ProductDetailsActivity.class);
		// intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT_ID,
		// groupList.get(0).getSkuList().get(0).getSkuId());
		// context.startActivity(intent);
		// }
		// });
	}

	class ProductOrderHolder {

		ImageView imgIcon;

		TextView txtName;

		TextView txtCount;

		TextView txtAmount;

		TextView txtPromotion;

		EditText edTxtInfo;

		TextView txtTax;

		View taxLine;

		TextView txtTrans;

		TextView txtAllAmount;

		RelativeLayout relytPromotion;

		// LinearLayout llytProductItem;
	}

}
