package com.practice.o2o.service;

import java.util.List;

import com.practice.o2o.dto.ProductCategoryExecution;
import com.practice.o2o.entity.ProductCategory;
import com.practice.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	List<ProductCategory> getProductCategoryList(long shopId);
	
	/**
	 * 批量添加商品信息
	 * @param productCategoryList
	 * @return
	 * @throws ProdcutCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) 
		throws ProductCategoryOperationException;
	/**
	 * 应该先将改类别下的商品的类别id设为空，再删除category-TODO
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId)
		throws ProductCategoryOperationException;
}
