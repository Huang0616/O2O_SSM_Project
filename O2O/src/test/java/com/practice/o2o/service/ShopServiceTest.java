package com.practice.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.practice.o2o.BaseTest;
import com.practice.o2o.dto.shopExecution;
import com.practice.o2o.entity.Area;
import com.practice.o2o.entity.PersonInfo;
import com.practice.o2o.entity.Shop;
import com.practice.o2o.entity.ShopCategory;
import com.practice.o2o.enums.ShopStateEnum;
import com.practice.o2o.util.FileFormat;

public class ShopServiceTest extends BaseTest{
	private FileFormat fr = new FileFormat();
	@Autowired
	private ShopService shopService; 
	@Test
	public void testAddShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1l);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1l);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdcive("审核中");
		shopExecution se = shopService.addShop(shop, null);
		assertEquals(se.getState(), ShopStateEnum.CHECK.getState());
	}
}
