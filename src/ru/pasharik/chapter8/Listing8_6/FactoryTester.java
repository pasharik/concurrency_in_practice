package ru.pasharik.chapter8.Listing8_6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pasharik on 09/03/18.
 */
public class FactoryTester {
    public static void main(String[] args) {
        //MyAppThread.setDebugLifecycle(true);
        ExecutorService exec = Executors.newFixedThreadPool(2, new MyThreadFactory("Poooooooool"));
        for (int i = 0; i < 10; i++) {
            exec.execute(() -> { System.out.println("Thread: " + Thread.currentThread().getName()); });
        }
        exec.shutdown();
    }
}
