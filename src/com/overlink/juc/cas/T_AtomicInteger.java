package com.overlink.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样问题的更高效的方法，使用AtomicXXX类
 * Atomic类均是使用CAS方式实现
 * Atomic类本事方法都是原子性的，但是不能保证多个方法连续是调用的原子性的
 * CAS：Compare And Set （无锁优化，自旋锁）
 * cas（（V，Expected，NewValue）
 * V 要改的值
 * Expected 期望值
 * NewValue 新值
 * -if V == Expected
 *  V =NewValue
 *  otherwise try again or fail
 * -CPU原语支持
 * 存在的问题  ABA问题 解决方式 加版本 @see AtomicStampedReference
 */
public class T_AtomicInteger {
    AtomicInteger count = new AtomicInteger(0);
    void m(){
        for (int i = 0; i < 100000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T_AtomicInteger t = new T_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m,"thread-"+i));
        }
        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try{
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
