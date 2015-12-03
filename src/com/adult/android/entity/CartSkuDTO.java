package com.adult.android.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.adult.android.model.ProductDetailModel;

public class CartSkuDTO implements Serializable {

	private static final long serialVersionUID = -4203913957875035757L;

	private String categoryId;// 商品类目

	private String brandId;// 商品品牌

	private Long mainActivityId; // 商品主活动

	private Long pid;

	private String pName;

	private Long skuId;

	private String skuName;

	private Integer qty;// 数量

	private BigDecimal price;// 原价格

	// 促销价格，不再使用
	// private BigDecimal promotionPrice;

	private BigDecimal domesticPrice;// 参考价

	private String originPlaceId;// 原产国ID

	private String originPlaceName;// 原产国名称

	private Integer stockQty;// 库存数量

	private Short isSoldOut;// 是否上下架

	private Date lastTime;// 修改时间

	private String imgUrl;// 商品图片地址

	private BigDecimal tax;// 税

	private Double exchangeRange; // 税率

	private boolean isSelect;

	private String skuNameEn; // sku 英文名
	private String unit; // 单位名称
	private BigDecimal subTotalPrice; // 价格小计
	private String createBy;
	private BigDecimal weight; // 重量
	private BigDecimal volume; // 体积
	private BigDecimal straightDownPrice;// 直降金额（非直降后金额）
	private BigDecimal fullcutPrice;
	private String hsCode;// 海关编号

	private Integer stockNum;

	private String customsCode;// 关区代码

	private Short orderType;

	private String productType;// 商品类型（海外直邮、保税区发货、国内发货）

	private List<OrderPromoteRelationDTO> promoteRelationDTOs; // 优惠券

	private TcCountry tcCountry; // 原产国等信息

	private boolean freePostage;
	private String businessId;// 类目
	private String goodsId;// 商品
	// 访问方式 1 B2B_PC 2.B2B_PAD 3.B2C_PC 4.B2C_WAP 5.B2C_APP
	private Long accessMode;
	private int isGift;
	private long mainRuleId = ProductDetailModel.PromotionType.WuHuodong
			.getTypeId()[0];
	private long mainRuleTerm;
	private String mainRuleName;
	private List<RuleListEntity> ruleList;
	private boolean isCheckedToDelete;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CartSkuDTO() {
		super();
	}

	public int getIsGift() {
		return isGift;
	}

	public void setIsGift(int isGift) {
		this.isGift = isGift;
	}

	public boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public BigDecimal getStraightDownPrice() {
		return straightDownPrice;
	}

	public void setStraightDownPrice(BigDecimal straightDownPrice) {
		this.straightDownPrice = straightDownPrice;
	}

	public BigDecimal getFullcutPrice() {
		return fullcutPrice;
	}

	public void setFullcutPrice(BigDecimal fullcutPrice) {
		this.fullcutPrice = fullcutPrice;
	}

	public long getMainRuleId() {
		return mainRuleId;
	}

	public void setMainRuleId(long mainRuleId) {
		this.mainRuleId = mainRuleId;
	}

	public long getMainRuleTerm() {
		return mainRuleTerm;
	}

	public void setMainRuleTerm(long mainRuleTerm) {
		this.mainRuleTerm = mainRuleTerm;
	}

	public String getMainRuleName() {
		return mainRuleName;
	}

	public void setMainRuleName(String mainRuleName) {
		this.mainRuleName = mainRuleName;
	}

	public List<RuleListEntity> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<RuleListEntity> ruleList) {
		this.ruleList = ruleList;
	}

	public TcCountry getTcCountry() {
		return tcCountry;
	}

	public void setTcCountry(TcCountry tcCountry) {
		this.tcCountry = tcCountry;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public Long getMainActivityId() {
		return mainActivityId;
	}

	public void setMainActivityId(Long mainActivityId) {
		this.mainActivityId = mainActivityId;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDomesticPrice() {
		return domesticPrice;
	}

	public void setDomesticPrice(BigDecimal domesticPrice) {
		this.domesticPrice = domesticPrice;
	}

	public String getOriginPlaceId() {
		return originPlaceId;
	}

	public void setOriginPlaceId(String originPlaceId) {
		this.originPlaceId = originPlaceId;
	}

	public String getSkuNameEn() {
		return skuNameEn;
	}

	public void setSkuNameEn(String skuNameEn) {
		this.skuNameEn = skuNameEn;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOriginPlaceName() {
		return originPlaceName;
	}

	public void setOriginPlaceName(String originPlaceName) {
		this.originPlaceName = originPlaceName;
	}

	public Integer getStockQty() {
		return stockQty;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	public Short getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(Short isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Double getExchangeRange() {
		return exchangeRange;
	}

	public void setExchangeRange(Double exchangeRange) {
		this.exchangeRange = exchangeRange;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public BigDecimal getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(BigDecimal subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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

	public String getCustomsCode() {
		return customsCode;
	}

	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public List<OrderPromoteRelationDTO> getPromoteRelationDTOs() {
		return promoteRelationDTOs;
	}

	public void setPromoteRelationDTOs(
			List<OrderPromoteRelationDTO> promoteRelationDTOs) {
		this.promoteRelationDTOs = promoteRelationDTOs;
	}

	// public BigDecimal getPromotionPrice() {
	// return promotionPrice;
	// }
	//
	// public void setPromotionPrice(BigDecimal promotionPrice) {
	// this.promotionPrice = promotionPrice;
	// }

	public boolean isFreePostage() {
		return freePostage;
	}

	public void setFreePostage(boolean freePostage) {
		this.freePostage = freePostage;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Long getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(Long accessMode) {
		this.accessMode = accessMode;
	}

	public boolean isCheckedToDelete() {
		return isCheckedToDelete;
	}

	public void setIsCheckedToDelete(boolean isCheckedToDelete) {
		this.isCheckedToDelete = isCheckedToDelete;
	}
}
