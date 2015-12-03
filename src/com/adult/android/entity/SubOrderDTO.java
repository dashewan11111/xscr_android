package com.adult.android.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuyujun 2015-3-17 下午3:59:40
 */
public class SubOrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Order> orderDTOs;

	private List<ResultCode> resultCodes;

	public SubOrderDTO() {
		super();
	}

	public List<ResultCode> getResultCodes() {
		return resultCodes;
	}

	public void setResultCodes(List<ResultCode> resultCodes) {
		this.resultCodes = resultCodes;
	}

	public List<Order> getOrderDTOs() {
		return orderDTOs;
	}

	public void setOrderDTOs(List<Order> orderDTOs) {
		this.orderDTOs = orderDTOs;
	}

}
