package com.adult.android.entity;

public class GetCarsListResponse2 extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8715940867821821504L;

	private CartDto2 cartDTO;

	private String resultMsg;

	public CartDto2 getCartDto2() {
		return cartDTO;
	}

	public void setCartDto2(CartDto2 cartDTO) {
		this.cartDTO = cartDTO;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}
