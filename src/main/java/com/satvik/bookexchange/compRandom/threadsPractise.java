package com.satvik.bookexchange.compRandom;

import java.util.Arrays;

public class threadsPractise {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main method starts!");

        Runnable t1Runnable = () -> {
                int[] values = {1,2,3,4,5};
                for(int i=0;i<values.length;i++){
                    System.out.println("t1 : "+values[i]);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        System.out.println("something went wrong while sleeping");
                    }
                    values[i]*=2;
                }
                System.out.println(Arrays.toString(values));
            };

        Runnable t2Runnable = () -> {
                int[] values = {1,2,3,4,5};
                for(int i=0;i<values.length;i++){
                    System.out.println("t2 -----: "+values[i]);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        System.out.println("soemthing went wrong while sleeping");
                    }
                    values[i]*=2;
                }
                System.out.println(Arrays.toString(values));
            };

//        Thread t1 = new Thread(t1Runnable);
//        Thread t2 = new Thread(t2Runnable);
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();



//        Thread cur = Thread.currentThread();
//        System.out.println("current thread: "+cur.getName()+"\nwith priority: "+cur.getPriority());
        Counter counter = new Counter();
        System.out.println("initial value of c: "+counter.c);

        Thread t3 = new Thread(() -> {
            for(int i=0;i<10000;i++){
                counter.increment();
            }
        });

        Thread t4 = new Thread(() -> {
            for(int i=0;i<10000;i++){
                counter.increment();
            }
        });

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("final value of c: "+counter.c);
        System.out.println("main method ends!");
    }
}
