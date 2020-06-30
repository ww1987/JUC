package com.overlink.juc.reentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * 在m1线程调用m2线程
 * m1线程可以调用m2线程
 * 锁是可以重入的
 */
public class T_ReentrantLock02 {
    synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if(i == 2) m2();
        }
    }

    synchronized void m2(){
        System.out.println("m2......");
    }

    public static void main(String[] args) {
        T_ReentrantLock02 r1 = new T_ReentrantLock02();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
