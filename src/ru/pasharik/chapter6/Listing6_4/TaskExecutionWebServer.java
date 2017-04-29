package ru.pasharik.chapter6.Listing6_4;

import ru.pasharik.chapter6.Listing6_5.ThreadPerTaskExecutor;
import ru.pasharik.chapter6.Listing6_6.WithinThreadExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by pasharik on 29/04/17.
 */
public class TaskExecutionWebServer {
    private static final int N_THREADS = 5;
    private static final Executor ex = Executors.newFixedThreadPool(N_THREADS);
    //private static final Executor ex = new ThreadPerTaskExecutor();
    //private static final Executor ex = new WithinThreadExecutor();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Runnable r = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) { throw new RuntimeException(e); }
                    System.out.println(Thread.currentThread().getName());
                }
            };
            ex.execute(r);
            System.out.println("Submitted: " + i);
        }
    }
}
