package com.example.hellojni;

import android.app.Activity;
import android.os.Bundle;

public class HelloJni extends Activity {
	DataProvider dataProvider;
	private int[] a = new int[10];// 实例化

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dataProvider = new DataProvider();
		// 本地方法返回字符串
		System.out.println(dataProvider.stringFromJNI());
		// 本地方法返回int
		System.out.println(dataProvider.intFromJNI());
		// 本地方法传递int参数
		System.out.println(dataProvider.addFromJNI(2, 2));
		// 本地方法返回int[]
		for (int i = 0; i < 10; i++) {
			a[i] = i;// 初始化
		}
		for (int i : dataProvider.getArrayDataFromJNI(a)) {
			System.out.println(i);
		}
		for (int i = 0; i < 10; i++) {
			a[i] = i;// 初始化
		}
		// 本地方法处理数组的第二种形式
		for (int i : dataProvider.dojintArrayMethod(a)) {
			System.out.println(i);
		}
		// 本地方法传递String参数，并返回String
		System.out.println(dataProvider.getStringFromJNI("get first string from native"));

		String[] ss = { "hello", "It", "is", "from", "java" };
		Object[] obj = dataProvider.dojobjectArrayMethod(ss);
		for (Object object : obj) {
			System.out.println("字符串1是："+object);
		}
		
		String[] strings = dataProvider.dojobjectArrayMethod(ss);
		for(String str : strings){
			System.out.println("字符串2是："+str);
		}

		// JNI底层改变Java类中的变量
		Student student = new Student("andy", 23);
		System.out.println("Before setting: " + student.toString());
		dataProvider.setStudentAge(student);
		System.out.println("After setting: " + student.toString());
		// c回调java里的方法
		dataProvider.callCcode();
		dataProvider.callCcode1();
		dataProvider.callCcode2();

	}
}
