package com.huangzl.enums;

public class Runner {
	
	

	public static void main(String[] args) {
		System.err.println(MyEnum.A);
		
		MyEnum.A.setName("xx");
		System.err.println(MyEnum.A.getName());
		
		
		System.err.println(MyEnum.D.getName());

	}

}
