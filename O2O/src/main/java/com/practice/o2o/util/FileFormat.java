package com.practice.o2o.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileFormat {
	
	public CommonsMultipartFile transferFile(File file) {
		System.out.println("111");
		FileItemFactory factory = new DiskFileItemFactory(16, null);
		String filePath = "" + file;
		int num = filePath.lastIndexOf(".");
		String extFile = filePath.substring(num);
		FileItem item = factory.createItem("textField", "text/plain", false, "myFileName"+extFile);
		System.out.println(item.getContentType());
		int byteRead = 0;
		byte[] buffer = new byte[8192];
		try {
			FileInputStream fis = new FileInputStream(file);
			OutputStream os = item.getOutputStream();
			while((byteRead = fis.read(buffer,0,8192)) != -1) {
				os.write(buffer, 0, byteRead);
			}
			os.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CommonsMultipartFile mfile = new CommonsMultipartFile(item);
		return mfile;
	}
}
