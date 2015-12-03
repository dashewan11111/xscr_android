package com.adult.android.entity;

import java.util.List;

public class ProductDetail extends BaseEntity {
	private ProductAtt product;
	private Evaluate comment;
	private List<PromotionDto> promotionDto;
	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductAtt getProduct() {
		return product;
	}
	public void setProduct(ProductAtt product) {
		this.product = product;
	}
	public Evaluate getComment() {
		return comment;
	}
	public void setComment(Evaluate comment) {
		this.comment = comment;
	}
	public List<PromotionDto> getPromotionDto() {
		return promotionDto;
	}
	public void setPromotionDto(List<PromotionDto> promotionDto) {
		this.promotionDto = promotionDto;
	}
	
	
}
