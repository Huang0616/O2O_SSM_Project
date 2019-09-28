package com.practice.o2o.enums;

public enum ShopStateEnum {
	Check(0,"审核中"),OFFLINE(-1,"非法"),SUCCESS(1,"操作成功"),PASS(2,"通过认证"),
	INNER_ERROR(-1001,"内部系统错误"),NULL_SHOPID(-1002,"shopid为空");
	
	private int state;
	private String stateInfo;
	
	private ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	/*
	 * 
	 */
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum stateEnum : values()) {
			if(stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}
