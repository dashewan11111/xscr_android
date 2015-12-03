package com.adult.android.view.product;
import android.content.Context;
import android.content.DialogInterface;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.adult.android.R;
import com.adult.android.utils.ToastUtil;
import com.adult.android.view.MyEditText;
import com.adult.android.view.SampleDialog;

/**
 * @ClassName: EditProductNumberDialog
 * @Description:
 * @author JingYuchuan
 * @date 2015年5月25日 下午5:00:10
 * 
 */
public abstract class EditProductNumberDialog extends SampleDialog {
	private MyEditText editText;

	public EditProductNumberDialog(Context context) {
		super(context, R.style.sample_edittext_dialog);
		setTitleText(R.string.edit_product_number, 0);
		setCancelable(false);
		editText.getEditText().setFocusable(true);
		editText.getEditText().setFocusableInTouchMode(true);
		setTwoButton(R.string.cancel, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				InputMethodManager imm = (InputMethodManager) getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				IBinder iBinder = editText.getWindowToken();
				if (iBinder != null) {
					imm.hideSoftInputFromWindow(iBinder,
							InputMethodManager.RESULT_UNCHANGED_SHOWN);
				}
				dialog.dismiss();
			}
		}, R.string.ok, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				int number = editText.getNums();
				if (number > 0) {
					InputMethodManager imm = (InputMethodManager) getContext()
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					IBinder iBinder = editText.getWindowToken();
					if (iBinder != null) {
						imm.hideSoftInputFromWindow(iBinder,
								InputMethodManager.RESULT_UNCHANGED_SHOWN);
					}
					onClickOk(number);
					dialog.dismiss();
				} else if (number == 0) {
					ToastUtil.showToastShort(getContext(),
							R.string.number_less_than_0);
				} else {
					ToastUtil.showToastShort(getContext(),
							R.string.please_input_number);
				}
			}
		});
	}

	@Override
	public View getContentView() {
		View view = LayoutInflater.from(context).inflate(
				R.layout.edit_product_number_dialog, null);
		editText = (MyEditText) view.findViewById(R.id.edit_count_edit_text);
		return view;
	}

	public void setNumber(int number) {
		editText.setNums(number);
	}

	public int getNumber() {
		return editText.getNums();
	}

	public void setMaxNumber(int number) {

		editText.setMaxNumber(number);
	}

	public void setTitleText(String title) {

		setTitleText(title, 0);
	}

	public abstract void onClickOk(int number);

}
