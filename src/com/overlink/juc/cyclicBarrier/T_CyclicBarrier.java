package com.overlink.juc.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * cyclicbarrier  栅栏-可理解为赛马场的栅栏
 *发令枪
 * 所有的程序到20个线程 栅栏就会被放到
 * 示例：
 */
public class T_CyclicBarrier {
    public static void main(String[] args) {



        CyclicBarrier barrier = new CyclicBarrier(20,()-> {
                System.out.println("人满，发车");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
