package com.dima.lesson2;

public class DaemonThread {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread terminated");
    }
}
