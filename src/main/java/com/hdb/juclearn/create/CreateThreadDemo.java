package com.hdb.juclearn.create;

import ch.qos.logback.core.joran.conditional.ThenAction;

public class CreateThreadDemo {
    public static void main(String[] args) {
        createThread();
        createRunnable();
        System.out.println("主线程");
        Thread t3 = new Thread(new MyRunnable());
        t3.start();
    }

    public static void createRunnable() {
        // new Runnable匿名内部类
        Thread t1 = new Thread(() -> {
            System.out.println("线程Runnable");
        });
        t1.start();
    }

    public static void createThread() {

        // new Thread 匿名内部类
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("线程Thread");
            }
        };
        t2.start();
    }
}
