package ru.pasharik.chapter8.Listing8_9;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pasharik on 12/03/18.
 */
public class TimingThreadPool extends ThreadPoolExecutor {
    private AtomicLong numTasks = new AtomicLong(0);
    private AtomicLong totalTime = new AtomicLong(0);
    private ThreadLocal<Long> currentTaskTime = new ThreadLocal<>();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        numTasks.incrementAndGet();
        currentTaskTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        totalTime.addAndGet(System.nanoTime() - currentTaskTime.get());
    }

    @Override
    protected void terminated() {
        System.out.println(String.format("Average time: %dns", totalTime.get() / numTasks.get()));
    }
}
