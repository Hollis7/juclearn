package com.hdb.juclearn.javalock;

import org.apache.catalina.startup.Catalina;

import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;
    ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "还剩：" + (--number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) {
                ticket.sale();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) {
                ticket.sale();
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) {
                ticket.sale();
            }
        }, "c").start();
    }
}
