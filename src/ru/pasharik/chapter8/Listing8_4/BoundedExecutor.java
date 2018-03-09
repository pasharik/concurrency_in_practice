package ru.pasharik.chapter8.Listing8_4;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * Created by pasharik on 09/03/18.
 */
public class BoundedExecutor {
    private Executor exec;
    private Semaphore semaphore;

    public BoundedExecutor(Executor exec, int limit) {
        this.exec = exec;
        semaphore = new Semaphore(limit);
    }

    public void executeBounded(Runnable r) throws InterruptedException {
        semaphore.acquire();
        try {
            exec.execute(() -> {
                try {
                    r.run();
                } finally { semaphore.release(); }
            });

        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}
