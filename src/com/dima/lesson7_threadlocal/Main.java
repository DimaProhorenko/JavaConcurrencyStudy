package com.dima.lesson7_threadlocal;

public class Main {
    private static ThreadLocal<String> tl = new ThreadLocal<>();
    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                tl.set(Thread.currentThread().getName());
                Thread.sleep(2000);
                String value = tl.get();
                System.out.println(value);

                Thread.sleep(3000);
                if (Thread.currentThread().getName().equalsIgnoreCase("Thread 1")) {
                    tl.remove();
                }
                System.out.println(tl.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunnable(), "Thread 1");
        Thread t2 = new Thread(new MyRunnable(), "Thread 2");


        t1.start();
        t2.start();

    }
}
