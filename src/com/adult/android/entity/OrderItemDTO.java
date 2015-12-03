package com.adult.android.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuyujun 2015-3-18 下午2:13:16
 */
public class OrderItemDTO extends ProductObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imgUrl;

	private boolean freePostage;

	private List<OrderPromoteRelationDTO> promoteRelationDTOs;

	public OrderItemDTO() {
		super();
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<OrderPromoteRelationDTO> getPromoteRelationDTOs() {
		return promoteRelationDTOs;
	}

	public void setPromoteRelationDTOs(
			List<OrderPromoteRelationDTO> promoteRelationDTOs) {
		this.promoteRelationDTOs = promoteRelationDTOs;
	}

	public boolean isFreePostage() {
		return freePostage;
	}

	public void setFreePostage(boolean freePostage) {
		this.freePostage = freePostage;
	}

}
