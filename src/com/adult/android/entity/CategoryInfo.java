package com.adult.android.entity;

public class CategoryInfo extends BaseEntity {
	private String pid;
	private String pName;
	private String highlightedPname = "";// 非默认列表
	private String b2cPname;// 默认列表
	private String inventory;
	private String brandName;
	private String cyid;
	private String msp;
	private String maxsp;
	private String imageurl;
	private String leadingtime;
	private String cdid;
	private String measureName;
	private String exchange = "";
	private String unit_price;
	private String domestic_price;
	private String cyName;
	private String countryImg;
	private String bondedZone;
	private String xinventory;
	// 贸易类型
	private String proStyleDescribe;
	// 促销类型
	private String promotion;
	private int b2csupply;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CategoryInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getHighlightedPname() {
		return highlightedPname;
	}

	public void setHighlightedPname(String highlightedPname) {
		this.highlightedPname = highlightedPname;
	}

	public String getB2cPname() {
		return b2cPname;
	}

	public void setB2cPname(String b2cPname) {
		this.b2cPname = b2cPname;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCyid() {
		return cyid;
	}

	public void setCyid(String cyid) {
		this.cyid = cyid;
	}

	public String getMsp() {
		return msp;
	}

	public void setMsp(String msp) {
		this.msp = msp;
	}

	public String getMaxsp() {
		return maxsp;
	}

	public void setMaxsp(String maxsp) {
		this.maxsp = maxsp;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getLeadingtime() {
		return leadingtime;
	}

	public void setLeadingtime(String leadingtime) {
		this.leadingtime = leadingtime;
	}

	public String getCdid() {
		return cdid;
	}

	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getDomestic_price() {
		return domestic_price;
	}

	public void setDomestic_price(String domestic_price) {
		this.domestic_price = domestic_price;
	}

	public String getCyName() {
		return cyName;
	}

	public void setCyName(String cyName) {
		this.cyName = cyName;
	}

	public String getCountryImg() {
		return countryImg;
	}

	public void setCountryImg(String countryImg) {
		this.countryImg = countryImg;
	}

	public String getBondedZone() {
		return bondedZone;
	}

	public void setBondedZone(String bondedZone) {
		this.bondedZone = bondedZone;
	}

	public String getXinventory() {
		return xinventory;
	}

	public void setXinventory(String xinventory) {
		this.xinventory = xinventory;
	}

	public String getProStyleDescribe() {
		return proStyleDescribe;
	}

	public void setProStyleDescribe(String proStyleDescribe) {
		this.proStyleDescribe = proStyleDescribe;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public int getB2csupply() {
		return b2csupply;
	}

	public void setB2csupply(int b2csupply) {
		this.b2csupply = b2csupply;
	}
}