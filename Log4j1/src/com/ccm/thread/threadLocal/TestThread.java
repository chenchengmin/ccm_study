package com.ccm.thread.threadLocal;
/**
 * 可以看到变量(counter)的值由每个线程增加，但是ThreadLocalCounter对于每个线程都保持为0
 * @author Administrator
 *
 */
public class TestThread {

	public static void main(String args[]) {
		RunnableDemo commonInstance = new RunnableDemo();

		Thread t1 = new Thread(commonInstance);
		Thread t2 = new Thread(commonInstance);
		Thread t3 = new Thread(commonInstance);
		Thread t4 = new Thread(commonInstance);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		// wait for threads to end
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
		System.out.println("main thread ----执行完毕");
	}

}
