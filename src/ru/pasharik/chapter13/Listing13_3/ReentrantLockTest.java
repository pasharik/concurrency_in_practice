package ru.pasharik.chapter13.Listing13_3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by pasharik on 20/07/18.
 */
public class ReentrantLockTest {
    private Lock lock = new ReentrantLock();

    private void doTest() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("Running...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) { throw new RuntimeException(e); } finally {
                    lock.unlock();
                }
            }).start();
        }
    }

    public static void main(String[] args) { new ReentrantLockTest().doTest(); }
}
