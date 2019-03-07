package com.ccm.thread.notify;

/**
 * 
 * 
 调用obj.wait( )释放了obj的锁，否则其他线程也无法获得obj的锁，也就无法在synchronized(obj){ obj.notify() }
 * 代码段内唤醒A。
 * 
 * notify( )方法只会通知等待队列中的第一个相关线程（不会通知优先级比较高的线程）
 * 
 * notifyAll( )通知所有等待该竞争资源的线程（也不会按照线程的优先级来执行）
 * 
 * 假设有三个线程执行了obj.wait( )，那么obj.notifyAll(
 * )则能全部唤醒tread1，thread2，thread3，但是要继续执行obj
 * .wait（）的下一条语句，必须获得obj锁，因此，tread1，thread2
 * ，thread3只有一个有机会获得锁继续执行，例如tread1，其余的需要等待thread1释放obj锁之后才能继续执行。
 * 
 * 当调用obj.notify/notifyAll后，调用线程依旧持有obj锁，因此，thread1，thread2，thread3虽被唤醒，
 * 但是仍无法获得obj锁
 * 。直到调用线程退出synchronized块，释放obj锁后，thread1，thread2，thread3中的一个才有机会获得锁继续执行。
 * 
 * @author Administrator
 * 
 */

public class Chat {

	boolean flag = false;

	public synchronized void Question(String msg) {
		if (flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(msg);
		flag = true;
		// 唤醒在此对象监视器(锁)上等待的单个线程
		notify();
	}

	public synchronized void Answer(String msg) {
		if (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(msg);
		flag = false;
		notify();
	}

}
