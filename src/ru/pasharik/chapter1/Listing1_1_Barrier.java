package ru.pasharik.chapter1;

import net.jcip.annotations.NotThreadSafe;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by pasharik on 21/03/17.
 */
public class Listing1_1_Barrier {
    private static Set<Integer> set = new HashSet<Integer>() {
        @Override
        public boolean add(Integer integer) {
            if (contains(integer)) throw new RuntimeException("" + integer);
            return super.add(integer);
        }
    };

    private UnsafeSequence unsafeSequence = new UnsafeSequence();
    private static final CyclicBarrier barrier = new CyclicBarrier(4);

    @NotThreadSafe
    private class UnsafeSequence {
        private int value;

        /** Returns a unique value.*/
        public int getNext() {
            return value++;
        }
    }

    private class Runner implements Runnable {
        @Override
        public void run() {
            try {
                int num = barrier.await();
                System.out.println(Thread.currentThread().getName() + " " + num);
            } catch (Exception ignored) {}
            for (int i = 0; i < 1000; i++) set.add(unsafeSequence.getNext());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Listing1_1_Barrier().start();
    }

    private void start() throws InterruptedException {
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
        new Thread(new Runner()).start();
    }

}
