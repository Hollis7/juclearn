package com.hdb.juclearn.javalock;

public class C4_SleepAndWait {
    public static final Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {

            System.out.println(mainThread.getState());
        });


        thread.start();
        synchronized (monitor) {

            monitor.wait(2000);
        }
    }
}
