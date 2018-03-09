package ru.pasharik.chapter8.Listing8_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pasharik on 09/03/18.
 */
public class BoundedExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        BoundedExecutor exec = new BoundedExecutor(service, 10);
        for (int i = 0; i < 20; i++) {
            System.out.println("  -- submitting -- " + i);
            exec.executeBounded(() -> {
                System.out.println("Running... ");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) { }
            });
        }
        service.shutdown();
    }
}
