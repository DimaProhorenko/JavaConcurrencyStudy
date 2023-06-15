package com.dima.lesson2;

public class Main {

    private static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread started");
            System.out.println("MyThread finished");
        }
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable thread started");
            System.out.println("Runnable thread finished");
        }
    }


    public static void main(String[] args) {
        Thread myThread = new MyThread();
        Thread myRunnable = new Thread(new MyRunnable());
        Thread anonymousRunnable = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous runnable thread started");
                System.out.println("Anonymous runnable thread finished");
            }
        });

        Thread lambdaRunnable = new Thread(() -> {
            System.out.println("Lambda thread started");
            System.out.println("Lambda thread finished");
        });



        System.out.println("Main thread started");
        myThread.start();
        myRunnable.start();
        anonymousRunnable.start();
        lambdaRunnable.start();
        System.out.println("Main thread finshed");
    }
}
