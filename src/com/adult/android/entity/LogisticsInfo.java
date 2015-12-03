package com.adult.android.entity;

import java.util.List;

/**
 * @ClassName: LogisticsInfo
 * @Description:物流信息
 * @author JingYuchuan
 * @date 2015年3月31日 上午10:31:08
 * 
 */
public class LogisticsInfo extends BaseEntity {
	private String waybillno;
	private String express;
	private List<LogisticsInfoItem> items;

	public LogisticsInfo() {
		super();
	}

	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public List<LogisticsInfoItem> getItems() {
		return items;
	}

	public void setItems(List<LogisticsInfoItem> items) {
		this.items = items;
	}
}
