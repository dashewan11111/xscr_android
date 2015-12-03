package com.adult.android.entity;

import java.util.List;

/**
 * @ClassName: CityModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年3月6日 下午4:54:31
 * 
 */
public class DivisionCity extends BaseEntity {
	private String id;
	private String name;
	private List<DivisionRegion> area_list;

	public DivisionCity() {
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

	public List<DivisionRegion> getArea_list() {
		return area_list;
	}

	public void setArea_list(List<DivisionRegion> area_list) {
		this.area_list = area_list;
	}

	@Override
	public String toString() {
		return name;
	}

}
