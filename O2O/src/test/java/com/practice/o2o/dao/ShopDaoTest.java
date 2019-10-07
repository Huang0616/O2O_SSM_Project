package com.practice.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.o2o.BaseTest;
import com.practice.o2o.entity.Area;
import com.practice.o2o.entity.PersonInfo;
import com.practice.o2o.entity.Shop;
import com.practice.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1l);
		shopCondition.setShopName("3");
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		System.out.println(shopList.size());
	}
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 17;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaID= "+ shop.getArea().getAreaId());
		System.out.println("areaName= "+ shop.getArea().getAreaName());
	}
	
	@Test
	@Ignore
	public void testInsertShop() {
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
		shop.setShopName("测试店铺10086");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdcive("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1l);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}
