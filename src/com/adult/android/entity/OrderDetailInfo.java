package com.adult.android.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: OrderDetailInfo
 * @Description: 订单详情
 * @author JingYuchuan
 * @date 2015年3月28日 下午3:59:19
 * 
 */
public class OrderDetailInfo extends BaseEntity {
	private long id;
	private int totalQty;
	private BigDecimal orderPrice;
	private BigDecimal price;
	private BigDecimal paidPrice;
	private BigDecimal discountPrice;
	private BigDecimal transferPrice;
	private BigDecimal realTransferprice;
	private BigDecimal refundPrice;
	private BigDecimal totalTax;
	private BigDecimal realTotalTax;
	private String userId;
	private String addressId;
	private int receiveProvinceId;
	private int receiveCityId;
	private int receiveAreaId;
	private int orderPlatform;
	private short needInvoice;
	private short isBinning;
	private short isEvaluate;
	private int refundQty;
	private int version;
	private String receiveName;
	private String supplyType;
	private String receiveMobilePhone;
	private String receiveAddress;
	private int status;
	private long createTime;
	private long lastEditTime;
	private String createBy;
	private String lastEditBy;
	private float weight;
	private float volume;
	private String payId;
	private String payWay;
	private String customsCode;
	private String saleTarget;
	private String deliverySource;
	private List<ProductObject> orderItemDTOs;
	private List<Coupon> orderPromoteRelations;
	private List<OrderLogisticsMsg> orderMsgs;
	private List<ShipOrderDTO> shipOrderDTOs;
	private String message;

	public OrderDetailInfo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(BigDecimal paidPrice) {
		this.paidPrice = paidPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(BigDecimal transferPrice) {
		this.transferPrice = transferPrice;
	}

	public BigDecimal getRealTransferprice() {
		return realTransferprice;
	}

	public void setRealTransferprice(BigDecimal realTransferprice) {
		this.realTransferprice = realTransferprice;
	}

	public BigDecimal getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getRealTotalTax() {
		return realTotalTax;
	}

	public void setRealTotalTax(BigDecimal realTotalTax) {
		this.realTotalTax = realTotalTax;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public int getReceiveProvinceId() {
		return receiveProvinceId;
	}

	public void setReceiveProvinceId(int receiveProvinceId) {
		this.receiveProvinceId = receiveProvinceId;
	}

	public int getReceiveCityId() {
		return receiveCityId;
	}

	public void setReceiveCityId(int receiveCityId) {
		this.receiveCityId = receiveCityId;
	}

	public int getReceiveAreaId() {
		return receiveAreaId;
	}

	public void setReceiveAreaId(int receiveAreaId) {
		this.receiveAreaId = receiveAreaId;
	}

	public int getOrderPlatform() {
		return orderPlatform;
	}

	public void setOrderPlatform(int orderPlatform) {
		this.orderPlatform = orderPlatform;
	}

	public short getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(short needInvoice) {
		this.needInvoice = needInvoice;
	}

	public short getIsBinning() {
		return isBinning;
	}

	public void setIsBinning(short isBinning) {
		this.isBinning = isBinning;
	}

	public short getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(short isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public int getRefundQty() {
		return refundQty;
	}

	public void setRefundQty(int refundQty) {
		this.refundQty = refundQty;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getReceiveMobilePhone() {
		return receiveMobilePhone;
	}

	public void setReceiveMobilePhone(String receiveMobilePhone) {
		this.receiveMobilePhone = receiveMobilePhone;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(long lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getSaleTarget() {
		return saleTarget;
	}

	public void setSaleTarget(String saleTarget) {
		this.saleTarget = saleTarget;
	}

	public String getDeliverySource() {
		return deliverySource;
	}

	public void setDeliverySource(String deliverySource) {
		this.deliverySource = deliverySource;
	}

	public List<ProductObject> getOrderItemDTOs() {
		return orderItemDTOs;
	}

	public void setOrderItemDTOs(List<ProductObject> orderItemDTOs) {
		this.orderItemDTOs = orderItemDTOs;
	}

	public List<Coupon> getOrderPromoteRelations() {
		return orderPromoteRelations;
	}

	public void setOrderPromoteRelations(List<Coupon> orderPromoteRelations) {
		this.orderPromoteRelations = orderPromoteRelations;
	}


	public List<OrderLogisticsMsg> getOrderMsgs() {
		return orderMsgs;
	}

	public void setOrderMsgs(List<OrderLogisticsMsg> orderMsgs) {
		this.orderMsgs = orderMsgs;
	}

	/**
	 * @return the shipOrderDTOs
	 */
	public List<ShipOrderDTO> getShipOrderDTOs() {
		return shipOrderDTOs;
	}

	/**
	 * @param shipOrderDTOs
	 *            the shipOrderDTOs to set
	 */
	public void setShipOrderDTOs(List<ShipOrderDTO> shipOrderDTOs) {
		this.shipOrderDTOs = shipOrderDTOs;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
