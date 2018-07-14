package ru.pasharik.chapter12.Listing12_5;

import java.util.concurrent.*;

/**
 * Created by pasharik on 13/07/18.
 */
public class CyclicBarrierTest {
    private static int NUM_THREADS = 10;
    private final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS + 1);
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    private void test() {
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                final int k = i;
                exec.submit(()-> {
                    try {
                        barrier.await();
                        Thread.sleep(1000 + k * 100);
                        System.out.println("Completed " + k);
                        barrier.await();
                    } catch (Exception e) { throw new RuntimeException(e); }
                });
            }
            System.out.println("Starting...");
            barrier.await(); //Wait for all threads to be ready and start at the same time
            barrier.await(); //Wait for all of them to finish
            System.out.println("All done!");
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public static void main(String[] args) {
        new CyclicBarrierTest().test();
        exec.shutdown();
    }
}
