package com.ccm.thread.ContextSwitchTest;

/**
 * 线程上下文切换：CPU通过时间片分配算法来循环执行任务，当前任务执行一个时间片后会切换到下一个任务。但是，在切换前会保存上一个任务的状态，
 * 以便下次切换回这个任务时，可以再次加载这个任务的状态，从任务保存到再加载的过程就是一次上下文切换。
 * 
 * 
 * 既然上下文切换会导致额外的开销，因此减少上下文切换次数便可以提高多线程程序的运行效率。减少上下文切换的方法有无锁并发编程、CAS算法、使用最少线程和使用协程。
无锁并发编程。多线程竞争时，会引起上下文切换，所以多线程处理数据时，可以用一些办法来避免使用锁，如将数据的ID按照Hash取模分段，不同的线程处理不同段的数据
CAS算法。Java的Atomic包使用CAS算法来更新数据，而不需要加锁
使用最少线程。避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态
协程。在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换
 * 
 * @author Administrator
 * 
 */
public class ContextSwitchTest {
	//线程执行次数
	private static final long count = 1000000000;

	public static void main(String[] args) throws Exception {
		// 修改上面的count值，即修改循环次数，看一下串行运行和并发运行的时间测试结果：
		concurrency();
		serial();
	}
	
	/**
	 * 并发执行
	 * @throws Exception
	 */
	private static void concurrency() throws Exception {
		long start = System.currentTimeMillis();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int a = 0;
				for (int i = 0; i < count; i++) {
					a += 5;
				}
			}
		});
		thread.start();
		int b = 0;
		for (long i = 0; i < count; i++) {
			b--;
		}
		thread.join();
		long time = System.currentTimeMillis() - start;
		System.out.println("Concurrency：" + time + "ms, b = " + b);
	}

	/**
	 * 串行执行
	 * @throws Exception
	 */
	private static void serial() {
		long start = System.currentTimeMillis();
		int a = 0;
		for (long i = 0; i < count; i++) {
			a += 5;
		}
		int b = 0;
		for (int i = 0; i < count; i++) {
			b--;
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("Serial：" + time + "ms, b = " + b );
	}

}
