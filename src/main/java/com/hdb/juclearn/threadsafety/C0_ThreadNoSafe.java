package com.hdb.juclearn.threadsafety;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class C0_ThreadNoSafe {
    public static Integer stock = 100000;
    public static AtomicInteger atomicStock = new AtomicInteger(100000);

    static class StockRunnable implements Runnable {
        @Override
        public synchronized void run() {
            if (stock > 0) {
                // 购买.. stock=stock-1； 1000000-1
                //stock--;
                stock--;
            }
            if (atomicStock.get() > 0) {
                atomicStock.decrementAndGet();
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
            threadPool.shutdown();
            // 等待关闭
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        }
        System.out.println("剩余库存：" + stock);
        System.out.println("剩余库存：" + atomicStock.get());
    }
}
