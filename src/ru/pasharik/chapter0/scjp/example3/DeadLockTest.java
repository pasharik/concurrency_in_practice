package ru.pasharik.chapter0.scjp.example3;

/**
 * Created by pasharik on 17/04/17.
 */
public class DeadLockTest {
    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();

    private static class T1 extends Thread {
        public void run() {
            synchronized (obj1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                synchronized (obj2) {}
            }
        }
    }

    private static class T2 extends Thread {
        public void run() {
            synchronized (obj2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
                synchronized (obj1) {}
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new T2().start();
    }
}
