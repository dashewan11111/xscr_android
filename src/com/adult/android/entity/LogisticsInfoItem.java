package com.adult.android.entity;

/**
 * @ClassName: LogisticsInfoItem
 * @Description: 单条物流信息
 * @author JingYuchuan
 * @date 2015年3月31日 上午10:48:29
 * 
 */
public class LogisticsInfoItem extends BaseEntity {
	private long id;
	private String opertime;
	private String status;
	private String statusdec;

	public LogisticsInfoItem() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOpertime() {
		return opertime;
	}

	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusdec() {
		return statusdec;
	}

	public void setStatusdec(String statusdec) {
		this.statusdec = statusdec;
	}
}
