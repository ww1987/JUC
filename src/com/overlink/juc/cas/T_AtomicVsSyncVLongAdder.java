package com.overlink.juc.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T_AtomicVsSyncVLongAdder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int k = 0; k < 100000; k++) {
                    count1.incrementAndGet();

                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        long end = System.currentTimeMillis();
        System.out.println("atomic,"+(end-start));
        //=============================================
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int k = 0; k < 100000; k++) {
                    synchronized (lock){
                        count2++;
                    }


                }
            });
        }

         start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
         end = System.currentTimeMillis();
        System.out.println("sync,"+(end-start));
        //=========================================
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int k = 0; k < 100000; k++) {
                        count3.increment();
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        end = System.currentTimeMillis();
        System.out.println("longadder,"+(end-start));
    }
}
