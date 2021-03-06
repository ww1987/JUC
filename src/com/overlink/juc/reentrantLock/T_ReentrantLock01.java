package com.overlink.juc.reentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * 在主线程执行m1线程在执行m2线程
 * 这种情况只有在m1执行完成后才能执行m2线程
 *
 */
public class T_ReentrantLock01 {
    synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    synchronized void m2(){
        System.out.println("m2......");
    }

    public static void main(String[] args) {
        T_ReentrantLock01 r1 = new T_ReentrantLock01();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
