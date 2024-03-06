package com.hdb.juclearn.threadpoll;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class C1_CreateThreadPool {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        // 固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //单个线程 线程池
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        动态创建线程 线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();

//        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(task);
//                threadPool.schedule(task, 5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 不会立马停止正在执行的线程，会等待所有的任务执行完后才彻底关闭
//            threadPool.shutdown();


            // 不会立马停止正在执行的线程，只会等待正在执行的线程执行完后才彻底关闭
            threadPool.shutdownNow();
            //等待所有任务执行，最多等待Long.MAX_VALUE的时间
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            System.out.println(threadPool.isTerminated());
        }

    }
}

@Slf4j
class Task implements Runnable {
    @Override
    public void run() {
        log.info("\t 办理业务");
    }
}