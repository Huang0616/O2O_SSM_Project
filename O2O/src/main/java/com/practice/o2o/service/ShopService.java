package com.practice.o2o.service;

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.practice.o2o.dto.shopExecution;
import com.practice.o2o.entity.Shop;

public interface ShopService {

	shopExecution addShop(Shop shop, CommonsMultipartFile shopImg); 
}
