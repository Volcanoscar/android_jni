package com.example.hellojni;

import android.app.Activity;
import android.os.Bundle;

public class HelloJni extends Activity {
	DataProvider dataProvider;
	private int[] a = new int[10];// ʵ����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dataProvider = new DataProvider();
		// ���ط��������ַ���
		System.out.println(dataProvider.stringFromJNI());
		// ���ط�������int
		System.out.println(dataProvider.intFromJNI());
		// ���ط�������int����
		System.out.println(dataProvider.addFromJNI(2, 2));
		// ���ط�������int[]
		for (int i = 0; i < 10; i++) {
			a[i] = i;// ��ʼ��
		}
		for (int i : dataProvider.getArrayDataFromJNI(a)) {
			System.out.println(i);
		}
		for (int i = 0; i < 10; i++) {
			a[i] = i;// ��ʼ��
		}
		// ���ط�����������ĵڶ�����ʽ
		for (int i : dataProvider.dojintArrayMethod(a)) {
			System.out.println(i);
		}
		// ���ط�������String������������String
		System.out.println(dataProvider.getStringFromJNI("get first string from native"));

		String[] ss = { "hello", "It", "is", "from", "java" };
		Object[] obj = dataProvider.dojobjectArrayMethod(ss);
		for (Object object : obj) {
			System.out.println("�ַ���1�ǣ�"+object);
		}
		
		String[] strings = dataProvider.dojobjectArrayMethod(ss);
		for(String str : strings){
			System.out.println("�ַ���2�ǣ�"+str);
		}

		// JNI�ײ�ı�Java���еı���
		Student student = new Student("andy", 23);
		System.out.println("Before setting: " + student.toString());
		dataProvider.setStudentAge(student);
		System.out.println("After setting: " + student.toString());
		// c�ص�java��ķ���
		dataProvider.callCcode();
		dataProvider.callCcode1();
		dataProvider.callCcode2();

	}
}
