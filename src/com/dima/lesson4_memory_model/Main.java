package com.dima.lesson4_memory_model;

public class Main {

    private static class MyRunnable implements Runnable {
        private MyObject mo;
        private int count = 0;

        public MyRunnable(MyObject mo) {
            this.mo = mo;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                synchronized (this) {
                    count++;
                }
            }

            System.out.printf("%s -> count - %d, myObject count - %d\n",
                    Thread.currentThread().getName(), count, mo.getCount()
                    );
        }
    }

    public static void main(String[] args) {
        MyObject mo1 = new MyObject();
        MyObject mo2 = new MyObject();
        MyObject mo3 = new MyObject();
        Thread t1 = new Thread(new MyRunnable(mo1), "First");
        Thread t2 = new Thread(new MyRunnable(mo2), "Second");

        Runnable runnable = new MyRunnable(mo3);
        Thread t3 = new Thread(runnable, "Third");
        Thread t4 = new Thread(runnable, "Fourth");

//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();

        String[] s = {"Summer", "Summer"};
        String[] a = new String[4];
        a[0] = "Summer";
        a[1] = "Summer";
        System.out.println(s[0] == s[1]);
        System.out.println(a[0] == a[1]);
        System.out.println("orange".compareTo("Orange"));
    }
}

class MyObject {
    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
