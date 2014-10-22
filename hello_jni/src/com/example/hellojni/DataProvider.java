package com.example.hellojni;

import android.util.Log;

public class DataProvider {
	private static String TAG = "DataProvider";
	/**
	 * 加载.so动态库文件
	 */
	static {
		try {
			Log.i(TAG, "Trying to load libtemDevice_jni.so");
			System.loadLibrary("hello-jni");
		} catch (UnsatisfiedLinkError ule) {
			Log.e(TAG, "WARNING: Could not load libtemDevice_jni.so");
		}

	}

	/**
	 * 本地方法声明
	 * 
	 * @return
	 */
	public native String stringFromJNI();

	public native int intFromJNI();

	public native int addFromJNI(int i, int j);

	public native String getStringFromJNI(String arg);

	public native int[] getArrayDataFromJNI(int[] args);
	
	public native int[] dojintArrayMethod(int[] arr);
	
	public native String[] dojobjectArrayMethod(String[] arr);

	public native void setStudentAge(Student student);

	public native void callCcode();// 该本地方法将调用java里的helloFromJava()方法。

	public native void callCcode1();

	public native void callCcode2();//该本地方法将调用java里的 Add(int x, int y)方法
	
	// C调用java中的空方法
	public void helloFromJava() {
		System.out.println("hello from java ");
	}

	// C调用java中的带两个int参数的方法
	public int Add(int x, int y) {
		System.out.println("x:"+x+",y:"+y);
		System.out.println("相加的结果为" + (x + y));
		return x + y;
	}

	// C调用java中参数为string的方法
	public void printString(String s) {
		System.out.println("in java code " + s);
	}
}
