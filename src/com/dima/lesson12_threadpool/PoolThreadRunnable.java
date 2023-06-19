package com.dima.lesson12_threadpool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {

    private Thread                  thread;
    private BlockingQueue<Runnable> taskQueue;
    private boolean                 isStopped = false;

    public PoolThreadRunnable(BlockingQueue<Runnable> tasksQueue) {
        this.taskQueue = tasksQueue;
    }


    @Override
    public void run() {
        thread = Thread.currentThread();
        while (!isStopped()) {
            try {
                Runnable runnable = taskQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}
