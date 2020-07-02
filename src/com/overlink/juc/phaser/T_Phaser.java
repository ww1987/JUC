package com.overlink.juc.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * phaser 阶段
 * 按阶段队线程进行控制
 * 遗传算法
 */
public class T_Phaser {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSheep(int milli){
        try {
            TimeUnit.SECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(3);

        for (int i = 0; i < 5; i++) {
            new Thread( new Person("person"+i)).start();
        }
    }

   static class Person implements Runnable{
        String name ;
        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSheep(r.nextInt(10));
            System.out.println(name+"arrive");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSheep(r.nextInt(10));
            System.out.println(name+"leave");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSheep(r.nextInt(10));
            System.out.println(name+"eat");
            phaser.arriveAndAwaitAdvance();
        }

       @Override
       public void run() {
           arrive();
           eat();
           leave();
       }
   }
}
class MarriagePhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                System.out.println("所有人到齐了");
                return false;
            case 1:
                System.out.println("所有人吃完了");
                return false;
            case 2:
                System.out.println("所有人离开了");
                System.out.println("婚礼结束");
                return true;
            default:
                return true;
        }
    }
}

