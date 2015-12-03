package com.adult.android.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CartSkuGroupDTO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3508800890295772265L;

	private Long mainActivityId; // 商品主活动
	private String title;// 分组名称
	private List<CartSkuDTO> skuList = new LinkedList<CartSkuDTO>();
	private BigDecimal sumPrice;

	private BigDecimal sumDomesticPrice;// 参考价

	private BigDecimal sumTax;// 税

	private BigDecimal transferPrice;// 运费

	private BigDecimal discountPrice;// 折扣金额

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CartSkuGroupDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getSumDomesticPrice() {
		return sumDomesticPrice;
	}

	public void setSumDomesticPrice(BigDecimal sumDomesticPrice) {
		this.sumDomesticPrice = sumDomesticPrice;
	}

	public BigDecimal getSumTax() {
		return sumTax;
	}

	public void setSumTax(BigDecimal sumTax) {
		this.sumTax = sumTax;
	}

	public BigDecimal getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(BigDecimal transferPrice) {
		this.transferPrice = transferPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Long getMainActivityId() {
		return mainActivityId;
	}

	public void setMainActivityId(Long mainActivityId) {
		this.mainActivityId = mainActivityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CartSkuDTO> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<CartSkuDTO> skuList) {
		this.skuList = skuList;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

}
