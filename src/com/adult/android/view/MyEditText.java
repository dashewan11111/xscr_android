package com.adult.android.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.adult.android.R;
import com.adult.android.entity.CartSkuDTO;

public class MyEditText extends LinearLayout implements TextWatcher,
		OnEditorActionListener, View.OnClickListener {
	private OnEditNumberListener mOnAddReduceClickListener;
	private OnEditTextClickListener mOnEditTextClickListener;
	private EditText mEditText;

	public EditText getEditText() {
		return mEditText;
	}

	private ImageView bAdd;
	private ImageView bReduce;
	private String number = "1";
	private int maxNumber = -1;
	private Object tag;

	// 这里的构造一定是两个参数。
	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private boolean isValidNumber() {
		if (!TextUtils.isEmpty(number)) {
			try {
				Integer.parseInt(number);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
		return false;
	}

	private void overrideSetText(String text) {
		mEditText.setText(text);
		mEditText.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());
	}

	protected void onFinishInflate() {
		super.onFinishInflate();
		LayoutInflater.from(getContext()).inflate(R.layout.mynumedittext, this);
		mEditText = (EditText) findViewById(R.id.edt_num);
		mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
		mEditText.setOnEditorActionListener(this);
		mEditText.setOnClickListener(this);
		mEditText.setFocusable(false);
		mEditText.setFocusableInTouchMode(false);

		bAdd = (ImageView) findViewById(R.id.btn_num_product_plus);
		bReduce = (ImageView) findViewById(R.id.btn_num_product_reduce);
		overrideSetText(number);
		mEditText.addTextChangedListener(this);
		bReduce.setEnabled(false);

		bAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (null != v.getTag()
						&& null != ((CartSkuDTO) v.getTag()).getIsSoldOut()
						&& 0 == ((CartSkuDTO) v.getTag()).getIsSoldOut()) {
					bReduce.setEnabled(false);
					return;
				}
				int tempNumber = Integer.parseInt(number);
				if (isValidNumber()) {
					if (tempNumber > 1) {
						bReduce.setEnabled(true);
					}
					if (-1 != maxNumber && tempNumber >= maxNumber) {
						if (mOnAddReduceClickListener != null) {
							mOnAddReduceClickListener.onMaxQty(v, maxNumber);
						}
						return;
					}

					tempNumber++;
					number = String.valueOf(tempNumber);

					// overrideSetText(number);
					if (mOnAddReduceClickListener != null) {
						mOnAddReduceClickListener.onAddClick(v, tempNumber - 1,
								tempNumber, v.getTag());
					}
				} else {
					number = "1";
					// overrideSetText(number);
					if (mOnAddReduceClickListener != null) {
						mOnAddReduceClickListener.onAddClick(v, 0, 1,
								v.getTag());
					}
				}
				checkNum(number);
			}
		});

		bReduce.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (null != v.getTag()
						&& null != ((CartSkuDTO) v.getTag()).getIsSoldOut()
						&& 0 == ((CartSkuDTO) v.getTag()).getIsSoldOut()) {
					bReduce.setEnabled(false);
					return;
				}
				if (isValidNumber()) {
					int tempNumber = Integer.parseInt(number);
					if (tempNumber <= 1) {
						return;
					}
					if (tempNumber == 2) {
						bReduce.setEnabled(false);
					}
					tempNumber--;
					number = String.valueOf(tempNumber);
					// overrideSetText(number);
					if (mOnAddReduceClickListener != null) {
						mOnAddReduceClickListener.onReduceClick(v,
								tempNumber + 1, tempNumber, v.getTag());
					}
				} else {
					number = "";
					// overrideSetText(number);
				}
				checkNum(number);
			}
		});
	}

	public OnEditNumberListener getOnAddReduceClickListener() {
		return mOnAddReduceClickListener;
	}

	@Override
	public void setTag(Object tag) {
		super.setTag(tag);
		this.tag = tag;
		bAdd.setTag(tag);
		bReduce.setTag(tag);
	}

	public void setOnAddReduceClickListener(
			OnEditNumberListener mOnAddReduceClickListener) {
		this.mOnAddReduceClickListener = mOnAddReduceClickListener;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		try {
			if (s == null || TextUtils.isEmpty(s.toString())) {
				number = "";
			} else {
				number = s.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			number = "";
		}
		if (isValidNumber()) {
			int tempNumber = Integer.parseInt(number);
			bAdd.setEnabled(true);
			if (tempNumber > 1) {
				bReduce.setEnabled(true);
			} else {
				bReduce.setEnabled(false);
			}
		} else {
			bAdd.setEnabled(false);
			bReduce.setEnabled(false);
		}
	}

	private void checkNum(CharSequence s) {
		if (s.toString().startsWith("0")) {
			number = s.toString().substring(1);
			overrideSetText(number);
			return;
		}
		if (-1 != maxNumber) {
			if (s != null && !TextUtils.isEmpty(s.toString())
					&& maxNumber < Integer.parseInt(s.toString())) {
				number = "" + maxNumber;
			} else {
				number = "9999";
			}
		} else {
			if (s != null && !TextUtils.isEmpty(s.toString())
					&& 9999 < Integer.parseInt(s.toString())) {
				number = "9999";
			}
		}
	}

	public void setNums(int num) {
		if (num < 0)
			return;
		number = String.valueOf(num);
		bAdd.setEnabled(true);
		if (num == 1 || num == 0) {
			bReduce.setEnabled(false);
		} else {
			bReduce.setEnabled(true);
		}
		overrideSetText(number);
	}

	public void setOnEditTextClickListener(OnEditTextClickListener l) {
		mOnEditTextClickListener = l;
	}

	public int getNums() {
		return isValidNumber() ? Integer.parseInt(number) : -1;
	}

	public void setEditable(boolean b) {
		bReduce.setEnabled(b);
		bAdd.setEnabled(b);
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public static interface OnEditNumberListener {
		void onAddClick(View v, int previousNum, int currentNum, Object tag);

		void onReduceClick(View v, int previousNum, int currentNum, Object tag);

		void onMaxQty(View v, int max);

		void onInputNumberDone(int number, int max, Object tag);
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			if (isValidNumber() && mOnAddReduceClickListener != null) {
				mOnAddReduceClickListener.onInputNumberDone(
						Integer.parseInt(number), maxNumber, this.tag);
			}
			return true;
		}
		return false;
	}

	public static interface OnEditTextClickListener {
		void onClick(EditText editText, MyEditText myEditText);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_num:
			if (mOnEditTextClickListener != null) {
				mOnEditTextClickListener.onClick(mEditText, MyEditText.this);
			}
			break;
		}
	}
}