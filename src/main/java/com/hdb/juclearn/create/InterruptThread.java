package com.hdb.juclearn.create;

import java.util.concurrent.TimeUnit;

public class InterruptThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                // 每隔1s 将时间片清除
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    // 当出现InterruptedException  会清除中断标记      false
                    e.printStackTrace();
                    // 再次加上中断标记
                    Thread.currentThread().interrupt();      // true
                }
                //  如果中断的标记为true
                // 获取线程中断标记，  并且会清除标记
                System.out.println(Thread.currentThread().isInterrupted());
                if (Thread.interrupted()) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }

                // 定时监控系统...
                System.out.println("定时监控...");
            }
        });
        thread.start();
        thread.interrupt();
    }
}
