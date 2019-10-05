package com.practice.o2o.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.o2o.dao.ShopDao;
import com.practice.o2o.dto.shopExecution;
import com.practice.o2o.entity.Shop;
import com.practice.o2o.enums.ShopStateEnum;
import com.practice.o2o.exceptions.ShopOperationException;
import com.practice.o2o.service.ShopService;
import com.practice.o2o.util.ImageUtil;
import com.practice.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public shopExecution addShop(Shop shop, InputStream shopImgInpuStream, String fileName) {
		//判断非空异常
		if(shop==null) {
			return new shopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//给店铺信息赋初值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectNum = shopDao.insertShop(shop);
			if(effectNum <= 0) {
				throw new ShopOperationException("创建店铺失败");
			}else {
				if(shopImgInpuStream != null) {
					//存储图片
					try {
						addShopImg(shop,shopImgInpuStream,fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShop error" + e.getMessage());
					}
					//更新店铺图片
					effectNum = shopDao.updateShop(shop);
					if(effectNum <= 0) {
						throw new ShopOperationException("更新店铺图片失败");
					}
				}
			}
		} catch (Exception e) {
			//只有抛出ShopOperationException以及其子类的时候，事务transaction才会roll back 
			throw new ShopOperationException("addShop.err"+e.getMessage());
		}
		return new shopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName){
		//获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImgPath(shop.getShopId());
		//拼接绝对路径
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
 	 	//更新
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getByShopId(long id) {
		return shopDao.queryByShopId(id);
	}

	@Override
	public shopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
			throws ShopOperationException {
		if(shop == null || shop.getShopId() == null) {
			return new shopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			try {
				//判断是否需要处理图片，比如，更新图片时要删除旧的图片
				if(shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
					//根据传入shop查找shopimg的地址，若能找到，说明已经存在图片，需要先删掉再更新
					Shop tempShop = shopDao.queryByShopId(shop.getShopId());
					if(tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
						System.out.println("DELETE");
					}
					addShopImg(shop, shopImgInputStream, fileName);
				}
				//更新店铺信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if(effectedNum<=0) {
					return new shopExecution(ShopStateEnum.INNER_ERROR);
				}else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new shopExecution(ShopStateEnum.SUCCESS,shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("modifyShop error: "+ e.getMessage());
			}
		}
	} 

}
