package ru.pasharik.chapter7.Listing7_12;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.*;

/**
 * Created by pasharik on 16/02/18.
 */
@ThreadSafe
public class CancellingThreadPoolExecutor extends ThreadPoolExecutor {
    public CancellingThreadPoolExecutor(int nThreads) {
        super(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellableTask) {
            return ((CancellableTask<T>) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }
}
