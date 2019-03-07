package com.ccm.thread.ThreadLocalRandom;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadLocalRandom;

/**
 * java.util.concurrent.ThreadLocalRandom是从jdk
 * 1.7开始引入的实用程序类，当需要多个线程或ForkJoinTasks来生成随机数时很有用。
 * 它提高了性能，并且比Math.random()方法占用更少的资源。
 * 
 * 
 * 
 * 以下是ThreadLocalRandom类中可用的重要方法的列表。
编号						方法														说明
1		public static ThreadLocalRandom current()					返回当前线程的ThreadLocalRandom。
2		protected int next(int bits)								生成下一个伪随机数。
3		public double nextDouble(double n)							返回伪随机，均匀分布在0(含)和指定值(独占)之间的double值。
4		public double nextDouble(double least, double bound)		返回在给定的least值(包括)和bound(不包括)之间的伪随机均匀分布的值。
5		public int nextInt(int least, int bound)					返回在给定的least值(包括)和bound(不包括)之间的伪随机均匀分布的整数值。
6		public long nextLong(long n)								返回伪随机均匀分布的值在0(含)和指定值(不包括)之间的长整数值。
7		public long nextLong(long least, long bound)				返回在给定的最小值(包括)和bound(不包括)之间的伪随机均匀分布的长整数值。
8		public void setSeed(long seed)								设置伪随机的种子值，抛出UnsupportedOperationException异常。
 * @author Administrator
 * 
 */

public class TestThread {

	public static void main(final String[] arguments) {
		/*
		 * System.out.println("Random Integer: " + new Random().nextInt());
		 * System.out.println("Seeded Random Integer: " + new
		 * Random(15).nextInt());
		 * System.out.println("Thread Local Random Integer: " +
		 * ThreadLocalRandom.current().nextInt()); final ThreadLocalRandom
		 * random = ThreadLocalRandom.current(); random.setSeed(15); //exception
		 * will come as seeding is not allowed in ThreadLocalRandom.
		 * System.out.println( "Seeded Thread Local Random Integer: " +
		 * random.nextInt());
		 */

		Random ran = new Random();
		System.out.println(ran.nextInt());// 生成一个int类型取值范围的随机数
		System.out.println(ran.nextInt(100));// 生成一个0-100的int类型的随机数
		System.out.println(ran.nextFloat());// 生成一个Float类型的随机数
		System.out.println(ran.nextDouble());// 生成一个Double类型的随机数
		/*
		 * 结果1： -405998184 81 0.7216265 0.8820657901017879
		 */
		
		
		
		/*
		 * Random（）里面的种子数是什么含义???
		 * 如果你知道随机函数怎么编出来的话应该会明白的，random里其实是一个数列，这个数列每一位的数字接近随机分布，可以从数学上证明，
		 * 但是一个数列一定是确定的，也就是第一个是什么数，第二个什么数都是固定的，就像1,2,3,4.。。。但是可以通过一个种子选取数列的起始位置，
		 * 例如，上面的数列从3开始，就是3,4，。。。这就使每次的随机数都不相同。而这个种子一般使用程序运行时对应时间的秒（从某一年开始总的秒数），
		 * 每次的随机数就不同了，产生可以使用的伪随机数。
		 * 
		 * 
		 */
		Random r2 = new Random(50);// 种子为50的对象
		Random r3 = new Random(50);// 种子为50的对象
		// 如果两个Random对象种子数相同，那么他们生成的结果将是一样。可以使用当前时间最为种子：System.currentTimeMillis()
		System.out.println("r2.nextInt():" + r2.nextInt()+ "-------r3.nextInt():" + r3.nextInt());
		
		
		
		
		System.out.println("---------------多线程环境下使用ThreadLocalRandom类，用法跟Random类基本类似---------------------");
		ThreadLocalRandom tlr = ThreadLocalRandom.current();//返回当前线程的ThreadLocalRandom
		System.out.println(tlr.nextInt(10, 50));// 生成一个10~50之间的随机数

		Random random = new Random(1);
		int a = random.nextInt(5);
		random = new Random(1);
		int b = random.nextInt(5);
		random = new Random(1);
		int c = random.nextInt(5);
		System.out.println(a);
		System.out.println(c);
		System.out.println(c);
	}
}
