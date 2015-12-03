package com.adult.android.presenter.fragment.main.tab.adapter;

import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;

import com.adult.android.R;

/**
 * @ClassName: InvoiceInfoListViewAdapter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年3月4日 上午10:48:46
 * 
 */
public class InvoiceInfoListViewAdapter extends BaseAdapter {

	private Context context;
	private String[] beans;
	// 用于记录每个RadioButton的状态，并保证只可选一个
	HashMap<String, Boolean> states = new HashMap<String, Boolean>();

	class ViewHolder {

		RadioButton rb_state;
		EditText editText;
	}

	public InvoiceInfoListViewAdapter(Context context, String[] beans) {
		this.beans = beans;
		this.context = context;
		states.put("0", true);
	}

	@Override
	public int getCount() {
		return beans.length;
	}

	@Override
	public Object getItem(int position) {
		return beans[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// 页面
		ViewHolder holder;
		String bean = beans[position];
		LayoutInflater inflater = LayoutInflater.from(context);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.invoice_info_list_item,
					null);
			holder = new ViewHolder();
			// holder.rb_state = (RadioButton) convertView
			// .findViewById(R.id.rb_light);
			holder.editText = (EditText) convertView
					.findViewById(R.id.edit_context);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final RadioButton radio = (RadioButton) convertView
				.findViewById(R.id.radio_btn_invoice);
		holder.rb_state = radio;
		holder.rb_state.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				// 重置，确保最多只有一项被选中
				for (String key : states.keySet()) {
					states.put(key, false);

				}
				states.put(String.valueOf(position), radio.isChecked());
				InvoiceInfoListViewAdapter.this.notifyDataSetChanged();
			}
		});

		boolean res = false;
		if (states.get(String.valueOf(position)) == null
				|| states.get(String.valueOf(position)) == false) {
			res = false;
			states.put(String.valueOf(position), false);
		} else
			res = true;

		holder.rb_state.setChecked(res);
		holder.rb_state.setText(bean);
		if (res) {
			holder.editText.setVisibility(View.VISIBLE);
		} else {
			holder.editText.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}
}
