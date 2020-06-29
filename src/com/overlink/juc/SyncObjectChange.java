package com.overlink.juc;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，如果o的属性发生盖欧彼岸，不影响锁的使用
 * 但是如果o变成另一个对象，则锁定的对象发生改变
 * 应避免将锁定对象的引用变成另外的对象
 */
public class SyncObjectChange {
    /*final*/ Object o = new Object();

    void m(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        SyncObjectChange syncObjectChange = new SyncObjectChange();
        //启动第一个线程
        new Thread(syncObjectChange::m,"t2").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //创建第二个线程
        Thread t2 = new Thread(syncObjectChange::m,"t2");

        syncObjectChange.o = new Object();

        t2.start();
    }
}
