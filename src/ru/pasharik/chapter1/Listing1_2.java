package ru.pasharik.chapter1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pasharik on 21/03/17.
 */
public class Listing1_2 {
    private static Set<Integer> set = new HashSet<Integer>() {
        @Override
        public boolean add(Integer integer) {
            if (contains(integer)) throw new RuntimeException("" + integer);
            return super.add(integer);
        }
    };

    private Sequence sequence = new Sequence();

    @ThreadSafe
    private class Sequence {
        @GuardedBy("this")
        private int value;

        public synchronized int getNext() {
            return value++;
        }
    }

    private class Runner implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++) set.add(sequence.getNext());
        }
    }

    public static void main(String[] args) {
        new Listing1_2().start();
    }

    private void start() {
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
    }

}
