package com.practice.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.o2o.BaseTest;
import com.practice.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testDeleteProductCategory() {
		long shopId = 1;
		int effectedNum = productCategoryDao.deleteProductCategory(10095L,shopId);
		System.out.println(effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryProductCate() {
		List<ProductCategory> list = productCategoryDao.queryProductCategoryList(20);
		System.out.println(list.size());
	}
	
	@Test
	@Ignore
	public void testBatchProductCate() {
		ProductCategory productcate1 = new ProductCategory();
		productcate1.setProductCategoryName("测试类别1");
		productcate1.setPriority(1);
		productcate1.setCreateTime(new Date());
		productcate1.setShopId(1l);
		ProductCategory productcate2 = new ProductCategory();
		productcate2.setProductCategoryName("测试类别2");
		productcate2.setPriority(2);
		productcate2.setCreateTime(new Date());
		productcate2.setShopId(2l);
		ProductCategory productcate3 = new ProductCategory();
		productcate3.setProductCategoryName("测试类别3");
		productcate3.setPriority(5);
		productcate3.setCreateTime(new Date());
		productcate3.setShopId(20l);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productcate1);
		productCategoryList.add(productcate2);
		productCategoryList.add(productcate3);
		int result = productCategoryDao.batchInsertProductCategory(productCategoryList);
		System.out.println(result);
	}
}
