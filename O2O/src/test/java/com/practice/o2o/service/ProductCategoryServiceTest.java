package com.practice.o2o.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.o2o.BaseTest;
import com.practice.o2o.entity.ProductCategory;


public class ProductCategoryServiceTest extends BaseTest{
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Test
	public void testGetProductCategoryList() {
		List<ProductCategory> pdlist = new ArrayList<ProductCategory>();
		pdlist = productCategoryService.getProductCategoryList(2l);
		System.out.println(pdlist.size());
	}
}
