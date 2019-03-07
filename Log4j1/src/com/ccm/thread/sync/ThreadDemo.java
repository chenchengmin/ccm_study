package com.ccm.thread.sync;

public class ThreadDemo extends Thread {

	public Thread t;
	private String threadName;
	PrintDemo PD;

	ThreadDemo(String name, PrintDemo pd) {
		threadName = name;
		PD = pd;
	}

	public void run() {
		synchronized (PD) {
			PD.printCount(threadName);
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
