package ru.pasharik.chapter13.Listing13_7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static ru.pasharik.chapter0.SleepUtil.doSleep;

/**
 * Created by pasharik on 24/07/18.
 */
public class ReadWriteLockTest {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    private void doTest() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                r.lock();
                try {
                    doSleep(500);
                    System.out.println("Reading...");
                    doSleep(500);
                } finally { r.unlock(); }
            }).start();
            doSleep(400);
        }
        new Thread(() -> {
            w.lock();
            try {
                doSleep(700);
                System.out.println("Writing...");
                doSleep(700);
            } finally { w.unlock(); }
        }).start();
    }

    public static void main(String[] args) {
        new ReadWriteLockTest().doTest();
    }
}
