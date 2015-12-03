package com.adult.android.entity;

/**
 * @ClassName: DistrictModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 潘学坤
 * @date 2015年3月6日 下午4:55:10
 * 
 */
public class DivisionRegion extends BaseEntity {
	private String id;
	private String name;

	public DivisionRegion() {
		super();
	}

	public DivisionRegion(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return name;
	}
}
