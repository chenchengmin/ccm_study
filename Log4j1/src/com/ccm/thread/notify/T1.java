package com.ccm.thread.notify;

public class T1 implements Runnable {
	Chat m;
	String[] s1 = { "ccm:Hi", "ccm:周末吃饭不?", "ccm:那就外婆家吧!" };

	public T1(Chat m1) {
		this.m = m1;
		new Thread(this, "Question").start();
	}

	public void run() {
		for (int i = 0; i < s1.length; i++) {
			m.Question(s1[i]);
		}
	}
}
