package com.overlink.juc;

import java.util.ArrayList;
import java.util.List;

/**
 * 保证线程可见性
 * -MESI
 * -缓存一致性协议
 * 禁止指令重排序（CPU）
 * -DCL单例
 * -Double Check Lock
 * volatile 并不能保证多个线程同时修改running变量是所带来的不一致问题，也就是volatile不能替代synchronized
 */
public class T_Volatile {
    volatile int count = 0;
//    int count = 0;
    void  m(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T_Volatile t_volatile = new T_Volatile();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t_volatile::m,"thread-"+i));

        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try{
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(t_volatile.count);
    }
}
