package ru.pasharik.chapter2.Listing2_4;

import net.jcip.annotations.GuardedBy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pasharik on 21/03/17.
 */
public class CountingFactorizer {
    private static Set<Long> set = new HashSet<Long>() {
        @Override
        public boolean add(Long aLong) {
            if (contains(aLong)) throw new RuntimeException("" + aLong);
            return super.add(aLong);
        }
    };

    @GuardedBy("this") private Long countSimple = 0L;
    private final AtomicLong countAtomic = new AtomicLong(0);
    private final AtomicInteger activeThreadCount = new AtomicInteger(0);
    private long timestamp = 0;

    private class Runner implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) {
                set.add(countAtomic.incrementAndGet());                         //AtomicLong
                //synchronized (CountingFactorizer.this) { set.add(countSimple++); }    //synchronized
            }
            printTime();
        }
    }

    private void printTime() {
        activeThreadCount.decrementAndGet();
        if (activeThreadCount.get() == 0) System.out.println(System.currentTimeMillis() - timestamp);
    }

    public static void main(String[] args) {
        new CountingFactorizer().start();
    }

    private void start() {
        timestamp = System.currentTimeMillis();
        activeThreadCount.set(4);
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
    }

}
