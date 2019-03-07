package com.ccm.thread.sync;

public class TestThread {

	/**
	 * 并发同步
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		PrintDemo PD = new PrintDemo();

		ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD);
		ThreadDemo T2 = new ThreadDemo("Thread - 2 ", PD);

		T1.start();
		T2.start();
		

		// wait for threads to end
		try {
			T1.t.join();
			T2.t.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
		System.out.println("main thread ----exiting");

	}

}
