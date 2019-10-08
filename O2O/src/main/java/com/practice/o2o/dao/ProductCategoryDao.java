package com.practice.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.practice.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	List<ProductCategory> queryProductCategoryList(@Param("shopId") long shopId);
	
	/**
	 * 批量添加商品类别
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	/**
	 * 删除商品类别
	 * @param productCategoryName
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long shopId);
}
