package com.hdb.juclearn.threadpoll;


import java.util.Arrays;
import java.util.concurrent.*;

public class C3_ExecuteVSSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        Future<Integer> future = threadPool.submit(() -> {
            System.out.println("running");
//            int a = 1 / 0;
            return 5;
        });
        Integer res = future.get();
        System.out.println(res);
        threadPool.shutdownNow();

        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable task;
                while ((task = arrayBlockingQueue.poll()) != null) {
                    task.run();
                }
            }
        }).start();

    }


}
