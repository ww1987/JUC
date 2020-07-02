package com.overlink.juc.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierTest {

    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        CyclicBarrier countDownLatch = new CyclicBarrier(5,()->{
            System.out.println("线程执行完成 ，开始合并数据");
            try {
                Thread.sleep((long) (Math.random()*500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("合并数据完成");
            System.out.println("集合数据：" + arrayList);
        });

        IntStream.range(0,10).forEach(i -> new Thread(()->{
            System.out.println(String.format("线程：%s开始执行任务",Thread.currentThread().getName()));
            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                arrayList.add(Thread.currentThread().getName());
                System.out.println(String.format("线程：%s执行任务完成",Thread.currentThread().getName()));
                countDownLatch.await();//进入屏障点
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start());


    }



}
