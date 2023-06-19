package com.dima.lesson13_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calculator {
    private static class Calculation {
        public static final int UNSPECIFIED = -1;
        public static final int ADDITION = 0;
        public static final int SUBTRACTION = 1;

        private int type = UNSPECIFIED;
        private double value;

        public Calculation(int type, double value) {
            this.type = type;
            this.value = value;
        }
    }


    private double result = 0.0;
    private Lock lock = new ReentrantLock();

    public void add(double value) {
        try {
            lock.lock();
            result += value;
        } finally {
            lock.unlock();
        }
    }

    public void subtract(double value) {
        try {
            lock.lock();
            result -= value;
        } finally {
            lock.unlock();
        }
    }

    public void calculate(Calculation... calcs) {
        try {
            lock.lock();
            for (Calculation operation : calcs) {
                switch(operation.type) {
                    case Calculation.ADDITION -> add(operation.value);
                    case Calculation.SUBTRACTION -> subtract(operation.value);
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
