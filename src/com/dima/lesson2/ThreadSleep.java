package com.dima.lesson2;


public class ThreadSleep {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " running");
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread with sleep");

        thread.start();
    }
}
