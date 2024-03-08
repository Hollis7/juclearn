package com.hdb.juclearn.threadsafety;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C3_ThreadLocalOOM {
    public static void main(String[] args) {
        ThreadLocal<User> userL = new ThreadLocal<User>();

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 30; i++) {
            // 线程1
            executorService.execute(() -> {
                // new User();
                userL.set(new User());
                userL.remove();

            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
