package com.ccm.thread;

public class TestThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RunnableDemo R1 = new RunnableDemo("Thread-1");
		R1.start();

		RunnableDemo R2 = new RunnableDemo("Thread-2");
		R2.start();

		try {

			// 睡眠的是当前类的线程，主线程
			Thread.sleep(1000);
			R1.end();
			System.out.println("Suspending First Thread");
			Thread.sleep(1000);
			R1.get();
			System.out.println("Resuming First Thread");

			R2.end();
			System.out.println("Suspending thread Two");
			Thread.sleep(1000);
			R2.get();
			System.out.println("Resuming thread Two");
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}

		try {
			System.out.println("Waiting for threads to finish.");
			R1.t.join();
			R2.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted*****主线程中断");
		}
		System.out.println("Main thread exiting******主线程执行完毕.");
	}

}
