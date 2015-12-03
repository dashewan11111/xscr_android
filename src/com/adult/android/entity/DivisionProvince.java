package com.adult.android.entity;

import java.util.List;

/**
 * @ClassName: ProvinceModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年3月6日 下午4:53:28
 * 
 */
public class DivisionProvince extends BaseEntity {
	private String id;
	private String name;
	private List<DivisionCity> city_list;

	public DivisionProvince() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DivisionCity> getCity_list() {
		return city_list;
	}

	public void setCity_list(List<DivisionCity> city_list) {
		this.city_list = city_list;
	}

	@Override
	public String toString() {
		return name;
	}
}
