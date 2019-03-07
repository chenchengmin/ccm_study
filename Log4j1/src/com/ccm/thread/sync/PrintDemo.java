package com.ccm.thread.sync;

/**
 * 打印类
 * 
 * @author Administrator
 * 
 */
public class PrintDemo {
	public void printCount(String threadName) {
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println(threadName+"Counter   ---   " + i);
			}
		} catch (Exception e) {
			System.out.println("Thread  interrupted.");
		}
	}
}
