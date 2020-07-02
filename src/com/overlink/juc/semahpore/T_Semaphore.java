package com.overlink.juc.semahpore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号  信号灯  灯塔
 * T1得到信号  T2得不到 反之亦然
 */
public class T_Semaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1,true);
        //允许一个线程同时执行
        new Thread(()->{
            try {
                s.acquire();// acquire  得到  取得 Semaphore 的 permits -1
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release(); // Semaphore 的 permits +1
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();// acquire  得到  取得 Semaphore 的 permits -1
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release(); // Semaphore 的 permits +1
            }
        }).start();
    }


}
