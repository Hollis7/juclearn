package com.hdb.juclearn.create;

public class DeamonThread {
    public static void main(String[] args) {
        //创建线程(默认前台线程)
        Thread d1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(500);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //设置线程为守护线程
        d1.setDaemon(true);//主线程结束便结束了
        d1.start();
        System.out.println("主线程结束");

    }
}
