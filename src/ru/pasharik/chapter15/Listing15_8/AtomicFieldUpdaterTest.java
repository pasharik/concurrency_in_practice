package ru.pasharik.chapter15.Listing15_8;

import ru.pasharik.BarrierUtils;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by pasharik on 13/08/18.
 */
public class AtomicFieldUpdaterTest {
    private volatile Integer counter = 0;
    private CyclicBarrier b = new CyclicBarrier(4 + 1);

    private static AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(
            AtomicFieldUpdaterTest.class,
            Integer.class,
            "counter");

    private void doTest() {
        for (int j = 0; j < 4; j++) {
            new Thread(() -> {
                for (int i = 0; i < 1_000_000; i++) {
                    updater.updateAndGet(AtomicFieldUpdaterTest.this, (o) -> {
                        return (Integer) o + 1;
                    });
                }
                BarrierUtils.awaitQuietly(b);
            }).start();
        }
        BarrierUtils.awaitQuietly(b);
        System.out.println(counter);
    }

    public static void main(String[] args) { new AtomicFieldUpdaterTest().doTest(); }
}
