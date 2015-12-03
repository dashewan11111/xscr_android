package com.adult.android.entity;

import java.util.List;

/**
 * 
 * @author zhaoweiChuang
 * 
 * @2015年4月9日
 * 
 * @descripte
 * 
 *            优惠券 信息 封装
 */
public class CouponCount extends BaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5306363817787833543L;

	private int index;

	private List<String> stockid;

	public List<String> getStockid() {
		return stockid;
	}
	public void setStockid(List<String> stockid) {
		this.stockid = stockid;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CouponCount() {
		super();
		// TODO Auto-generated constructor stub
	}

}
