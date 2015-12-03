package com.adult.android.entity;

/**
 * @ClassName: AddToCarsmessage
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年4月29日 下午7:09:12
 * 
 */

public class AddToCarsmessage extends BaseEntity {

	private String message;

	public AddToCarsmessage() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
