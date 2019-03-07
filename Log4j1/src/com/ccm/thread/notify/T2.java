package com.ccm.thread.notify;

public class T2 implements Runnable {

	Chat m;
	String[] s2 = { "zsx:Hi", "zsx:吃啊！", "zsx:好的，你定了就好，我负责吃就行！" };

	public T2(Chat m2) {
		this.m = m2;
		new Thread(this, "Answer").start();
	}

	public void run() {
		for (int i = 0; i < s2.length; i++) {
			m.Answer(s2[i]);
		}
	}

}
