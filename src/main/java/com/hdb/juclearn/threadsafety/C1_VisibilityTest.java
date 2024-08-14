package com.hdb.juclearn.threadsafety;

public class C1_VisibilityTest {
    static volatile Boolean always = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (always) {
                System.out.println("执行了....");
                synchronized (always) {
                }
            }

        }).start();
        Thread.sleep(2000);
        always = false;
    }
}
