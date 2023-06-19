package com.dima.lesson12_threadpool;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private BlockingQueue<Runnable> tasksQueue;
    private List<PoolThreadRunnable> runnables = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool(int numOfThreads, int maxNumOfTasks) {
        tasksQueue = new ArrayBlockingQueue(maxNumOfTasks);

        for (int i = 0; i < numOfThreads; i++) {
            PoolThreadRunnable ptr = new PoolThreadRunnable(tasksQueue);
            runnables.add(ptr);
        }

        runnables.forEach(runnable -> new Thread(runnable).start());
    }

    public synchronized void execute(Runnable task) throws IllegalStateException {
        if (isStopped) throw new IllegalStateException("ThreadPool is stopped");
        this.tasksQueue.offer(task);
    }

    public synchronized void stop() {
        isStopped = true;
        runnables.forEach(PoolThreadRunnable::doStop);
    }

    public synchronized void waitUntilAllTasksFinished() {
        while (tasksQueue.size() > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
