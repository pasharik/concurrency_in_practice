package ru.pasharik.chapter8.Listing8_2;

import java.util.concurrent.*;

/**
 * Created by pasharik on 05/03/18.
 */
public class ThreadPoolExecutorTest {
    private void doRun() {
        //** Exits 60 seconds after all tasks finished. Unlimited number of threads
        //ExecutorService exec = Executors.newCachedThreadPool();

        //** Exits 10 seconds after all tasks finished. 4 threads
        ThreadPoolExecutor exec = new ThreadPoolExecutor(4, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        exec.allowCoreThreadTimeOut(true); //Without it pool never shutdown unless call shutdown() manually

        //** Similar to newCachedThreadPool()
        //** Exits 5 seconds after all tasks finished. Up to 100 threads, but if there is more than 100 tasks,
        //** new tasks rejected, because SynchronousQueue doesn't have any internal capacity
        //ExecutorService exec = new ThreadPoolExecutor(0, 100, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        for (int i = 0; i < 10; i++) {
            exec.submit(() -> {
                System.out.println("Do stuff");
                try { Thread.sleep(1000); } catch (InterruptedException ignored) { }
            });
        }
        //exec.shutdown(); //If allowCoreThreadTimeOut() is not set
    }

    public static void main(String[] args) { new ThreadPoolExecutorTest().doRun(); }
}
