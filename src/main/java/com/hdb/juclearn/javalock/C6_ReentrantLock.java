package com.hdb.juclearn.javalock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class C6_ReentrantLock {
    private static Integer stock = 100000;
    public static ReentrantLock lock = new ReentrantLock();

    static class StockRunnable implements Runnable {

        @Override
        public void run() {
            try {
                lock.lock();
                stock--;
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        StockRunnable task = new StockRunnable();
        try {
            for (int i = 0; i < 100000; i++) {
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdownNow();
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        }
        System.out.println("剩余库存：" + stock);
    }

}
