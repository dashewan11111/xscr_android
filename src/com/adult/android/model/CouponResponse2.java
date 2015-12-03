package com.adult.android.model;

import java.util.List;

import com.adult.android.entity.BaseEntity;
import com.adult.android.entity.CouponDto;

public class CouponResponse2 extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4944871851994296483L;
	private List<CouponDto> couponList;

	public List<CouponDto> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponDto> couponList) {
		this.couponList = couponList;
	}
}
