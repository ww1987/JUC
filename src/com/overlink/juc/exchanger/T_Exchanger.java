package com.overlink.juc.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 */
public class T_Exchanger {

    static Exchanger<String> exchanger  = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";

            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  " +s);
        }).start();

        new Thread(()->{
            String s = "T2";

            try {
//                Thread.sleep(1000);
                s = exchanger.exchange(s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  " +s);
        }).start();

        new Thread(()->{
            String s = "T3";

            try {
//                Thread.sleep(1000);
                s = exchanger.exchange(s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  " +s);
        }).start();


        new Thread(()->{
            String s = "T4";

            try {
//                Thread.sleep(1000);
                s = exchanger.exchange(s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"  " +s);
        }).start();
    }
}
