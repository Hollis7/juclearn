package com.hdb.juclearn.create;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableFuture {
    public static void main(String[] args) {
        //第一步，创建Callable实现类的实例，并实现call方法
        //第2步，创建Callable实现类实例
        Task task = new Task();
        //第3步，使用FutureTask类来包装Callable对象，可以创建匿名对象
        // 也可以直接用lambda省略1、2步
        FutureTask<Integer> future = new FutureTask<>(task);
        new Thread(future).start();
        log.info("1.已启动...");
        try {
            //FutureTask的get()方法会自动阻塞，知道得到任务执行结果为止
            Integer value = future.get();  //第5步，调用FutureTask对象的方法来获取子线程执行结束后的返回值
            log.info("3.返回值" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        log.info("2.子线程运行中...");
        Thread.sleep(5000);
        return 5;
    }
}
