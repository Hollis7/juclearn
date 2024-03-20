package com.hdb.juclearn.threadsafety;

public class C4_InheritableThreadLocal_2 {
    public static void main(String[] args) throws InterruptedException {
        // 替换成 InheritableThreadLocal
//        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("test");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("value:" + value);
            }
        });
        thread.start();
        Thread.sleep(1000);
    }
}
