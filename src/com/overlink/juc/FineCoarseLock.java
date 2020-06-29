package com.overlink.juc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * synchronnized优化
 * -锁的细化
 * 同步代码块种语句越少越好
 * 如果代码中细碎的锁比较多时应吧锁加载整体上
 */
public class FineCoarseLock {
    int count = 0;

    synchronized void m1(){
        //不需要使用sync的代码
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要使用sync，这时不应该给整个方法上锁
        count++;

        //不需要使用sync的代码
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    void m2(){
        //不需要使用sync的代码
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑中只有下面这句需要使用sync，这时不应该给整个方法上锁
        synchronized (this){
            count++;
        }


        //不需要使用sync的代码
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
