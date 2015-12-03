package com.adult.android.entity;

import java.util.List;

/**
 * @ClassName: ShipOrderDTO
 * @Description:
 * @author jingYuchuan
 * @date 2015年4月24日 上午10:28:00
 * 
 */
public class ShipOrderDTO extends BaseEntity {
	private String packNo;
	private String logisticsNumber;
	private String logisticsCarriersName;
	private String dealerName;
	private long createTime;
	private short status;
	private List<ShipItemDto> shipItemDtoList;

	public ShipOrderDTO() {
		super();
	}

	public String getLogisticsNumber() {
		return logisticsNumber;
	}

	public void setLogisticsNumber(String logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}

	public String getLogisticsCarriersName() {
		return logisticsCarriersName;
	}

	public void setLogisticsCarriersName(String logisticsCarriersName) {
		this.logisticsCarriersName = logisticsCarriersName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public List<ShipItemDto> getShipItemDtoList() {
		return shipItemDtoList;
	}

	public void setShipItemDtoList(List<ShipItemDto> shipItemDtoList) {
		this.shipItemDtoList = shipItemDtoList;
	}

	/**
	 * @return the packNo
	 */
	public String getPackNo() {
		return packNo;
	}

	/**
	 * @param packNo
	 *            the packNo to set
	 */
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
}
