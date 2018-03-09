package ru.pasharik.chapter8.Listing8_3;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pasharik on 09/03/18.
 */
public class CallerRunsTest {
    public static void main(String[] args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10));
        exec.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 20; i++) {
            System.out.println("  -- submitting -- " + i);
            exec.submit(() -> {
                try { Thread.sleep(1000); } catch (InterruptedException consumed) { }
                System.out.println("Running... in thread: " + Thread.currentThread().getName());
            });
        }
        exec.shutdown();
    }
}
