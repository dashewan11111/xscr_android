package com.adult.android.entity;

public class CountryInfo extends BaseEntity {
	private String countryid;
	private String name;
	private String nameEn;
	private String callingcode;
	private String img;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CountryInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getCallingcode() {
		return callingcode;
	}

	public void setCallingcode(String callingcode) {
		this.callingcode = callingcode;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CountryInfo{" + "countryid='" + countryid + '\'' + ", name='"
				+ name + '\'' + ",nameEn='" + nameEn + '\'' + ",callingcode='"
				+ callingcode + '\'' + ", img='" + img + '}';
	}

}
