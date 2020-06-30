package com.overlink.juc.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平锁
 */
public class T_ReentrantLock05 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示公平锁，谁先执行谁获得锁

    public void run() {
        for (int i = 0; i < 100; i++) {


            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T_ReentrantLock05 rl = new T_ReentrantLock05();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        Thread t3 = new Thread(rl);
        Thread t4 = new Thread(rl);
        Thread t5 = new Thread(rl);
        Thread t6 = new Thread(rl);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
