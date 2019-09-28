package com.practice.o2o.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.seperator");
	/*
	 * 获取运行时所需的文件路径的根路径
	 * 外部文件，比如图片等应该单独存放，不要混在项目根目录下 
	 * @return root url of image
	 */
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = ""; 
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image;";
		}else {
			basePath = "/home/fei/***";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}
	/*
	 * @param shopId
	 * @return the address of image of the shop
	 */
	public static String getShopImgPath(long shopId) {
		String imagePath = "/***/ite/shop" + shopId +"/";
		return imagePath.replace("/", seperator);
	}
}