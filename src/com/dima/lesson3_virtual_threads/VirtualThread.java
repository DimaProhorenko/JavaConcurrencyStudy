package com.dima.lesson3_virtual_threads;

public class VirtualThread {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread vt = Thread.ofVirtual().unstarted(runnable);
        vt.start();

        try {
            vt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
