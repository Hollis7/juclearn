package com.hdb.juclearn.threadpoll;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class C2_CustomeThreadPool {
    public static void main(String[] args) throws InterruptedException {

//        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(10);
//
//        for (int i = 0; i < 11; i++) {
//            q.put(i);
//        }
//
//        System.out.println("a + ");

        ExecutorService threadPool = new ThreadPoolExecutor(
                10,
                20,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
//                new CustomizableThreadFactory("购物"),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //10个顾客请求
        try {
            for (int i = 1; i <= 100; i++) {
                MyTask task = new MyTask(i);
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}

class MyTask implements Runnable {
    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "程序员做第" + i + "个项目");
//        System.out.println(i);
        try {
            Thread.sleep(3000L);//业务逻辑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}