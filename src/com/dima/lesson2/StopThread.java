package com.dima.lesson2;

public class StopThread {
    private static class MyStopRunnable implements Runnable {
        private boolean stopRequested = false;

        public synchronized void requestStop() {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRequested;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("Stoppable runnable running");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("Stoppable runnable finished");
        }
    }

    public static void main(String[] args) {
        MyStopRunnable stoppableRunnable = new MyStopRunnable();
        Thread thread = new Thread(stoppableRunnable, "StopRunnable");
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();
    }
}

