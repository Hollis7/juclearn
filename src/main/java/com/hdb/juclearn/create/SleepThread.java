package com.hdb.juclearn.create;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SleepThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("睡眠完毕");
            }
        });
        t1.start();
        // 线程中断
        t1.interrupt();
    }
}
