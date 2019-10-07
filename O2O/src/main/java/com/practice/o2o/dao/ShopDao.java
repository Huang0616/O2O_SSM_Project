package com.practice.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.practice.o2o.entity.ProductCategory;
import com.practice.o2o.entity.Shop;

public interface ShopDao {
	/*
	 * 按shopID查询shop中的商品类别
	 */
	List<ProductCategory> queryProductCategoryListById(long id); 
	/**
	 * 分页查询，模糊查询
	 * @param shopCondition 查询条件
	 * @param rowIndex 返回数据起始行号
	 * @param pageSize 返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	/**
	 * 返回shoplist的总数，和queryShopList搭配使用
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/*
	 * 查询店铺by shop id
	 * @return shopId
	 */
	Shop queryByShopId(long id);
	/*
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/*
	 * 更新店铺
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}

