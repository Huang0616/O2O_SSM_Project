package com.practice.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.o2o.BaseTest;
import com.practice.o2o.dto.shopExecution;
import com.practice.o2o.entity.Area;
import com.practice.o2o.entity.PersonInfo;
import com.practice.o2o.entity.Shop;
import com.practice.o2o.entity.ShopCategory;
import com.practice.o2o.enums.ShopStateEnum;
import com.practice.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService; 
	
	@Test
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1l);
		shopCondition.setShopName("1");
		shopCondition.setOwner(owner);
		shopExecution se = shopService.getShopList(shopCondition, 2, 6);
		System.out.println(se.getShopList().size());
		System.out.println(se.getCount());
	}
	
	@Test
	@Ignore
	public void testModifyshop() throws ShopOperationException, FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(1l);
		shop.setShopName("新店铺");
		File shopImg = new File("/Users/fei/Desktop/v2-7794c89eda1a94daf9a5b4425ec0e380_r.jpg");
		FileInputStream is = new FileInputStream(shopImg);
		shopExecution shopexecution = shopService.modifyShop(shop, is, "v2-7794c89eda1a94daf9a5b4425ec0e380_r.jpg");
		System.out.println("新图片地址:" + shopexecution.getShop().getShopImg());
	}
	
	@Ignore
	@Test
	public void testAddShop() throws FileNotFoundException {
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
		shop.setShopName("测试店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdcive("审核中");
		File testFile = new File("/Users/fei/Documents/O2OFile/t0120b2f23b554b8402.jpg");
		InputStream iStream = new FileInputStream(testFile);
		shopExecution se = shopService.addShop(shop, iStream,testFile.getName());
		assertEquals(se.getState(), ShopStateEnum.CHECK.getState());
	}
}
