package com.adult.android.entity;

import java.io.Serializable;

public class TcCountry implements Serializable {
    private String countryid;

    private String name;

    private String description;//描述暂时修改为英文

    private String vaild;

    private String area;

    private String chinaarea;

    private Long sortvalue;

    private String countrycode;

    private String currency;

    private String callingcode;
    
    private String img;//国旗图片url

    private static final long serialVersionUID = 1L;

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid == null ? null : countryid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getVaild() {
        return vaild;
    }

    public void setVaild(String vaild) {
        this.vaild = vaild == null ? null : vaild.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getChinaarea() {
        return chinaarea;
    }

    public void setChinaarea(String chinaarea) {
        this.chinaarea = chinaarea == null ? null : chinaarea.trim();
    }

    public Long getSortvalue() {
        return sortvalue;
    }

    public void setSortvalue(Long sortvalue) {
        this.sortvalue = sortvalue;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getCallingcode() {
        return callingcode;
    }

    public void setCallingcode(String callingcode) {
        this.callingcode = callingcode == null ? null : callingcode.trim();
    }

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
    
    
}