package com.overlink.juc.readWriteLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁就是  共享锁和排他锁
 */
public class T_ReadWriteLock {
    static Lock lock  = new ReentrantLock();

    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    public static void read(Lock lock){
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("read over:"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void write(Lock lock,int v){
        lock.lock();
        try {
            Thread.sleep(1000);
            value = v;
            System.out.println("write over"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        Runnable readR = () -> read(lock);
        Runnable readR = () -> read(readLock);

//        Runnable write = () -> write(lock,new  Random().nextInt());
        Runnable write = () -> write(writeLock,new Random().nextInt());

        for (int i = 0; i < 2; i++) {
            new Thread(write).start();
        }

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }


    }
}
