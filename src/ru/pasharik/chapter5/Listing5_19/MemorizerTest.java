package ru.pasharik.chapter5.Listing5_19;

import ru.pasharik.chapter5.Listing5_11.TestHarness;

/**
 * Created by pasharik on 22/04/17.
 */
public class MemorizerTest {
    static Computable<Integer, Integer> c = new Computable<Integer, Integer>() {
        @Override
        public Integer compute(Integer arg) throws InterruptedException {
            Thread.sleep(2000);
            return arg + 1000;
        }
    };

    static Memorizer<Integer, Integer> m = new Memorizer<Integer, Integer>(c);

    private static class MyRunnable implements Runnable {
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("Thread : " + Thread.currentThread().getName() + " | " + m.compute(i));
                } catch (InterruptedException e) { throw new RuntimeException(e); }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable r = new MyRunnable();
        new TestHarness().timeTasks(4, r);
    }
}
