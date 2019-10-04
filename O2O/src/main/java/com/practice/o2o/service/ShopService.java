package com.practice.o2o.service;

import java.io.InputStream;

import com.practice.o2o.dto.shopExecution;
import com.practice.o2o.entity.Shop;

public interface ShopService {

	shopExecution addShop(Shop shop, InputStream shopImg, String fileName); 
}
