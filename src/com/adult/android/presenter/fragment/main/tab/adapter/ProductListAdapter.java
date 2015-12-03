package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.Product2ForList;
import com.adult.android.model.constants.ServiceUrlConstants;
import com.adult.android.presenter.activity.ProductListActivity;
import com.lidroid.xutils.BitmapUtils;

public class ProductListAdapter extends BaseAdapter {

	private Context context;

	private List<Product2ForList> productList;

	private BitmapUtils bitmapUtils;

	public ProductListAdapter(Context context, List<Product2ForList> productList) {
		this.context = context;
		this.productList = productList;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
	}

	@Override
	public int getCount() {
		return productList == null ? 0 : productList.size();
	}

	@Override
	public Object getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			if (ProductListActivity.isGrid) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item_product_list_g, null);
			} else {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item_product_list_l, null);
			}
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_product_list_image);
			holder.txtName = (TextView) convertView
					.findViewById(R.id.item_product_list_name);
			holder.txtDesc = (TextView) convertView
					.findViewById(R.id.item_product_list_desc);
			holder.txtPrice = (TextView) convertView
					.findViewById(R.id.item_product_list_price);
			holder.txtSalesAmount = (TextView) convertView
					.findViewById(R.id.item_product_list_salesamount);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Product2ForList product = (Product2ForList) getItem(position);
		// Sku2 sku = product.getSkuList().get(0);
		holder.txtName.setText(product.getName());
		holder.txtDesc.setText(product.getName());
		holder.txtPrice.setText(product.getPrice());
		holder.txtSalesAmount.setText("销量:" + product.getSalesVolume());

		bitmapUtils.display(holder.image, ServiceUrlConstants.getImageHost()
				+ product.getImgUrl());

		return convertView;
	}

	private class ViewHolder {

		private ImageView image;

		private TextView txtName;

		private TextView txtDesc;

		private TextView txtPrice;

		private TextView txtSalesAmount;

	}
}
