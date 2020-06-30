package com.overlink.juc.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以替代synchronized（this）
 * synchronized自动解锁（在代码块执行完成）
 * ReentrantLock必须手动释放锁
 * 使用synchronized在遇到异常时jvm会自动释放锁，但是lock必须手动释放，因此需要放在finally中
 * 使用ReentrantLock可以进行tryLock（尝试锁定），在无法锁定或则指定时间内无法获得锁会返回false
 *
 */
public class T_ReentrantLock03 {
    Lock lock = new ReentrantLock();
     void m1(){
         lock.lock();
         try {
             for (int i = 0; i < 10; i++) {
                 TimeUnit.SECONDS.sleep(1);
                 System.out.println(i);
             }
         }catch (InterruptedException e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }


    }

    /**
     * tryLock尝试锁定，不管锁定与否，方法都会执行
     * 可以根据tryLock的返回值判断是否锁定
     * 也可以指定tryLock的时间
     */
    synchronized void m2(){

        boolean locked = false;
        try{
            locked = lock.tryLock(15,TimeUnit.SECONDS);
            System.out.println("m2......"+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked)lock.unlock();
        }


    }

    public static void main(String[] args) {
        T_ReentrantLock03 r1 = new T_ReentrantLock03();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
