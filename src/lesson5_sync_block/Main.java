package lesson5_sync_block;

class Exchanger {
    private Object o;

    public synchronized Object getObject() {
        return o;
    }

    public synchronized void setObject(Object o) {
        this.o = o;
    }
}

public class Main {

    public static void main(String[] args) {

        Exchanger e = new Exchanger();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    e.setObject("" + i);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(e.getObject());
            }
        });

        t1.start();
        t2.start();

    }
}
