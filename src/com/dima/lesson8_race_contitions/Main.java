package com.dima.lesson8_race_contitions;

class Counter {
    private int value = 0;
    int incAndGet() {
        return ++value;
    }

    int get() {
        return value;
    }
}

class SyncCounter extends Counter {
    @Override
    int incAndGet() {
        synchronized (this) {
            return super.incAndGet();
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(getRunnable(counter, "Thread 1"));
        Thread t2 = new Thread(getRunnable(counter, "Thread 1"));

//        t1.start();
//        t2.start();

        Counter syncCounter = new Counter();
        Thread t3 = new Thread(getIncRunnable(syncCounter));
        Thread t4 = new Thread(getReadRunnable(syncCounter));

        t3.start();
        t4.start();

    }

    private static Runnable getRunnable(Counter counter, String message) {
        return () -> {
          for (int i = 0; i < 1000; i++) {
              counter.incAndGet();
          }
            System.out.println(message + " " + counter.get());
        };
    }

    private static Runnable getIncRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.incAndGet();
            }
            System.out.println("Thread 1 final count: " + counter.get());
        };
    }

    private static Runnable getReadRunnable(Counter counter) {
        return () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1);
                    System.out.println("Thread 2 count: " + counter.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
