package com.dima.lesson7_threadlocal;


public class TLInitialValue {

    public static void main(String[] args) {
//        First way
//        ThreadLocal<String> tl = new ThreadLocal() {
//            @Override
//            protected String initialValue() {
//                return "Default";
//            }
//        };

        ThreadLocal<String> tl = ThreadLocal.withInitial(() -> "Default");

        Thread t1 = new Thread(() -> {
           try {
               System.out.println(tl.get());
               Thread.sleep(2000);
               tl.set("New Value");
               System.out.println(tl.get());
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        });
        t1.start();

    }

}
