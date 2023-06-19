package com.dima.lesson12_threadpool;


public class Main {

    public static void main(String[] args) {
        ThreadPool tp = new ThreadPool(3, 10);

        for (int i = 0; i < 10; i++) {
            int taskNum = i;
            tp.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " Task #" + taskNum);
            });
        }

        tp.waitUntilAllTasksFinished();
        tp.stop();

    }
}
