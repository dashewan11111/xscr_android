package com.adult.android.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer totalQty;

	private BigDecimal orderPrice;

	private BigDecimal price;

	private BigDecimal discountPrice;

	private BigDecimal paidPrice;

	private BigDecimal transferPrice;

	private BigDecimal realTransferprice;

	private Long logisticsCarriersId;

	private String logisticsNumber;

	private String logisticsCarriersName;

	private BigDecimal logisticsFee;

	private Short orderType;

	private Long userId;

	private Long addressId;

	private String receiveName;

	private String receivePhone;

	private String receiveMobilePhone;

	private Long receiveProvinceId;

	private Long receiveCityId;

	private Long receiveAreaId;

	private String receiveAddress;

	private String message;

	private Short orderPlatform;

	private String userIp;

	private Short needInvoice;

	private String invoiceTitle;

	private Integer refundQty;

	private BigDecimal refundPrice;

	private Short status;

	private Short isBinning;

	private Integer version;

	private Integer isEvaluate;

	private Date createTime;

	private String createBy;

	private Date lastEditTime;

	private String lastEditBy;

	private Date signTime;

	private String mftNo;

	private BigDecimal weight;

	private BigDecimal volume;

	private String payWay;

	private String deliverySource;

	private String paymentNo;

	private String orderSeqNo;

	private Long payId;

	private String invoiceDetail;

	private String customsCode;

	private BigDecimal totalTax;

	private BigDecimal realTotalTax;

	private String saleTarget;

	private String supplyType;// 货源种类

	private Short sendCode;

	private Timestamp checkTime;// 海关审核时间

	private List<OrderItemDTO> orderItemDTOs;
	private List<ProductOfOrder> productList;

	public Timestamp getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	public Short getSendCode() {
		return sendCode;
	}

	public void setSendCode(Short sendCode) {
		this.sendCode = sendCode;
	}

	public String getSaleTarget() {
		return saleTarget;
	}

	public void setSaleTarget(String saleTarget) {
		this.saleTarget = saleTarget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(BigDecimal paidPrice) {
		this.paidPrice = paidPrice;
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

	public Long getLogisticsCarriersId() {
		return logisticsCarriersId;
	}

	public void setLogisticsCarriersId(Long logisticsCarriersId) {
		this.logisticsCarriersId = logisticsCarriersId;
	}

	public String getLogisticsNumber() {
		return logisticsNumber;
	}

	public void setLogisticsNumber(String logisticsNumber) {
		this.logisticsNumber = logisticsNumber == null ? null : logisticsNumber
				.trim();
	}

	public String getLogisticsCarriersName() {
		return logisticsCarriersName;
	}

	public void setLogisticsCarriersName(String logisticsCarriersName) {
		this.logisticsCarriersName = logisticsCarriersName == null ? null
				: logisticsCarriersName.trim();
	}

	public BigDecimal getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(BigDecimal logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName == null ? null : receiveName.trim();
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone == null ? null : receivePhone.trim();
	}

	public String getReceiveMobilePhone() {
		return receiveMobilePhone;
	}

	public void setReceiveMobilePhone(String receiveMobilePhone) {
		this.receiveMobilePhone = receiveMobilePhone == null ? null
				: receiveMobilePhone.trim();
	}

	public Long getReceiveProvinceId() {
		return receiveProvinceId;
	}

	public void setReceiveProvinceId(Long receiveProvinceId) {
		this.receiveProvinceId = receiveProvinceId;
	}

	public Long getReceiveCityId() {
		return receiveCityId;
	}

	public void setReceiveCityId(Long receiveCityId) {
		this.receiveCityId = receiveCityId;
	}

	public Long getReceiveAreaId() {
		return receiveAreaId;
	}

	public void setReceiveAreaId(Long receiveAreaId) {
		this.receiveAreaId = receiveAreaId;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress == null ? null : receiveAddress
				.trim();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}

	public Short getOrderPlatform() {
		return orderPlatform;
	}

	public void setOrderPlatform(Short orderPlatform) {
		this.orderPlatform = orderPlatform;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp == null ? null : userIp.trim();
	}

	public Short getNeedInvoice() {
		return needInvoice;
	}

	public void setNeedInvoice(Short needInvoice) {
		this.needInvoice = needInvoice;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
	}

	public BigDecimal getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}

	public Short getIsBinning() {
		return isBinning;
	}

	public void setIsBinning(Short isBinning) {
		this.isBinning = isBinning;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy == null ? null : lastEditBy.trim();
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getMftNo() {
		return mftNo;
	}

	public void setMftNo(String mftNo) {
		this.mftNo = mftNo == null ? null : mftNo.trim();
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay == null ? null : payWay.trim();
	}

	public String getDeliverySource() {
		return deliverySource;
	}

	public void setDeliverySource(String deliverySource) {
		this.deliverySource = deliverySource == null ? null : deliverySource
				.trim();
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo == null ? null : paymentNo.trim();
	}

	public String getOrderSeqNo() {
		return orderSeqNo;
	}

	public void setOrderSeqNo(String orderSeqNo) {
		this.orderSeqNo = orderSeqNo == null ? null : orderSeqNo.trim();
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public String getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(String invoiceDetail) {
		this.invoiceDetail = invoiceDetail == null ? null : invoiceDetail
				.trim();
	}

	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	public Integer getRefundQty() {
		return refundQty;
	}

	public void setRefundQty(Integer refundQty) {
		this.refundQty = refundQty;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public List<OrderItemDTO> getOrderItemDTOs() {
		return orderItemDTOs;
	}

	public void setOrderItemDTOs(List<OrderItemDTO> orderItemDTOs) {
		this.orderItemDTOs = orderItemDTOs;
	}

	/**
	 * @return the productList
	 */
	public List<ProductOfOrder> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List<ProductOfOrder> productList) {
		this.productList = productList;
	}

}