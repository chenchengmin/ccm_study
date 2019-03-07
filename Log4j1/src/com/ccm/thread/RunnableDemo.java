package com.ccm.thread;

public class RunnableDemo extends Thread {

	public Thread t;
	private String threadName;
	// 暂停标志
	boolean suspended = false;

	RunnableDemo(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		try {
			for (int i = 10; i > 0; i--) {
				System.out.println("Thread: " + threadName + ", " + i);
				// Let the thread sleep for a while.
				Thread.sleep(300);
				// suspended公共量，线程同步
				synchronized (this) {
					while (suspended) {
						//其实相当于this调用wait()方法
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
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

	// 线程暂停
	public void end() {
		suspended = true;
	}

	// 线程唤醒
	public synchronized void get() {
		suspended = false;
		notify();
	}
}
