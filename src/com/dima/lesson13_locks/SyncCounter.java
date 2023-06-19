package com.dima.lesson13_locks;

public class SyncCounter {
    private int count = 0;

    public void inc() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
