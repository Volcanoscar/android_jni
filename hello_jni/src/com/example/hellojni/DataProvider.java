package com.example.hellojni;

import android.util.Log;

public class DataProvider {
	private static String TAG = "DataProvider";
	/**
	 * ����.so��̬���ļ�
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
	 * ���ط�������
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

	public native void callCcode();// �ñ��ط���������java���helloFromJava()������

	public native void callCcode1();

	public native void callCcode2();//�ñ��ط���������java��� Add(int x, int y)����
	
	// C����java�еĿշ���
	public void helloFromJava() {
		System.out.println("hello from java ");
	}

	// C����java�еĴ�����int�����ķ���
	public int Add(int x, int y) {
		System.out.println("x:"+x+",y:"+y);
		System.out.println("��ӵĽ��Ϊ" + (x + y));
		return x + y;
	}

	// C����java�в���Ϊstring�ķ���
	public void printString(String s) {
		System.out.println("in java code " + s);
	}
}
