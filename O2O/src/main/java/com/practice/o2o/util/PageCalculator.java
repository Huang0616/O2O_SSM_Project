package com.practice.o2o.util;

public class PageCalculator {
	/**
	 * 传入页码和页面大小，返回本页第一条数据的行号
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0)?(pageIndex - 1) * pageSize : 0;
	}
}
