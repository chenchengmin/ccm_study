package com.ccm.thread.waitAndnotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、wait/notify为什么要在同步块内调用
 * 
 *2、同步强调的是过程：两个线程只之前的执行过程先后顺序
 * 	阻塞：强调的是一个线程的状态，线程阻塞的时候让出cpu，是让出cpu停止执行（阻塞），还是继续执行（非阻塞）
 * 
 * 
 * 
 * @author Administrator
 * 
 *         1、同步是个过程，阻塞是线程的一种状态。多个线程操作共享变量时可能会出现竞争。这时需要同步来防止两个以上的线程同时进入临界区，在这个过程中
 *         ，后进入临界区的线程将阻塞，等待先进入的线程走出临界区。线程同步不一定发生阻塞！！！线程同步的时候，需要协调推进速度，
 *         互相等待和互相唤醒会发生阻塞。 
 *         2、线程同步与阻塞 
 *         2.1 线程同步
 *         两个（或多个）线程之间执行有指定顺序，一个做了什么之后，另一个才能做，一前一后，而不能随机。 
 *         2.2 线程互斥
 *         当有一个线程在使用临界资源时，其他线程都不可以对这个内存地址进行操作，直到该线程完成操作，其他线程才能对该内存地址进行操作。 
 *         2.3 阻塞
 *         阻塞一般指方法，说一个方法阻塞，本质上指该方法不能马上返回，当前线程不能继续执行。 
 *         2.4 非阻塞
 *         非阻塞一般指方法，说一个方法非阻塞，本质上指该方法不等结果执行马上返回，当前线程可以继续执行。
 *         阻塞和非阻塞关注的是程序在等待调用结果时的状态
 *         。阻塞调用是指调用结果返回之前，当前线程会被挂起。调用线程只有在得到结果之后才会返回。非阻塞调用指在不能立刻得到结果之前
 *         ，该调用不会阻塞当前线程。 严格来说，阻塞和非阻塞是强调方法的代码块，跟线程同步与否没有关系，只不过线程同步一般要用到阻塞这种方式去实现。
 * 
 */
public class WaitNotifyCase {
	public static void main(String[] args) {
		// final Object lock = new Object();
		final Lock lock = new ReentrantLock();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread A is waiting to get lock");
				synchronized (lock) {
					try {
						System.out.println("thread A get lock");
						TimeUnit.SECONDS.sleep(3);
						System.out.println("thread A do wait method");
						lock.wait();
						System.out.println("wait end");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread B is waiting to get lock");
				synchronized (lock) {
					System.out.println("thread B get lock");
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread B do notify method");
					lock.notify();
					System.out.println("thread A will be alive in five seconds");
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				try {
					System.out.println("thread B aready notify thread A");
					TimeUnit.SECONDS.sleep(5);
					System.out.println("特么我睡醒了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
