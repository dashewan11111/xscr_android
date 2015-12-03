package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;
import com.adult.android.entity.ProductForCart;
import com.adult.android.entity.SkuForCart;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.MyEditText2;
import com.adult.android.view.MyEditText2.OnEditNumberListener;
import com.lidroid.xutils.BitmapUtils;

public class FavouriteListAdapter extends BaseAdapter implements
		OnEditNumberListener {

	private Context context;

	private List<ProductForCart> items;

	private BitmapUtils bitmapUtils;

	private OnDataChangeListener onDataChangeListener;

	public FavouriteListAdapter(Context context, List<ProductForCart> items,
			OnDataChangeListener onDateChangeListener) {
		this.context = context;
		this.items = items;
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultLoadFailedImage(R.drawable.img_default_114);
		bitmapUtils.configDefaultLoadingImage(R.drawable.img_default_114);
		this.onDataChangeListener = onDateChangeListener;
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public ProductForCart getItem(int position) {
		return items.get(position);
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
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_favourite_list, null);
			holder.imgCheck = (ImageView) convertView
					.findViewById(R.id.item_favourite_check);
			holder.imgProduct = (ImageView) convertView
					.findViewById(R.id.item_favourite_img_product);
			holder.txtProductName = (TextView) convertView
					.findViewById(R.id.item_favourite_product_name);
			holder.txtProductPrice = (TextView) convertView
					.findViewById(R.id.item_favourite_product_price);
			holder.txtProductFormart = (TextView) convertView
					.findViewById(R.id.item_favourite_product_formart);
			// holder.editCount = (MyEditText2) convertView
			// .findViewById(R.id.item_favourite_edit_count);
			holder.llytDisable = (LinearLayout) convertView
					.findViewById(R.id.item_favourite_llyt_disable);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ProductForCart procut = (ProductForCart) getItem(position);

		SkuForCart sku = procut.getSkuList().get(0);
		if (procut.isChecked()) {
			holder.imgCheck.setImageResource(R.drawable.cart_product_select_on);
		} else {
			holder.imgCheck
					.setImageResource(R.drawable.cart_product_select_off);
		}
		holder.txtProductName.setText(procut.getTitle());
		holder.txtProductPrice.setText(sku.getPrice());
		holder.txtProductFormart.setText(sku.getSkuName());
		holder.imgCheck.setOnClickListener(new ItemCartClickListner(position));

		// bitmapUtils.display(holder.imgProduct,
		// ServiceUrlConstants.getImageHost()+procut);
		holder.editCount.setOnAddReduceClickListener(this, 0, position);
		holder.editCount.setTag(sku);
		holder.editCount.setNums(sku.getQty());
		holder.editCount.setMaxNumber(sku.getStockQty());
		// addImages(holder.llytImageContainer, topic.getTopicImageList());
		if (00 == sku.getIsSoldOut()) {
			holder.llytDisable.setVisibility(View.VISIBLE);
		} else {
			holder.llytDisable.setVisibility(View.GONE);
		}
		holder.llytDisable.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
			}
		});
		return convertView;
	}

	class ItemCartClickListner implements OnClickListener {

		private int position;

		public ItemCartClickListner(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.item_favourite_check:
				if (getItem(position).isChecked()) {
					onDataChangeListener.onChecedChange(position, false);
				} else {
					onDataChangeListener.onChecedChange(position, true);
				}
				break;

			default:
				break;
			}
		}
	}

	private class ViewHolder {

		private ImageView imgCheck;

		private ImageView imgProduct;

		private TextView txtProductName;

		private TextView txtProductPrice;

		private TextView txtProductFormart;

		private MyEditText2 editCount;

		private LinearLayout llytDisable;

	}

	@Override
	public void onAddClick(int position1, int position2, View v,
			int previousNum, int currentNum, Object tag) {
		onDataChangeListener.onSkuCountChange(position2, currentNum);
	}

	@Override
	public void onReduceClick(int position1, int position2, View v,
			int previousNum, int currentNum, Object tag) {
		ToastUtil.showToastLong(context, position2 + ", currentNum : "
				+ currentNum);
		onDataChangeListener.onSkuCountChange(position2, currentNum);
	}

	@Override
	public void onMaxQty(int position1, int position2, View v, int max) {
		ToastUtil.showToastLong(context, position2 + "");
	}

	@Override
	public void onInputNumberDone(int position1, int position2, int number,
			int max, Object tag) {
		ToastUtil.showToastLong(context, position2 + "");
	}

	public interface OnDataChangeListener {

		void onSkuCountChange(int position, int count);

		void onChecedChange(int position, boolean isChecked);

		void onSkuDelete(int position);
	}
}
