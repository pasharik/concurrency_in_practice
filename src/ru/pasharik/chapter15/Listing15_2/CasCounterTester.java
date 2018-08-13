package ru.pasharik.chapter15.Listing15_2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static ru.pasharik.BarrierUtils.awaitQuietly;

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
                awaitQuietly(barrier);
                for (int j = 0; j < 1_000_000; j++) {
                    c.increment();
                }
                awaitQuietly(barrier);
            }).start();
        }
        awaitQuietly(barrier); System.out.println("Calculating...");
        awaitQuietly(barrier); System.out.println(c.getValue());
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new CasCounterTester().doTest();
    }
}
