package com.practice.o2o.dao;

import com.practice.o2o.entity.Shop;

public interface ShopDao {
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

