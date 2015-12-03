package com.adult.android.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;
/**
 * @ClassName: SampleDialog
 * @Description: 默认样式的Dialog
 * @author JingYuchuan
 * @date 2015年4月25日 上午10:03:07
 * 
 */
public abstract class SampleDialog extends Dialog {
	private FrameLayout titleContanier, contentContainer;
	private TextView defaultTitleTV, defaultContentTV;
	private TextView button1, button2;
	private View buttonsDividingLine;
	protected Context context;

	private View sample_dialog_title_divide;

	public SampleDialog(Context context) {
		super(context, R.style.sample_dialog);
		init(context);
	}

	public SampleDialog(Context context, int theme) {
		super(context, theme);
		init(context);
	}

	private void init(Context context) {
		this.context = context;
		LinearLayout view = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.sample_dialog_layout, null);
		titleContanier = (FrameLayout) view
				.findViewById(R.id.sample_dialog_title_container);
		sample_dialog_title_divide = view
				.findViewById(R.id.sample_dialog_title_divide);
		contentContainer = (FrameLayout) view
				.findViewById(R.id.sample_dialog_content_container);

		defaultTitleTV = (TextView) titleContanier
				.findViewById(R.id.sample_dialog_title_default_tv);
		defaultContentTV = (TextView) contentContainer
				.findViewById(R.id.sample_dialog_default_content_tv);
		View newContentView = getContentView();
		if (newContentView != null) {
			contentContainer.removeAllViews();
			contentContainer.addView(newContentView,
					new FrameLayout.LayoutParams(
							FrameLayout.LayoutParams.MATCH_PARENT,
							FrameLayout.LayoutParams.WRAP_CONTENT));
			sample_dialog_title_divide.setVisibility(View.VISIBLE);
		} else {
			contentContainer.setVisibility(View.GONE);
			sample_dialog_title_divide.setVisibility(View.GONE);
		}
		button1 = (TextView) view
				.findViewById(R.id.sample_dialog_default_button_1);
		button2 = (TextView) view
				.findViewById(R.id.sample_dialog_default_button_2);
		buttonsDividingLine = view
				.findViewById(R.id.sample_dialog_default_button_dividing_line);
		super.setContentView(view);
	}

	/**
	 * @Description: 设置默认的标题文字
	 * 
	 * @param text
	 *            标题文字内容
	 * @param color
	 *            标题文字颜色, 默认传入0
	 */
	public void setTitleText(String text, int color) {
		if (!TextUtils.isEmpty(text)) {
			defaultTitleTV.setText(text);
		}
		if (color > 0) {
			defaultTitleTV.setTextColor(context.getResources().getColor(color));
		}
	}

	/**
	 * @Description: 设置默认的标题文字
	 * 
	 * @param textRes
	 *            标题文字内容资源
	 * @param color
	 *            标题文字颜色, 默认传入0
	 */
	public void setTitleText(int textRes, int color) {
		if (textRes > 0) {
			defaultTitleTV.setText(textRes);
		}
		if (color > 0) {
			defaultTitleTV.setTextColor(context.getResources().getColor(color));
		}
	}

	/**
	 * @Description: 设置默认的内容文字
	 * 
	 * @param text
	 *            内容文字内容
	 * @param color
	 *            内容文字颜色, 默认传入0
	 */
	public void setContentText(String text, int color) {
		if (!TextUtils.isEmpty(text)) {
			contentContainer.setVisibility(View.VISIBLE);
			sample_dialog_title_divide.setVisibility(View.VISIBLE);
			defaultContentTV.setText(text);
		}
		if (color > 0) {
			defaultContentTV.setTextColor(context.getResources()
					.getColor(color));
		}
	}

	/**
	 * @Description: 设置默认的内容文字
	 * 
	 * @param textRes
	 *            内容文字内容资源
	 * @param color
	 *            内容文字颜色, 默认传入0
	 */
	public void setContentText(int textRes, int color) {
		if (textRes > 0) {
			contentContainer.setVisibility(View.VISIBLE);
			sample_dialog_title_divide.setVisibility(View.VISIBLE);
			defaultContentTV.setText(textRes);
		}
		if (color > 0) {
			defaultContentTV.setTextColor(context.getResources()
					.getColor(color));
		}
	}

	/**
	 * @Description: 设置默认的内容gravity
	 * 
	 * @param gravity
	 * 
	 */
	public void setContentTextGravity(int gravity) {
		contentContainer.setVisibility(View.VISIBLE);
		sample_dialog_title_divide.setVisibility(View.VISIBLE);
		defaultContentTV.setGravity(gravity);
	}

	/**
	 * @Description: 设置单独的按钮
	 * 
	 * @param btnText
	 *            按钮文字内容
	 * @param color
	 *            按钮文字颜色, 默认传入0
	 * @param l
	 *            按钮点击监听
	 */
	public void setSingleButton(String btnText, int color,
			final OnClickListener l) {
		button1.setText(btnText);
		if (color > 0) {
			button1.setTextColor(context.getResources().getColor(color));
		}
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				l.onClick(SampleDialog.this, 0);
			}
		});
		buttonsDividingLine.setVisibility(View.GONE);
		button2.setVisibility(View.GONE);
	}

	/**
	 * @Description: 设置单独的按钮
	 * 
	 * @param btnTextRes
	 *            按钮文字内容资源id
	 * @param color
	 *            按钮文字颜色, 默认传入0
	 * @param l
	 *            按钮点击监听
	 */
	public void setSingleButton(int btnTextRes, int color,
			final OnClickListener l) {
		String text = context.getString(btnTextRes);
		setSingleButton(text, color, l);
	}

	/**
	 * @Description: 设置两个按钮
	 * 
	 * @param firstBtnText
	 *            第一个按钮文字内容
	 * @param firstBtnColor
	 *            第一个按钮文字颜色, 默认传入0
	 * @param firstListener
	 *            第一个按钮点击监听
	 * @param secondBtnText
	 *            第二个按钮文字内容
	 * @param secondBtnColor
	 *            第二个按钮文字颜色, 默认传入0
	 * @param secondListener
	 *            第二个按钮点击监听
	 */
	public void setTwoButton(String firstBtnText, int firstBtnColor,
			final OnClickListener firstListener, String secondBtnText,
			int secondBtnColor, final OnClickListener secondListener) {
		if (!TextUtils.isEmpty(firstBtnText)) {
			button1.setText(firstBtnText);
		}
		if (firstBtnColor > 0) {
			button1.setTextColor(context.getResources().getColor(firstBtnColor));
		}
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				firstListener.onClick(SampleDialog.this, 0);
			}
		});
		if (!TextUtils.isEmpty(secondBtnText)) {
			button2.setText(secondBtnText);
		}
		if (secondBtnColor > 0) {
			button2.setTextColor(context.getResources()
					.getColor(secondBtnColor));
		}
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				secondListener.onClick(SampleDialog.this, 1);
			}
		});
	}

	/**
	 * @Description: 设置两个按钮
	 * 
	 * @param firstBtnTextRes
	 *            第一个按钮文字内容资源id
	 * @param firstBtnColor
	 *            第一个按钮文字颜色
	 * @param firstListener
	 *            第一个按钮点击监听
	 * @param secondBtnTextRes
	 *            第二个按钮文字内容资源id
	 * @param secondBtnColor
	 *            第二个按钮文字颜色
	 * @param secondListener
	 *            第二个按钮点击监听
	 */
	public void setTwoButton(int firstBtnTextRes, int firstBtnColor,
			final OnClickListener firstListener, int secondBtnTextRes,
			int secondBtnColor, final OnClickListener secondListener) {
		String firstText = firstBtnTextRes > 0 ? context
				.getString(firstBtnTextRes) : "";
		String secondText = secondBtnTextRes > 0 ? context
				.getString(secondBtnTextRes) : "";
		setTwoButton(firstText, firstBtnColor, firstListener, secondText,
				secondBtnColor, secondListener);
	}

	public void hideTitle() {
		titleContanier.setVisibility(View.GONE);
	}

	public abstract View getContentView();

}
