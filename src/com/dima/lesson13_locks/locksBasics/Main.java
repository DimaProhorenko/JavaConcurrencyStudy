package com.dima.lesson13_locks.locksBasics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        lockBasics();
    }

    private static void lockBasics() {
        Lock lock = new ReentrantLock();
        Runnable runnable = () -> {lockSleepUnlock(lock, 1000);};

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }

    private static void lockSleepUnlock(Lock lock, long millis) {
        try {
            lock.lock();
            printThreadMsg(" holds the lock");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void printThreadMsg(String text) {
        String msg = Thread.currentThread().getName() + text;
        int msgLength = msg.length();

        System.out.println("-".repeat(msgLength));
        System.out.println(msg);
        System.out.println("-".repeat(msgLength));
    }
}
