package com.practice.o2o.entity;

import java.util.Date;

public class ProductCategory {
	private Long productCategoryId;
	private Long shopId;
	private String productCategoryName;
	private Integer priority;
	private Date createTime;
	public Long getProductCategory() {
		return productCategoryId;
	}
	public void setProductCategory(Long productCategory) {
		this.productCategoryId = productCategory;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
