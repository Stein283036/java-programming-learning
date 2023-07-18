package com.guhe.misc;

/**
 * @author njl
 * @date 2023/5/4
 */
public class Misc2App {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new MyThread());
		thread.start();
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "-" + i);
			if (i == 20) {
				thread.join(); // 等该线程执行结束
			}
		}
	}
}

