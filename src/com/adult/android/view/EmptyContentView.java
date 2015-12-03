package com.adult.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adult.android.R;

public class EmptyContentView extends LinearLayout {

	private ImageView image;
	private TextView text;
	public EmptyContentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.empty_content, this);
		 image=(ImageView)findViewById(R.id.empty_content_image);
		 text=(TextView)findViewById(R.id.empty_content_text);
	}

	public void setImage(int id) {
		image.setBackgroundResource(id);
	}
	
	public void setTextContent(int textid) {
		text.setText(getResources().getString(textid));
	}

	
}
