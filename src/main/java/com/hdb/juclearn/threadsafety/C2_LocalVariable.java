package com.hdb.juclearn.threadsafety;

import lombok.val;

public class C2_LocalVariable {
    static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // 线程1
        Thread thread1 = new Thread(() -> {
            userThreadLocal.set(new User());
            User user = userThreadLocal.get();
            for (int i = 0; i < 1000000; i++) {
                user.age++;
            }
            System.out.println(user.age);
        });
        thread1.start();

        // 线程2
        Thread thread2 = new Thread(() -> {
            userThreadLocal.set(new User());
            User user = userThreadLocal.get();
            for (int i = 0; i < 1000000; i++) {
                user.age++;
            }
            System.out.println(user.age);
        });
        thread2.start();

        thread1.join();
        thread2.join();

    }
}
