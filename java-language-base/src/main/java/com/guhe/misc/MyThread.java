package com.guhe.misc;

/**
 * @author njl
 * @date 2023/7/18
 */
class MyThread implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.println(Thread.currentThread().getName() + "-" + i);
		}
	}
}
