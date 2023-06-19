package com.dima.lesson8_race_contitions;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurentHashMapExample {

    public static void main(String[] args) {
        Map<String, String> sharedMap = new ConcurrentHashMap<>();

        Thread t1 = new Thread(getRunnable(sharedMap));
        Thread t2 = new Thread(getRunnable(sharedMap));

        t1.start();
        t2.start();
    }

    private static Runnable getRunnable(Map<String, String> map) {
        return () -> {
          for (int i = 0; i < 1_000_000; i++) {
              synchronized (map) {
                  if (map.containsKey("key")) {
                      String val = map.remove("key");
                      if (val == null) {
                          System.out.println("Iteration " + i + " Value for the key was null");
                      }
                  } else {
                      map.put("key", "value");
                  }
              }
          }
        };
    }
}
