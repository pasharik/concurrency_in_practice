package ru.pasharik.chapter1;

import net.jcip.annotations.NotThreadSafe;
import ru.pasharik.chapter5.Listing5_11.TestHarness;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pasharik on 21/03/17.
 */
public class Listing1_1 {
    private static Set<Integer> set = new HashSet<Integer>() {
        @Override
        public boolean add(Integer integer) {
            if (contains(integer)) throw new RuntimeException("" + integer);
            return super.add(integer);
        }
    };

    private UnsafeSequence unsafeSequence = new UnsafeSequence();

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
            for (int i = 0; i < 1000; i++) set.add(unsafeSequence.getNext());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Listing1_1().start();
    }

    private void start() throws InterruptedException {
        new TestHarness().timeTasks(4, new Runner());
    }

}
