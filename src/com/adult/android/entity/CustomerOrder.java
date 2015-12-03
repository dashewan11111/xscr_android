package com.adult.android.entity;

import java.io.Serializable;
import java.util.List;

public class CustomerOrder implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2297323962910210697L;

	private Order order;

	private List<CartSkuDTO> cartSkus;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CustomerOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CartSkuDTO> getCartSkus() {
		return cartSkus;
	}

	public void setCartSkus(List<CartSkuDTO> cartSkus) {
		this.cartSkus = cartSkus;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
