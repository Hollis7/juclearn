package com.hdb.juclearn.create;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartAndRun {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->
        {
            System.out.println(Thread.currentThread().getName());
            log.info("2.子线程启动..");
        });
        log.info("1.开始创建线程");
        // t1.run();       // 同步执行
        t1.setName("徐庶1");
        t1.start();     // 异步执行
        log.info("3.主线程结束");

        System.out.println(Thread.currentThread().getName());
    }
}
