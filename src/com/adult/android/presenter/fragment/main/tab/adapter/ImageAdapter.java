package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adult.android.R;

/**
 * @ClassName: ImageAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年3月4日 下午3:28:42
 * 
 */
public class ImageAdapter extends BaseAdapter {
	private Context mContext; // 定义Context
	// // 定义一个向量作为图片源
	// private Vector<Integer> mImageIds = new Vector<Integer>();
	// 定义一个向量作为选中与否容器
	private Vector<Boolean> mImage_bs = new Vector<Boolean>();

	private List<String> list;

	// 记录上一次选中的图片位置，-1表示未选中任何图片
	private int lastPosition = -1;
	// 表示当前适配器是否允许多选
	private boolean multiChoose;

	public ImageAdapter(Context c, boolean isMulti, List<String> list) {
		mContext = c;
		multiChoose = isMulti;
		this.list = list;
		// // 装入资源
		// mImageIds.add(R.drawable.item1);
		// mImageIds.add(R.drawable.item2);
		// mImageIds.add(R.drawable.item3);
		// mImageIds.add(R.drawable.item4);
		// mImageIds.add(R.drawable.item5);
		for (int i = 0; i < list.size(); i++) {
			mImage_bs.add(false);
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHoder viewHoder = null;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.grid_view_item, null);
			viewHoder = new ViewHoder();
			viewHoder.textView = (TextView) convertView
					.findViewById(R.id.txt_name);
			convertView.setTag(viewHoder);
			// textView = new TextView(mContext);
			// textView.setLayoutParams(new GridView.LayoutParams(150, 80));
			// textView.setText("黑色");
			// textView.setTextColor(color.red);
			// textView.setGravity(Gravity.CENTER);
			// // textView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		} else {
			viewHoder = (ViewHoder) convertView.getTag();
		}

		viewHoder.textView.setText(list.get(position));
		if (mImage_bs.elementAt(position)) {
			viewHoder.textView.setTextColor(mContext.getResources().getColor(
					R.color.purple));
			viewHoder.textView
					.setBackgroundResource(R.drawable.round_button_selected);

		} else {
			viewHoder.textView.setTextColor(mContext.getResources().getColor(
					R.color.grey));
			viewHoder.textView
					.setBackgroundResource(R.drawable.round_button_unselected);
		}
		// imageView.setImageDrawable(makeBmp(mImageIds.elementAt(position),
		// mImage_bs.elementAt(position)));
		return convertView;
	}

	// private LayerDrawable makeBmp(int id, boolean isChosen) {
	// Bitmap mainBmp = ((BitmapDrawable) mContext.getResources().getDrawable(
	// id)).getBitmap();
	//
	// // 根据isChosen来选取对勾的图片
	// Bitmap selectedBmp;
	// if (isChosen == true) {
	// selectedBmp = BitmapFactory.decodeResource(mContext.getResources(),
	// R.drawable.btncheck_yes);
	// } else {
	// selectedBmp = BitmapFactory.decodeResource(mContext.getResources(),
	// R.drawable.btncheck_no);
	// }
	//
	// // 产生叠加图
	// Drawable[] array = new Drawable[2];
	// array[0] = new BitmapDrawable(mainBmp);
	// array[1] = new BitmapDrawable(selectedBmp);
	// LayerDrawable la = new LayerDrawable(array);
	// la.setLayerInset(0, 0, 0, 0, 0);
	// la.setLayerInset(1, 0, -5, 60, 45);
	//
	// return la; // 返回叠加后的图
	// }

	// 修改选中的状态
	public void changeState(int position) {
		// 多选时
		if (multiChoose == true) {
			mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接
		} else { // 单选时
			if (lastPosition != -1) {
				// 取消上一次的选中状态
				mImage_bs.setElementAt(false, lastPosition);
			}
			// 直接取反即可
			mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);
			lastPosition = position; // 记录本次选中的位置
		}
		notifyDataSetChanged(); // 通知适配器进行更新
	}

	public class ViewHoder {
		TextView textView;
	}
}
