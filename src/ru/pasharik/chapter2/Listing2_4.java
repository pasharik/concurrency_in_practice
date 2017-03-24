package ru.pasharik.chapter2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pasharik on 21/03/17.
 */
public class Listing2_4 {
    private static Set<Long> set = new HashSet<Long>() {
        @Override
        public boolean add(Long aLong) {
            if (contains(aLong)) throw new RuntimeException("" + aLong);
            return super.add(aLong);
        }
    };

    private final AtomicLong count = new AtomicLong(0);

    private class Runner implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) set.add(count.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        new Listing2_4().start();
    }

    private void start() {
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
    }

}
