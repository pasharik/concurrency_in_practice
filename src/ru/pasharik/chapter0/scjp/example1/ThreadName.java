package ru.pasharik.chapter0.scjp.example1;

/**
 * Created by pasharik on 17/04/17.
 */
public class ThreadName {
    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                try {
                    System.out.println(i + " " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable r = new MyRunnable();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}
