package ru.pasharik.chapter0.scjp.example2;

/**
 * Created by pasharik on 17/04/17.
 */
public class MyQueueTester {
    private static final MyQueue<String> q = new MyQueue<>();

    private static class ReaderThread extends Thread {
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(q.take());
                }
            } catch (InterruptedException e) { }
        }
    }

    private static class WriterThread extends Thread {
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    q.put("" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) { }
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        new WriterThread().start();
    }
}
