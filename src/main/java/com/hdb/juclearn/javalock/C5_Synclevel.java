package com.hdb.juclearn.javalock;

import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class C5_Synclevel {


    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(4100);
        class T {
            Integer age;
        }

        T o = new T();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("Thread1 = " + ClassLayout.parseInstance(o).toPrintable());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("Thread2 = " + ClassLayout.parseInstance(o).toPrintable());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main:" + ClassLayout.parseInstance(o).toPrintable());

    }
}
