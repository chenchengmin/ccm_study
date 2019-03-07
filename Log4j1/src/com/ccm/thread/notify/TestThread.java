package com.ccm.thread.notify;

public class TestThread {

	/**聊天 问答
	 * 
	 * 线程通信问题演示
	 * @param args
	 */
	public static void main(String[] args) {
		Chat m = new Chat();
		new T1(m);
		new T2(m);
	}

}
