package com.adult.android.entity;

/**
 * @ClassName: ProductObject
 * @Description
 * @author JingYuchuan
 * @date 2015年3月27日 下午3:07:34
 * 
 */
public class OrderObject extends BaseEntity {
	private long pid;
	private String pName;
	private String imgUrl;

	public OrderObject() {
		super();
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
