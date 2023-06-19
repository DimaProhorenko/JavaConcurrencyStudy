package com.dima.lesson13_locks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
public class LockCounter {
    private int counter = 0;
    private Lock lock = new ReentrantLock();

    public void inc() {
        try {
            lock.lock();
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        try {
            lock.lock();
            return counter;
        } finally {
            lock.unlock();
        }
    }
}
