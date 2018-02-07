package ru.pasharik.chapter7.Listing7_10;

import net.jcip.examples.LaunderThrowable;

import java.util.concurrent.*;


/**
 * Created by pasharik on 06/02/18.
 */
public class TimedRun {
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void timedRun(Runnable r, long time, TimeUnit timeUnit) throws InterruptedException {
        Future<?> f = executor.submit(r);
        try {
            f.get(time, timeUnit);
        } catch (TimeoutException e) {
            //Do nothing. Future will be cancelled in final block
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        } finally {
            f.cancel(true);
        }
    }
}
