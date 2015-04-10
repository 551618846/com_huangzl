package com.huangzl.enums;


public class MyEnum{
	
	public static void main(String[] args) {
		System.err.println(MyEnumz.A);
		
		MyEnumz.A.setName("xx");
		System.err.println(MyEnumz.A.getName());
		
		
		System.err.println(MyEnumz.D.getName());
		MyEnumz.D.print();

	}
	
	interface MyInter{
		void print();
	}
	
	/**
	 * 所有的枚举都继承自java.lang.Enum类。由于Java 不支持多继承，所以枚举对象不能再继承其他类。
	 * 可以实现接口
	 * @author Administrator
	 *
	 */
	enum MyEnumz implements MyInter{//不能继承,只能实现接口
		A,B,c(),D(4,"my name is D");//调用构造方法的2种方式
		
		private MyEnumz(int index,String name) {//构造方法
			this.index = index;
			this.name = name;
		}
		
		private MyEnumz() {//构造方法
			
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

		@Override
		public void print() {
			System.err.println(this.index + "-" + this.name);
			
		}
		
		
	}
	
	
	
}





