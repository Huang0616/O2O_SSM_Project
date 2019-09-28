package com.practice.o2o.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImagUtil {    
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
	public static final Random r = new Random();
	/*
	 * thumballator 图像处理工具类
	 * 输入文件对象和目标地址，返回系统生成的随机地址并创建相应的路径目录
	 * 
	 */
	public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnail.getInputStream()).size(200,200).
			watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
			.outputQuality(0.8).toFile(dest);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 创建目标路径上的目录
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	/*
	 * 获取输入文件扩展名
	 * @param thumbnail
	 * @return return extension of the input file
	 */
	private static String getFileExtension(CommonsMultipartFile cFile) {
		String originalFileName = cFile.getOriginalFilename();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}

	/* 
	 * @return a random filename with current yy-mm-dd-hh-mm-ss + random 5
	 */
	private static String getRandomFileName() {
		//get random number of 5 digits
		int rannum = r.nextInt(89999)+10000;
		String nowTimeStr = sDateFormat.format(new Date());
 		return nowTimeStr+rannum;
	}
}
