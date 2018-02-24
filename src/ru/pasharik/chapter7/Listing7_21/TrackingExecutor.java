package ru.pasharik.chapter7.Listing7_21;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by pasharik on 24/02/18.
 * Limitation of ExecutorService.shutdownNow() - it doesn't return list of cancelled tasks.
 * This class supports it
 */
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor() {
        exec = Executors.newCachedThreadPool();
    }

    public List<Runnable> getCancelledTasks() {
        if (!exec.isTerminated()) { throw new IllegalStateException("Not terminated yet"); }
        return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }

    @Override
    public void execute(final Runnable command) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted())
                        tasksCancelledAtShutdown.add(command);
                }
            }
        });
    }

    public void shutdown() { exec.shutdown(); }
    public List<Runnable> shutdownNow() { return exec.shutdownNow(); }
    public boolean isShutdown() { return exec.isShutdown(); }
    public boolean isTerminated() { return exec.isTerminated(); }
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException { return exec.awaitTermination(timeout, unit); }
}
