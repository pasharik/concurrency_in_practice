package ru.pasharik.chapter7.Listing7_25;

/**
 * Created by pasharik on 28/02/18.
 */
public class UncaughtExample {
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                throw new RuntimeException("Test exception");
            }
        };
        t.setUncaughtExceptionHandler(new UEHLogger());
        t.start();
    }
}
