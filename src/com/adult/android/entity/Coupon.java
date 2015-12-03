package com.adult.android.entity;

/**
 * @ClassName: Coupon
 * @Description: 优惠券
 * @author JingYuchuan
 * @date 2015年3月28日 下午4:21:10
 * 
 */
public class Coupon extends BaseEntity {
	private long activeCouponId;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getActiveCouponId() {
		return activeCouponId;
	}

	public void setActiveCouponId(long activeCouponId) {
		this.activeCouponId = activeCouponId;
	}

}
