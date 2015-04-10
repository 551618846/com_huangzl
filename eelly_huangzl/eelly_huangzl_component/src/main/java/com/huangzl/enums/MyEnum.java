package com.huangzl.enums;

public enum MyEnum {
	A,B,c(),D(4,"my name is D");//调用构造方法的2种方式
	
	private MyEnum(int index,String name) {//构造方法
		this.index = index;
		this.name = name;
	}
	
	private MyEnum() {//构造方法
		// TODO Auto-generated constructor stub
	}
	
	
	private int index;
	private String name;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
