package ru.pasharik.chapter15.Listing15_2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by pasharik on 09/08/18.
 */
public class CasCounterTester {
    private CasCounter c;
    private CyclicBarrier barrier;

    public void doTest() throws BrokenBarrierException, InterruptedException {
        c = new CasCounter();
        barrier = new CyclicBarrier(4 + 1);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                    for (int j = 0; j < 1_000_000; j++) {
                        c.increment();
                    }
                    barrier.await();
                } catch (BrokenBarrierException | InterruptedException e) { throw new RuntimeException(e); }
            }).start();
        }
        barrier.await(); System.out.println("Calculating...");
        barrier.await(); System.out.println(c.getValue());
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new CasCounterTester().doTest();
    }
}
