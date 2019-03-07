package com.ccm.enumtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		System.out.println(37/Math.pow(2, 3));//m的n次幂
		
		long a = 449568591470133251l;
		System.out.println(a);*/
		
		/*String a = "449568591470133251";
		
		long b = Long.parseLong(a);
		System.out.println(b);//Collection
		
		 List c = new ArrayList<>();
		 c.add("1");
		 c.add("2");
		 c.add("3");
		 c.add("1");
		 c.add("1");
		 System.out.println(c);*/
		
		
		//Recevied from MCIS:[HeapBuffer[pos=0 lim=32 cap=16384: E7 94 A8 E6 88 B7 E5 90 8D EF BC 9A 61 64 6D 69...]]
		String str = "用户名：admin；密码：123";
		byte[] arr = str.getBytes();
		System.out.println(Arrays.toString(arr));
		
		
		
		
		
		
		
		
		/*String  groupId = "累计登录";
		System.out.println(AchievementGroup.getNameByDescribe(groupId));*/
	}

}
