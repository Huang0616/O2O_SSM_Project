package com.practice.o2o.service;

import java.io.InputStream;

import com.practice.o2o.dto.shopExecution;
import com.practice.o2o.entity.Shop;
import com.practice.o2o.exceptions.ShopOperationException;

public interface ShopService {
	/**
	 * 增加店铺
	 * @param shop
	 * @param shopImg
	 * @param fileName
	 * @return
	 */
	shopExecution addShop(Shop shop, InputStream shopImg, String fileName); 
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	Shop getByShopId(long id);
	/**
	 * 修改信息
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	shopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
